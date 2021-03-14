/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 13.10.19, 11:07
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

package org.mcnative.runtime.api.connection;

import io.netty.buffer.ByteBuf;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;
import org.mcnative.runtime.api.text.Text;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public interface MinecraftConnection {

    String getName();

    MinecraftProtocolVersion getProtocolVersion();

    ConnectionState getState();

    InetSocketAddress getAddress();

    boolean isConnected();


    default void disconnect(String message){
        disconnect(Text.of(message));
    }

    default void disconnect(MessageComponent<?> reason){
        disconnect(reason,VariableSet.createEmpty());
    }

    void disconnect(MessageComponent<?> reason, VariableSet variables);


    void sendPacket(MinecraftPacket packet);

    void sendLocalLoopPacket(MinecraftPacket packet);

    void sendRawPacket(ByteBuf buffer);


    OutputStream sendData(String channel);

    void sendData(String channel, byte[] output);
}
