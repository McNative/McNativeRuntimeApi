/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.07.20, 12:24
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

package org.mcnative.runtime.api.loader;

import java.util.UUID;

public class ResourceConfig {

    private final String name;
    private final UUID id;
    private final String qualifier;
    private final String version;

    public ResourceConfig(String name, UUID id, String qualifier, String version) {
        this.name = name;
        this.id = id;
        this.qualifier = qualifier;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public String getQualifier() {
        return qualifier;
    }

    public String getVersion() {
        return version;
    }

    public boolean isAutomatically(){
        return version.equalsIgnoreCase("LATEST");
    }
}
