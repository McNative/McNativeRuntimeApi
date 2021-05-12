/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 10.08.19, 17:14
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

import java.util.function.Function;

public class DynamicTextSidebarModule extends AbstractSidebarModule{

    private final String prefix, value;
    private final Function<OnlineMinecraftPlayer,Object> valueGetter;

    public DynamicTextSidebarModule(String name,String prefix, Function<OnlineMinecraftPlayer, Object> valueGetter) {
        super(name);
        this.valueGetter = valueGetter;
        if(prefix.length() > 16){
            this.prefix = prefix.substring(0,16);
            this.value = prefix.substring(16);
        }else{
            this.prefix = prefix;
            this.value = null;
        }
    }

    @Override
    public SidebarEntry render(OnlineMinecraftPlayer player) {
        return new DynamicEntry(prefix,value,valueGetter.apply(player).toString());
    }

    private static class DynamicEntry implements SidebarEntry{

        private final String prefix, value, suffix;

        public DynamicEntry(String prefix, String value, String suffix) {
            this.prefix = prefix;
            this.value = value;
            this.suffix = suffix;
        }

        @Override
        public String getPrefix() {
            return prefix;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String getSuffix() {
            return suffix;
        }
    }
}
