/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 13.04.20, 20:37
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

package org.mcnative.bukkit;

import net.pretronic.libraries.event.Listener;
import org.bukkit.Bukkit;
import org.mcnative.common.McNative;
import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.protocol.packet.type.scoreboard.MinecraftScoreboardTeamsPacket;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.service.event.player.MinecraftPlayerJoinEvent;

public class Test {

    @Listener
    public void onLogin(MinecraftPlayerJoinEvent event) {
        System.out.println("login");

        Bukkit.getScheduler().runTaskLater(McNativeLauncher.getPlugin(), ()-> {
            System.out.println("send");
            execute();
        }, 20*10);
    }

    public void execute(){
        //OnlineMinecraftPlayer dkrieger = McNative.getInstance().getLocal().getOnlinePlayer("Dkrieger");
        //OnlineMinecraftPlayer dkriescitv = McNative.getInstance().getLocal().getOnlinePlayer("DkriesciTV");
        OnlineMinecraftPlayer fridious = McNative.getInstance().getLocal().getOnlinePlayer("Dkriegser");

        System.out.println("XXXX");

        sendPrefix(fridious, fridious,"Admin:",TextColor.DARK_RED);
      //  sendPrefix(dkrieger,fridious,"Admin : ",TextColor.DARK_RED);
      //  sendPrefix(dkrieger,dkriescitv,"Player : ",TextColor.GRAY);



       // sendPrefix(fridious,dkrieger,"Admin : ",TextColor.DARK_RED);
      //  sendPrefix(fridious,fridious,"Admin : ",TextColor.DARK_RED);
     //   sendPrefix(fridious,dkriescitv,"Player : ",TextColor.GRAY);


       // sendPrefix(dkriescitv,dkrieger,"Admin : ",TextColor.DARK_RED);
     //   sendPrefix(dkriescitv,fridious,"Admin (H) : ",TextColor.DARK_RED);
        //sendPrefix(dkriescitv,dkriescitv,"Player : ",TextColor.GRAY);


        /*
        sendVisibility(dkrieger,fridious,true);
        sendVisibility(dkrieger,dkriescitv,false);


        sendVisibility(fridious,fridious,true);
        sendVisibility(fridious,dkriescitv,true);


        sendVisibility(dkriescitv,fridious,false);
        sendVisibility(dkriescitv,dkriescitv,false);
         */
    }

    public void sendPrefix(OnlineMinecraftPlayer player, OnlineMinecraftPlayer target, String prefix,TextColor color){
        MinecraftScoreboardTeamsPacket packet = new MinecraftScoreboardTeamsPacket();
        packet.setAction(MinecraftScoreboardTeamsPacket.Action.CREATE);
        packet.setName("XX"+target.getName());

        packet.setDisplayName(Text.of("d"));
        packet.setPrefix(Text.of(prefix,color));
        packet.setSuffix(Text.of("s"));

        packet.setColor(color);
        packet.setEntities(new String[]{target.getName()});
        player.sendPacket(packet);
    }

    public void sendVisibility(OnlineMinecraftPlayer player, OnlineMinecraftPlayer target,boolean canSee){
        MinecraftScoreboardTeamsPacket packet = new MinecraftScoreboardTeamsPacket();
        packet.setName("VEW-"+target.getName());
        packet.setAction(MinecraftScoreboardTeamsPacket.Action.CREATE);
        packet.setNameTagVisibility(canSee
                ? MinecraftScoreboardTeamsPacket.OptionStatus.FOR_OWN_TEAM
                : MinecraftScoreboardTeamsPacket.OptionStatus.FOR_OTHER_TEAMS);
        packet.setEntities(new String[]{target.getName()});
        player.sendPacket(packet);
    }

}
