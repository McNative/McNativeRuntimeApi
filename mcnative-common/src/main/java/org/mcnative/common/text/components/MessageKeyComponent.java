/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.09.19, 20:24
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

package org.mcnative.common.text.components;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.message.MessageProvider;
import net.pretronic.libraries.message.bml.Message;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.message.language.Language;
import org.mcnative.common.McNative;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.connection.PendingConnection;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.serviceprovider.message.builder.MinecraftBuildContext;
import org.mcnative.common.serviceprovider.message.builder.TextBuildType;
import org.mcnative.common.text.format.TextColor;

import java.util.Collection;

public class MessageKeyComponent implements MessageComponent<MessageKeyComponent>{

    private String key;
    private Message message;

    public MessageKeyComponent() {}

    public MessageKeyComponent(String key) {
        this.key = key;
    }

    public MessageKeyComponent(Message message) {
        this.key = "custom";
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
        this.message = null;
    }

    @Override
    public Collection<MessageComponent<?>> getExtras() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MessageKeyComponent addExtra(MessageComponent<?> component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MessageKeyComponent removeExtra(MessageComponent<?> component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void toPlainText(StringBuilder builder, VariableSet variables, Language language) {
        String result = McNative.getInstance().getRegistry().getService(MessageProvider.class).buildMessage(key,variables,language);
        builder.append(result.replace("\\n","\n"));
    }

    @Override
    public void compileToLegacy(StringBuilder builder, MinecraftConnection connection, VariableSet variables, Language language) {
        try{
            if(message == null){
                message = McNative.getInstance().getRegistry().getService(MessageProvider.class).getMessage(this.key, language);
            }
            OnlineMinecraftPlayer player = null;
            if(connection instanceof OnlineMinecraftPlayer){
                player = (OnlineMinecraftPlayer) connection;
            }else if(connection instanceof PendingConnection && ((PendingConnection) connection).isPlayerAvailable()){
                player = ((PendingConnection) connection).getPlayer();
            }
            builder.append(message.build(new MinecraftBuildContext(language,variables,player, TextBuildType.LEGACY)).toString());
        }catch (Exception e){
            e.printStackTrace();
            McNative.getInstance().getLogger().error("[McNative] (Message-Provider) Failed building BML message");
            McNative.getInstance().getLogger().error("[McNative] (Message-Provider) Error: "+e.getMessage());
            builder.append("$4Internal Server Error");
        }
    }

    //@Todo optimize

    @Override
    public Document compile(String key, MinecraftConnection connection, VariableSet variables, Language language) {
        try{
            if(message == null){
                message = McNative.getInstance().getRegistry().getService(MessageProvider.class).getMessage(this.key, language);
            }
            OnlineMinecraftPlayer player = null;
            if(connection instanceof OnlineMinecraftPlayer){
                player = (OnlineMinecraftPlayer) connection;
            }else if(connection instanceof PendingConnection && ((PendingConnection) connection).isPlayerAvailable()){
                player = ((PendingConnection) connection).getPlayer();
            }

            Object result = message.build(new MinecraftBuildContext(language,variables,player, TextBuildType.COMPILE));
            if(result instanceof Document){
                return (Document) result;
            }
            else{
                Document wrapper = Document.newDocument();
                wrapper.set("text","");
                wrapper.set("extra",new Object[]{result});
                return wrapper;
            }
        }catch (Exception e){
            e.printStackTrace();
            McNative.getInstance().getLogger().error("[McNative] (Message-Provider) Failed building BML message");
            McNative.getInstance().getLogger().error("[McNative] (Message-Provider) Error: "+e.getMessage());
        }
        return Document.newDocument().set("color",TextColor.DARK_RED.getName()).set("text","Internal Server Error");
    }

    @Override
    public void decompile(Document data) {
        this.key = data.getString("key");
    }
}
