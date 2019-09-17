/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.08.19, 18:26
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

package org.mcnative.bungeecord.internal.listeners;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.mcnative.bungeecord.BungeeCordService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ServerListener implements Listener {

    private BungeeCordService service;

    public ServerListener(BungeeCordService service) {
        this.service = service;
    }

    @EventHandler
    public void onServerPluginMessageReceive(PluginMessageEvent event){//Todo implement transaction id
        for(BungeeCordService.PluginMessageListenerEntry entry : service.getPluginMessageListeners()) {
            if(entry.name.equals(event.getTag())){
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                entry.listener.onMessageReceive(null,null,new ByteArrayInputStream(event.getData()),output);
                if(output.size() > 0){
                    if(event.getSender() instanceof Server){
                        ((Server) event.getSender()).sendData(event.getTag(),output.toByteArray());
                    }else if(event.getSender() instanceof ProxiedPlayer){
                        ((ProxiedPlayer) event.getSender()).sendData(event.getTag(),output.toByteArray());
                    }
                }
                try {
                    output.close();
                } catch (IOException ignored) {}
                return;
            }
        }
    }

}
