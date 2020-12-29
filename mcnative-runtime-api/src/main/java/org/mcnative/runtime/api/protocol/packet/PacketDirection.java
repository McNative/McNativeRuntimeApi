/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 15.09.19, 20:20
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

package org.mcnative.runtime.api.protocol.packet;

/**
 * Server -> OUTGOING | Proxy | -> OUTGOING -> Client
 * Server <- INCOMING | Proxy | <- INCOMING <- Client
 *
 * The proxy has always the same direction state. If a server sends a packet to the client
 * and the proxy reads this packet the state is still OUTGOING.
 */
public enum PacketDirection {

    INCOMING(),
    OUTGOING()

}
