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

package org.mcnative.common.connection;

import org.mcnative.common.MinecraftOutputStream;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.common.text.variable.VariableSet;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.function.Consumer;

public interface MinecraftConnection {

    String getName();

    MinecraftProtocolVersion getProtocolVersion();

    ConnectionState getState();

    InetSocketAddress getAddress();

    boolean isConnected();


    default void disconnect(String message){
        disconnect(Text.of(message));
    }

    default void disconnect(MessageComponent reason){
        disconnect(reason,VariableSet.newEmptySet());
    }

    void disconnect(MessageComponent reason, VariableSet variables);


    void sendPacket(MinecraftPacket packet);

    void sendLocalLoopPacket(MinecraftPacket packet);


    default OutputStream sendData(String channel){
        return new MinecraftOutputStream(channel,this);
    }//requires flush and close

    default void sendData(String channel,Consumer<OutputStream> output){
        MinecraftOutputStream stream = new MinecraftOutputStream(channel,this);
        output.accept(stream);
        stream.flush();
    }

    void sendData(String channel, byte[] output);


    InputStream sendDataQuery(String channel, byte[] output);

    InputStream sendDataQuery(String channel,Consumer<OutputStream> output);//Always receives same id (Multiple flushable)

}
