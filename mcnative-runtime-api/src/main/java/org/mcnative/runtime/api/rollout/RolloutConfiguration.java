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

package org.mcnative.runtime.api.rollout;

import java.util.Collection;
import java.util.Map;

public interface RolloutConfiguration {

    Map<String,RolloutProfile> getProfiles();

    Collection<PluginEntry> getPlugins();

    RolloutProfile getProfile(String name);

    class PluginEntry {

        private String pluginName;
        private String profile;

        public PluginEntry() {}

        public PluginEntry(String pluginName, String profile) {
            this.pluginName = pluginName;
            this.profile = profile;
        }

        public String getPluginName() {
            return pluginName;
        }

        public String getProfile() {
            return profile;
        }

        public void setPluginName(String pluginName) {
            this.pluginName = pluginName;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }
    }
}
