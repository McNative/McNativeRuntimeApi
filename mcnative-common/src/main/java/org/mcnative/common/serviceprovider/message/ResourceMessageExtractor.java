/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 06.04.20, 22:29
 * @web %web%
 *
 * The McNative Project is under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.mcnative.common.serviceprovider.message;

import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.message.MessagePack;
import net.pretronic.libraries.message.MessageProvider;
import net.pretronic.libraries.plugin.Plugin;
import org.mcnative.common.McNative;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ResourceMessageExtractor {

    public static void extractMessages(Plugin<?> plugin){
        MessageProvider messageProvider = McNative.getInstance().getRegistry().getServiceOrDefault(MessageProvider.class);
        if(messageProvider != null){
            String module = plugin.getDescription().getMessageModule();
            if(module != null){
                List<MessagePack> result = messageProvider.loadPacks(module);
                MessagePack defaultPack = readPack(plugin,"messages/default.yml");
                if(defaultPack != null){
                    if(result.isEmpty()){
                        String languageTag = Locale.getDefault().toLanguageTag().replace("-","_");
                        String language = Locale.getDefault().getLanguage();

                        MessagePack pack = readPack(plugin,"messages/"+languageTag+".yml");
                        if(pack == null) pack = readPack(plugin,"messages/"+language+".yml");

                        if(pack == null){
                            messageProvider.importPack(defaultPack);
                        }else{
                            updateMessages(pack,defaultPack);
                            messageProvider.importPack(pack);
                        }
                    }else{
                        for (MessagePack original : result) {
                            MessagePack pack = readPack(plugin,"messages/"+original.getMeta().getLanguage().getCode()+".yml");
                            if(pack == null) pack = readPack(plugin,"messages/"+original.getMeta().getLanguage().getName()+".yml");

                            int updated = 0;
                            if(pack != null) updated += updateMessages(original,pack);
                            updated += updateMessages(original,defaultPack);

                            if(updated > 0){
                                messageProvider.updatePack(original,updated);
                            }
                        }
                    }
                }
                messageProvider.calculateMessages();
            }
        }
    }

    private static MessagePack readPack(Plugin<?> plugin, String location){
        InputStream stream = plugin.getLoader().getClassLoader().getResourceAsStream(location);
        if(stream != null){
            return MessagePack.fromDocument(DocumentFileType.YAML.getReader().read(stream, StandardCharsets.UTF_8));
        }
        return null;
    }

    private static int updateMessages(MessagePack original, MessagePack provided){
        int result = 0;
        for (Map.Entry<String, String> entry : provided.getMessages().entrySet()) {
            boolean updated = original.getMessages().putIfAbsent(entry.getKey(),entry.getValue()) == null;
            if(updated) result++;
        }
        return result;
    }
}
