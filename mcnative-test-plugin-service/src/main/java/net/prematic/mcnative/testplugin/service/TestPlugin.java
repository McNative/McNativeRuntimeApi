/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 02.08.19 20:42
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

package net.prematic.mcnative.testplugin.service;

import net.prematic.libraries.plugin.lifecycle.Lifecycle;
import net.prematic.libraries.plugin.lifecycle.LifecycleState;
import net.prematic.mcnative.common.plugin.MinecraftPlugin;
import net.prematic.mcnative.testplugin.service.listener.PlayerLoginListener;

public class TestPlugin extends MinecraftPlugin {

    @Lifecycle(state= LifecycleState.BOOTSTRAP)
    public void onEnable(LifecycleState state){
        System.out.println("McNative plugin started");



        getRuntime().getEventManager().registerListener(this,new PlayerLoginListener());
    }

}
