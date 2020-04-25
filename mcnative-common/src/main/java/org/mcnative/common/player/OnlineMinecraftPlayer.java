/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:44
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

package org.mcnative.common.player;

import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.Textable;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.connection.PendingConnection;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.ProxyServer;
import org.mcnative.common.network.component.server.ServerConnectReason;
import org.mcnative.common.network.component.server.ServerConnectResult;
import org.mcnative.common.player.chat.ChatPosition;
import org.mcnative.common.player.sound.Instrument;
import org.mcnative.common.player.sound.Note;
import org.mcnative.common.player.sound.Sound;
import org.mcnative.common.player.sound.SoundCategory;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.support.ProtocolCheck;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageComponent;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;


public interface OnlineMinecraftPlayer extends MinecraftPlayer, CommandSender {

    PendingConnection getConnection();

    InetSocketAddress getVirtualHost();

    InetSocketAddress getAddress();

    DeviceInfo getDevice();

    boolean isOnlineMode();

    PlayerSettings getSettings();

    int getPing();


    ProxyServer getProxy();

    MinecraftServer getServer();

    default void connect(MinecraftServer target){
        connect(target, ServerConnectReason.API);
    }

    void connect(MinecraftServer target, ServerConnectReason reason);

    default CompletableFuture<ServerConnectResult> connectAsync(MinecraftServer target){
        return connectAsync(target, ServerConnectReason.API);
    }

    CompletableFuture<ServerConnectResult> connectAsync(MinecraftServer target, ServerConnectReason reason);

    default void kick(){
        kick("Kicked by an operator.");
    }

    default void kick(String reason){
        kick(Text.of(reason));
    }

    default void kick(MessageComponent<?> message){
        kick(message,VariableSet.newEmptySet());
    }

    void kick(MessageComponent<?> message,VariableSet variables);


    void performCommand(String command);

    void chat(String message);

    default void sendMessage(String text){
        sendMessage(Text.of(text));
    }

    default void sendMessage(MessageComponent<?> component){
        sendMessage(component,VariableSet.newEmptySet());
    }

    default void sendMessage(MessageComponent<?> component, VariableSet variables){
        sendMessage(ChatPosition.PLAYER_CHAT,component,variables);
    }

    default void sendSystemMessage(MessageComponent<?> component){
        sendSystemMessage(component,VariableSet.newEmptySet());
    }

    default void sendSystemMessage(MessageComponent<?> component, VariableSet variables){
        sendMessage(ChatPosition.SYSTEM_CHAT,component,variables);
    }

    default void sendMessage(ChatPosition position,MessageComponent<?> component){
        sendMessage(position, component,VariableSet.newEmptySet());
    }

    void sendMessage(ChatPosition position,MessageComponent<?> component, VariableSet variables);


    default void sendActionbar(MessageComponent<?> message){
        sendActionbar(message,VariableSet.newEmptySet());
    }

    void sendActionbar(MessageComponent<?> message,VariableSet variables);

    default void sendActionbar(MessageComponent<?> message,long stayTime){
        sendActionbar(message,VariableSet.newEmptySet(), stayTime);
    }

    void sendActionbar(MessageComponent<?> message,VariableSet variables,long staySeconds);



    default void sendTitle(String title, String subTitle, int stayTime){
        sendTitle(Text.of(title),Text.of(subTitle),stayTime);
    }

    default void sendTitle(MessageComponent<?> title, MessageComponent<?> subTitle, int stayTime){
        sendTitle(title, subTitle, stayTime,VariableSet.newEmptySet());
    }

    default void sendTitle(MessageComponent<?> title, MessageComponent<?> subTitle, int stayTime,VariableSet variables){
        sendTitle(Title.newTitle().title(title).title(subTitle).stayTime(stayTime).variables(variables));
    }

    void sendTitle(Title title);

    void resetTitle();



    void sendPacket(MinecraftPacket packet);


    void playNote(Instrument instrument, Note note);

    void playSound(Sound sound, SoundCategory category, float volume, float pitch);

    void stopSound(Sound sound);

    void stopSound(String sound, SoundCategory category);


    void check(Consumer<ProtocolCheck> checker);

    default void sendMessage(Textable textable) {
        sendMessage(textable,VariableSet.newEmptySet());
    }

    default void sendMessage(Textable textable,VariableSet variables) {
        if(textable instanceof MessageComponent){
            sendMessage((MessageComponent<?>)textable,variables);
        }else{
            this.sendMessage(textable.toText(variables));
        }
    }

    @Override
    default OnlineMinecraftPlayer getAsOnlinePlayer() {
        return this;
    }
}
