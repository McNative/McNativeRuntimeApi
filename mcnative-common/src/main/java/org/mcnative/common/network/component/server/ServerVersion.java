/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 29.12.19, 18:43
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

package org.mcnative.common.network.component.server;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.adapter.DocumentAdapter;
import net.pretronic.libraries.document.entry.DocumentBase;
import net.pretronic.libraries.document.entry.DocumentEntry;
import net.pretronic.libraries.utility.GeneralUtil;
import net.pretronic.libraries.utility.reflect.TypeReference;
import org.mcnative.common.protocol.MinecraftEdition;
import org.mcnative.common.protocol.MinecraftProtocolVersion;

public class ServerVersion {

    private String name;
    private MinecraftProtocolVersion protocol;

    public ServerVersion(String name, MinecraftProtocolVersion protocol) {
        this.name = name;
        this.protocol = protocol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public MinecraftProtocolVersion getProtocol() {
        return protocol;
    }

    public void setProtocol(MinecraftProtocolVersion protocol) {
        this.protocol = protocol;
    }

    public static class Adapter implements DocumentAdapter<ServerVersion> {

        private final MinecraftEdition edition;

        public Adapter(MinecraftEdition edition) {
            this.edition = edition;
        }

        @Override
        public ServerVersion read(DocumentBase document, TypeReference<ServerVersion> reference) {
            if(document.isPrimitive()){
                String version = document.toPrimitive().getAsString();
                String[] data = version.split("#");
                if(data.length != 2 && GeneralUtil.isNaturalNumber(data[1])){
                    return new ServerVersion(data[0],MinecraftProtocolVersion.of(edition,Integer.parseInt(data[1])));
                }else throw new IllegalArgumentException("Invalid version string");
            }else{
                String name = document.toNode().getString("name");
                int protocol = document.toNode().getInt("protocol");
                return new ServerVersion(name,MinecraftProtocolVersion.of(edition,protocol));
            }
        }

        @Override
        public DocumentEntry write(String key, ServerVersion version) {
            return Document.newDocument()
                    .set("name",version.getName())
                    .set("protocol",version.getProtocol().getNumber());
        }
    }
}
