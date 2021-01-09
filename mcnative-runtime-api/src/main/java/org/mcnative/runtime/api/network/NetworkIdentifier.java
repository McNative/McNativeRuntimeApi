/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.01.20, 20:23
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

package org.mcnative.runtime.api.network;

import java.util.UUID;

public class NetworkIdentifier {

    public static String SERVER_GROUP_DELIMITER = "-";


    public static final NetworkIdentifier UNKNOWN = new NetworkIdentifier("Unknown",new UUID(-1,-1));

    public static final NetworkIdentifier BROADCAST = new NetworkIdentifier("Network Broadcast",new UUID(0,0));

    public static final NetworkIdentifier BROADCAST_PROXY = new NetworkIdentifier("Network Proxy Broadcast",new UUID(0,1));

    public static final NetworkIdentifier BROADCAST_SERVER = new NetworkIdentifier("Network Server Broadcast",new UUID(0,2));

    public static final NetworkIdentifier MANAGER_DATA_REQUEST = new NetworkIdentifier("Manager Data Request",new UUID(0,3));

    private final String name;
    private final UUID uniqueId;
    private final String group;

    public NetworkIdentifier(String name, UUID uniqueId, String group) {
        this.name = name;
        this.uniqueId = uniqueId;
        this.group = group;
    }

    public NetworkIdentifier(String name, UUID uniqueId) {
        this(name, uniqueId, parseGroup(name));
    }

    public String getName() {
        return name;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public String getGroup() {
        return group;
    }

    private static String parseGroup(String name) {
        int index = name.lastIndexOf(SERVER_GROUP_DELIMITER);
        if(index == -1) {
            return name;
        }
        return name.substring(0, name.lastIndexOf(SERVER_GROUP_DELIMITER)-1);
    }
}
