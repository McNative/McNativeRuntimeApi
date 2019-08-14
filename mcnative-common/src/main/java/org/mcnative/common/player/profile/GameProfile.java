/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 11.08.19, 19:14
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

package org.mcnative.common.player.profile;

import net.prematic.libraries.document.Document;
import net.prematic.libraries.utility.Iterators;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class GameProfile {

    private final UUID uniqueId;
    private final String name;
    private final List<Property> properties;

    public GameProfile(UUID uniqueId, String name) {
        this(uniqueId, name, new ArrayList<>());
    }

    public GameProfile(UUID uniqueId, String name, List<Property> properties) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.properties = properties;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public Property getProperty(String name){
        return Iterators.findOne(this.properties, property -> property.name.equals(name));
    }

    public String getTextures(){
        Property textures = getProperty("textures");
        return textures.getValue();
    }

    public static GameProfile fromJson(String json){
        return Document.newDocument(json).getAsObject(GameProfile.class);
    }

    public static final class Property {

        private final String name;
        private final String value;
        private final String signature;

        public Property(String name, String value, String signature) {
            this.name = Objects.requireNonNull(name, "name");
            this.value = Objects.requireNonNull(value, "value");
            this.signature = Objects.requireNonNull(signature, "signature");
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public String getSignature() {
            return signature;
        }

        @Override
        public String toString() {
            return "Property{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    ", signature='" + signature + '\'' +
                    '}';
        }
    }
}
