/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 10.08.19, 17:21
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

package org.mcnative.runtime.api.player.scoreboard.sidebar.module;

import org.mcnative.runtime.api.player.OnlineMinecraftPlayer;
import org.mcnative.runtime.api.player.scoreboard.sidebar.SidebarEntry;

public class SimpleSidebarModule extends AbstractSidebarModule{

    private final SimpleEntry entry;

    public SimpleSidebarModule(String name) {
        super(name);
        this.entry = new SimpleEntry();
    }

    public void setPrefix(String prefix){
        this.entry.setPrefix(prefix);
    }

    public void setValue(String value){
        this.entry.setValue(value);
    }

    public void setSuffix(String suffix){
        this.entry.setSuffix(suffix);
    }

    @Override
    public SidebarEntry render(OnlineMinecraftPlayer player) {
        return this.entry;
    }

    public static class SimpleEntry implements SidebarEntry{

        private String prefix, value, suffix;

        @Override
        public String getPrefix() {
            return this.prefix;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        @Override
        public String getSuffix() {
            return this.suffix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }
    }

}
