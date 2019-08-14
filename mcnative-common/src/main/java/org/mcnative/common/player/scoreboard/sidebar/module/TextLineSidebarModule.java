/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:45
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

package org.mcnative.common.player.scoreboard.sidebar.module;

import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.scoreboard.sidebar.SidebarEntry;

public class TextLineSidebarModule extends AbstractSidebarModule {

    private SidebarEntry entry;

    public TextLineSidebarModule(String name, String text) {
        super(name);
        setText(text);
    }

    public void setText(String text){
        this.entry = new TextEntry(text);
    }

    @Override
    public SidebarEntry render(OnlineMinecraftPlayer player) {
        return entry;
    }

    private static class TextEntry implements SidebarEntry{

        private final String[] text;

        public TextEntry(String text){
            this.text = new String[3];
            if(text.length() > 16){
                if(text.length() > 32){
                    this.text[0] = text.substring(0,16);
                    this.text[1] = text.substring(16,32);
                    this.text[2] = text.substring(32);
                }else{
                    this.text[0] = text.substring(0,16);
                    this.text[2] = text.substring(16);
                }
            }else this.text[0] = text;
        }

        @Override
        public String getPrefix() {
            return text[0];
        }

        @Override
        public String getValue() {
            return text[1];
        }

        @Override
        public String getSuffix() {
            return text[2];
        }
    }
}
