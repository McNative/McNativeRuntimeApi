/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.08.19, 18:59
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

package org.mcnative.wrapper.bungeecord;

import net.md_5.bungee.api.plugin.Plugin;
import org.mcnative.wrapper.guest.GuestPluginExecutor;
import org.mcnative.wrapper.loader.McNativeLoader;

import java.util.logging.Level;

public class BungeeCordMcNativePluginBootstrap extends Plugin {

    private GuestPluginExecutor executor;

    @Override
    public void onLoad() {
        try{
            McNativeLoader.install("BungeeCord");

            this.executor = null;//new GuestPluginExecutor(new TestPlugin());
            this.executor.loadGuestPlugin();
        }catch (Exception exception){
            getLogger().log(Level.SEVERE,String.format("Could not load %s v%s by %s"
                    ,getDescription().getName()
                    ,getDescription().getVersion()
                    ,getDescription().getAuthor())
                    ,exception);

            getProxy().getPluginManager().unregisterCommands(this);
            getProxy().getPluginManager().unregisterListeners(this);
        }
    }

    @Override
    public void onEnable() {
        if(this.executor != null) this.executor.enableGuestPlugin();
    }

    @Override
    public void onDisable() {
        if(this.executor != null) this.executor.disableGuestPlugin();
    }
}
