/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 15:53
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

package org.mcnative.common;

import net.prematic.libraries.utility.io.IORuntimeException;
import org.mcnative.common.text.TextComponent;
import org.mcnative.common.protocol.packet.MinecraftPacket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.function.Consumer;

public interface MinecraftConnection {

    InetSocketAddress getAddress();

    boolean isConnected();


    void disconnect(String message);

    void disconnect(TextComponent... reason);


    void sendPacket(MinecraftPacket packet);

    void sendPacketAsync(MinecraftPacket packet);


    default OutputStream sendData(String channel){
        return new MinecraftOutputStream(channel,this);
    }//requires flush and close

    default void sendData(String channel,Consumer<OutputStream> output){
        MinecraftOutputStream stream = new MinecraftOutputStream(channel,this);
        output.accept(stream);
        try {
            stream.flush();
        } catch (IOException exception) {
            throw new IORuntimeException(exception);
        }
    }

    void sendData(String channel, byte[] output);


    InputStream sendDataQuery(String channel, byte[] output);

    InputStream sendDataQuery(String channel,Consumer<OutputStream> output);//Always receives same id (Multiple flushable)






}
