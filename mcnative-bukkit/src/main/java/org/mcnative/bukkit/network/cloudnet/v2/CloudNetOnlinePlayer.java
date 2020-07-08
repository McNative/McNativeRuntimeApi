/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 26.06.20, 21:40
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

package org.mcnative.bukkit.network.cloudnet.v2;

import de.dytanic.cloudnet.lib.player.CloudPlayer;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.common.McNative;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.ProxyServer;
import org.mcnative.common.network.component.server.ServerConnectReason;
import org.mcnative.common.network.component.server.ServerConnectResult;
import org.mcnative.common.player.DeviceInfo;
import org.mcnative.common.player.OfflineMinecraftPlayer;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.Title;
import org.mcnative.common.player.chat.ChatPosition;
import org.mcnative.common.player.sound.Instrument;
import org.mcnative.common.player.sound.Note;
import org.mcnative.common.player.sound.Sound;
import org.mcnative.common.player.sound.SoundCategory;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.support.ProtocolCheck;
import org.mcnative.common.text.components.MessageComponent;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class CloudNetOnlinePlayer extends OfflineMinecraftPlayer implements OnlineMinecraftPlayer {

    private final CloudPlayer player;

    public CloudNetOnlinePlayer(CloudPlayer player) {
        super(null);
        this.player = player;
    }

    @Override
    public InetSocketAddress getAddress() {
        return new InetSocketAddress(player.getPlayerConnection().getHost(),player.getPlayerConnection().getPort());
    }

    @Override
    public DeviceInfo getDevice() {
        return DeviceInfo.JAVA;
    }

    @Override
    public boolean isOnlineMode() {
        return player.getPlayerConnection().isOnlineMode();
    }

    @Override
    public int getPing() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CompletableFuture<Integer> getPingAsync() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProxyServer getProxy() {
        return McNative.getInstance().getNetwork().getProxy(player.getProxy());
    }

    @Override
    public MinecraftServer getServer() {
        return McNative.getInstance().getNetwork().getServer(player.getServer());
    }

    @Override
    public void connect(MinecraftServer target, ServerConnectReason reason) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CompletableFuture<ServerConnectResult> connectAsync(MinecraftServer target, ServerConnectReason reason) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void kick(MessageComponent<?> message, VariableSet variables) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void performCommand(String command) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void chat(String message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendMessage(ChatPosition position, MessageComponent<?> component, VariableSet variables) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendActionbar(MessageComponent<?> message, VariableSet variables) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendActionbar(MessageComponent<?> message, VariableSet variables, long staySeconds) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendTitle(Title title) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resetTitle() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendPacket(MinecraftPacket packet) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void playNote(Instrument instrument, Note note) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void playSound(Sound sound, SoundCategory category, float volume, float pitch) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void stopSound(Sound sound) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void stopSound(String sound, SoundCategory category) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void check(Consumer<ProtocolCheck> checker) {
        throw new UnsupportedOperationException();
    }
}
