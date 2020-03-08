/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 22.01.20, 20:25
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

import net.prematic.libraries.document.Document;
import net.prematic.libraries.document.DocumentRegistry;
import net.prematic.libraries.document.type.DocumentFileType;
import net.prematic.libraries.message.MessagePack;
import net.prematic.libraries.message.MessageProvider;
import net.prematic.libraries.message.bml.variable.VariableSet;
import net.prematic.libraries.message.language.Language;
import net.prematic.libraries.message.language.LanguageAble;
import net.prematic.libraries.message.repository.MessageRepository;
import net.prematic.libraries.plugin.Plugin;
import net.prematic.libraries.utility.Iterators;
import net.prematic.libraries.utility.Validate;
import net.prematic.libraries.utility.map.caseintensive.CaseIntensiveHashMap;
import org.mcnative.common.McNative;
import org.mcnative.common.plugin.MinecraftPlugin;
import org.mcnative.common.text.Text;

import java.io.File;
import java.util.*;

public class DefaultMessageProvider implements MessageProvider {

    private static final String MESSAGE_NOT_FOUND = "The message %key% was not found.";

    private final List<MessagePack> packs;
    private final Map<String,Map<Language,String>> messages;

    private Language defaultLanguage;

    public DefaultMessageProvider() {
        this(Language.ENGLISH);
    }

    public DefaultMessageProvider(Language defaultLanguage) {
        Validate.notNull(defaultLanguage);
        this.packs = new ArrayList<>();
        this.messages = new CaseIntensiveHashMap<>();
        this.defaultLanguage = defaultLanguage;
    }

    @Override
    public Language getDefaultLanguage() {
        return defaultLanguage;
    }

    @Override
    public void setDefaultLanguage(Language language) {
        Validate.notNull(language);
        this.defaultLanguage = language;
    }

    @Override
    public Collection<MessageRepository> getRepositories() {
        return null;
    }

    @Override
    public MessageRepository getRepository(String s) {
        return null;
    }

    @Override
    public MessageRepository addRepository(String s, String s1) {
        return null;
    }

    @Override
    public Collection<MessagePack> getPacks() {
        return packs;
    }

    @Override
    public Collection<MessagePack> getPacks(String module) {
        return Iterators.filter(this.packs, pack -> pack.getMeta().getModule().equalsIgnoreCase(module));
    }

    @Override
    public Collection<MessagePack> getPacks(Language language) {
        return Iterators.filter(this.packs, pack -> pack.getMeta().getLanguage().equals(language));
    }

    @Override
    public MessagePack importPack(Document document) {
        MessagePack pack = MessagePack.fromDocument(document);
        MinecraftPlugin owner = findModuleOwner(pack.getMeta().getModule());
        if(owner == null) throw new IllegalArgumentException("Packet owner is missing");

        addPack(pack);

        File folder = new File(owner.getDataFolder(),"messages/");
        folder.mkdirs();
        String name = pack.getMeta().getName()+"-"+pack.getMeta().getLanguage().getCode()+".yml";
        name = name.toLowerCase().replace(" ","-");
        File location = new File(folder,name);
        DocumentFileType.YAML.getWriter().write(location,document);
        return pack;
    }

    @Override
    public MessagePack getPack(String name) {
        return Iterators.findOne(this.packs, pack -> pack.getMeta().getName().equals(name));
    }

    @Override
    public void addPack(MessagePack pack) {
        if(getPack(pack.getMeta().getName()) != null) throw new IllegalArgumentException("A pack with the name "+pack.getMeta().getName()+" is already available");
        this.packs.add(pack);
    }

    @Override
    public MessagePack addPack(Document document) {
        MessagePack pack = MessagePack.fromDocument(document);
        addPack(pack);
        return pack;
    }

    //@Todo update from repository
    @Override
    public List<MessagePack> loadPacks(String module) {
        Validate.notNull(module);
        MinecraftPlugin plugin = findModuleOwner(module);
        if(plugin != null){
            File folder = plugin.getDataFolder();
            if(folder != null){
                File messageFolder = new File(folder,"messages/");
                if(messageFolder.exists()){
                    File[] content = messageFolder.listFiles();
                    if(content != null){
                        List<MessagePack> result = new ArrayList<>();
                        for (File file : content) {
                            DocumentFileType type = DocumentRegistry.findType(file);
                            if(type != null){
                                try{
                                    MessagePack pack = addPack(type.getReader().read(file));
                                    result.add(pack);
                                    plugin.getLogger().info("(Message-Provider) Loaded message pack {}",pack.getMeta().getName());
                                }catch (Exception exception){
                                    plugin.getLogger().info("(Message-Provider) Could not load message pack {}",file.getName());
                                    plugin.getLogger().error(exception);
                                }
                            }
                        }
                        return result;
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private MinecraftPlugin findModuleOwner(String module){
        for (Plugin<?> plugin : McNative.getInstance().getPluginManager().getPlugins()) {
            if (plugin instanceof MinecraftPlugin
                    && plugin.getDescription().getMessageModule() != null
                    && plugin.getDescription().getMessageModule().equalsIgnoreCase(module)) {
                return (MinecraftPlugin) plugin;
            }
        }
        return null;
    }

    @Override
    public String getMessage(String key) {
        return getMessage(key, (Language) null);
    }

    @Override
    public String getMessage(String key, Language language) {
        if(language == null) language = defaultLanguage;
        Map<Language,String> node = this.messages.get(key);
        if(node != null && !node.isEmpty()){
            String result = node.get(language);
            if(result != null) return result;
            result = node.get(defaultLanguage);
            if(result != null) return result;
            return node.entrySet().iterator().next().getValue();
        }
        return MESSAGE_NOT_FOUND.replace("%key%",key);
    }

    @Override
    public String getMessage(String key, LanguageAble obj) {
        return getMessage(key,obj != null ? obj.getLanguage() : null);
    }

    @Override
    public String buildMessage(String key, VariableSet variables) {
        return buildMessage(key,variables,null);
    }

    @Override
    public String buildMessage(String key, Language language) {
        return buildMessage(key,VariableSet.newEmptySet(),language);
    }

    @Override
    public String buildMessage(String key, VariableSet variables, Language language) {
        return variables.replace(getMessage(key,language));
    }

    @Override
    public void calculateMessages() {
        this.messages.clear();
        for (MessagePack pack : this.packs) {
            pack.getMessages().forEach((key, message) -> {
                Map<Language,String> node = messages.computeIfAbsent(key, key1 -> new HashMap<>());
                node.put(pack.getMeta().getLanguage(),replaceInternalVariables(pack,message));
            });
        }
    }

    private static String replaceInternalVariables(MessagePack pack,String message){
        char[] content = message.toCharArray();
        StringBuilder builder = new StringBuilder(content.length);
        int start = -1;
        for (int i = 0; i < content.length; i++) {
            if(content[i] == '$'){
                start = i;
            }else if(content[i] == '{' && i != 0 && content[i-1] == '$'){
                start = i;
            }else if(content[i] == '}' && start != -1){
                String key = message.substring(start+1,i);

                String subMessage = pack.getMessage(key);

                builder.append(subMessage == null ? "{NOT FOUND}" : replaceInternalVariables(pack,subMessage));
                start = -1;
            }else if(start == -1){
                builder.append(content[i]);
            }
        }
        return Text.translateAlternateColorCodes('&',builder.toString());
    }
}
