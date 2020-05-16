/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 16.05.20, 20:42
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

package org.mcnative.bukkit.network.bungeecord;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.Validate;
import net.pretronic.libraries.utility.annonations.Internal;
import net.pretronic.libraries.utility.exception.OperationFailedException;
import org.mcnative.common.McNative;
import org.mcnative.common.network.NetworkIdentifier;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.ProxyServer;
import org.mcnative.common.network.component.server.ServerConnectReason;
import org.mcnative.common.network.component.server.ServerConnectResult;
import org.mcnative.common.player.DeviceInfo;
import org.mcnative.common.player.OfflineMinecraftPlayer;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.Title;
import org.mcnative.common.player.chat.ChatPosition;
import org.mcnative.common.player.data.MinecraftPlayerData;
import org.mcnative.common.player.sound.Instrument;
import org.mcnative.common.player.sound.Note;
import org.mcnative.common.player.sound.Sound;
import org.mcnative.common.player.sound.SoundCategory;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.support.ProtocolCheck;
import org.mcnative.common.text.components.MessageComponent;

import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class BungeeCordOnlinePlayer extends OfflineMinecraftPlayer implements OnlineMinecraftPlayer {

    private final UUID uniqueId;
    private final String name;
    private final InetSocketAddress address;
    private final boolean onlineMode;
    private MinecraftServer server;

    public BungeeCordOnlinePlayer(MinecraftPlayerData data,UUID uniqueId,String name,InetSocketAddress address,boolean onlineMode,MinecraftServer server) {
        super(data);
        this.uniqueId = uniqueId;
        this.name = name;
        this.address = address;
        this.server = server;
        this.onlineMode = onlineMode;
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public OnlineMinecraftPlayer getAsOnlinePlayer() {
        return this;
    }

    @Override
    public boolean isOnline() {
        return onlineMode;
    }

    @Override
    public InetSocketAddress getAddress() {
        return address;
    }

    @Override
    public DeviceInfo getDevice() {
        return DeviceInfo.JAVA;//Currently only java clients supported
    }

    @Override
    public boolean isOnlineMode() {
        return false;
    }

    @Override
    public int getPing() {
        try {
            return getPingAsync().get(2, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new OperationFailedException(e);
        }
    }

    @Override
    public CompletableFuture<Integer> getPingAsync() {
        CompletableFuture<Integer> resultFuture = new CompletableFuture<>();
        executePlayerBasedFuture(Document.newDocument()
                .set("action","getPing"))
                .thenAccept(documentEntries -> resultFuture.complete(documentEntries.getInt("ping")));
        return resultFuture;
    }

    @Override
    public ProxyServer getProxy() {//Only one
        throw new UnsupportedOperationException("Currently not supported, implementation in progress");
    }

    @Override
    public MinecraftServer getServer() {//Need update
        return server;
    }

    @Override
    public void connect(MinecraftServer target, ServerConnectReason reason) {
        executePlayerBased(Document.newDocument()
                .set("action","connect")
                .set("target",target.getName())
                .set("reason",reason));
    }

    @Override
    public CompletableFuture<ServerConnectResult> connectAsync(MinecraftServer target, ServerConnectReason reason) {
        CompletableFuture<ServerConnectResult> resultFuture = new CompletableFuture<>();
        executePlayerBasedFuture(Document.newDocument()
                .set("action","connectAsync")
                .set("target",target.getName())
                .set("reason",reason))
                .thenAccept(documentEntries -> resultFuture.complete(documentEntries.getObject("result",ServerConnectResult.class)));
        return resultFuture;
    }

    @Override
    public void kick(MessageComponent<?> message, VariableSet variables) {
        executePlayerBased(Document.newDocument()
                .set("action","kick")
                .set("message",message.compile(variables)));
    }

    @Override
    public void performCommand(String command) {
        executePlayerBased(Document.newDocument()
                .set("action","performCommand")
                .set("command",command));
    }

    @Override
    public void chat(String message) {
        executePlayerBased(Document.newDocument()
                .set("action","chat")
                .set("message",message));
    }

    @Override
    public void sendMessage(ChatPosition position, MessageComponent<?> component, VariableSet variables) {
        executePlayerBased(Document.newDocument()//@Todo find solution for sending component with player
                .set("action","sendMessage")
                .set("position",position.getId())
                .set("text",component.compile(variables)));
    }

    @Override
    public void sendActionbar(MessageComponent<?> message, VariableSet variables) {
        throw new UnsupportedOperationException("Currently not supported, implementation in progress");
    }

    @Override
    public void sendActionbar(MessageComponent<?> message, VariableSet variables, long staySeconds) {
        throw new UnsupportedOperationException("Currently not supported, implementation in progress");
    }

    @Override
    public void sendTitle(Title title) {
        throw new UnsupportedOperationException("Currently not supported, implementation in progress");
    }

    @Override
    public void resetTitle() {
        throw new UnsupportedOperationException("Currently not supported, implementation in progress");
    }

    @Override
    public void sendPacket(MinecraftPacket packet) {
        throw new UnsupportedOperationException("Currently not supported, implementation in progress");
    }

    @Override
    public void playNote(Instrument instrument, Note note) {
        throw new UnsupportedOperationException("Currently not supported, implementation in progress");
    }

    @Override
    public void playSound(Sound sound, SoundCategory category, float volume, float pitch) {
        throw new UnsupportedOperationException("Currently not supported, implementation in progress");
    }

    @Override
    public void stopSound(Sound sound) {
        throw new UnsupportedOperationException("Currently not supported, implementation in progress");
    }

    @Override
    public void stopSound(String sound, SoundCategory category) {
        throw new UnsupportedOperationException("Currently not supported, implementation in progress");
    }

    @Override
    public void check(Consumer<ProtocolCheck> checker) {
        throw new UnsupportedOperationException("Currently not supported, implementation in progress");
    }

    @Internal
    public void setServer(MinecraftServer server){
        Validate.notNull(server);
        this.server = server;
    }

    private void executePlayerBased(Document data){
        data.set("uniqueId",uniqueId);
        McNative.getInstance().getNetwork().getMessenger()
                .sendMessage(NetworkIdentifier.BROADCAST_PROXY,"mcnative_player",data);
    }

    private CompletableFuture<Document> executePlayerBasedFuture(Document data){
        data.set("uniqueId",uniqueId);
        return McNative.getInstance().getNetwork().getMessenger()
                .sendQueryMessageAsync(NetworkIdentifier.BROADCAST_PROXY,"mcnative_player",data);
    }
}
