/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 28.12.19, 21:28
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

package org.mcnative.common.protocol.packet;

import net.pretronic.libraries.event.Cancellable;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.event.MinecraftEvent;
import org.mcnative.common.protocol.Endpoint;
import org.mcnative.common.protocol.MinecraftProtocolVersion;

/**
 * A @{@link MinecraftPacketListener} is always called with this event, it contains all information about
 * the received packet and connection.
 */
public class MinecraftPacketEvent implements MinecraftEvent, Cancellable {

    private final Endpoint endpoint;
    private final PacketDirection direction;
    private final MinecraftConnection connection;

    private MinecraftPacket packet;
    private boolean rewrite;
    private boolean cancelled;

    public MinecraftPacketEvent(Endpoint endpoint, PacketDirection direction, MinecraftConnection connection, MinecraftPacket packet) {
        this.endpoint = endpoint;
        this.direction = direction;
        this.connection = connection;
        this.packet = packet;

        this.rewrite = false;
        this.cancelled = false;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public PacketDirection getDirection() {
        return direction;
    }

    public MinecraftConnection getConnection() {
        return connection;
    }

    public MinecraftProtocolVersion getVersion() {
        return connection.getProtocolVersion();
    }

    public MinecraftPacket getPacket() {
        return packet;
    }

    public boolean isPacket(Class<?> packetClass){
        return packetClass.isAssignableFrom(packet.getClass());
    }

    @SuppressWarnings("unchecked")
    public <T extends MinecraftPacket> T getPacket(Class<T> packetClass) {
        return (T) packet;
    }

    public void setPacket(MinecraftPacket packet) {
        this.packet = packet;
    }

    public boolean isRewrite() {
        return rewrite;
    }

    public void setRewrite(boolean rewrite) {
        this.rewrite = rewrite;
    }

    /**
     * Mark the packet to be written back to the buffer
     */
    public void rewrite(){
        setRewrite(true);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
