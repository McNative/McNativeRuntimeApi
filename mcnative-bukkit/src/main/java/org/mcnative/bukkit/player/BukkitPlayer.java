/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.02.20, 19:03
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

package org.mcnative.bukkit.player;

import net.prematic.libraries.message.bml.variable.VariableSet;
import net.prematic.libraries.utility.annonations.Internal;
import org.bukkit.Bukkit;
import org.mcnative.bukkit.McNativeLauncher;
import org.mcnative.bukkit.entity.BukkitEntity;
import org.mcnative.bukkit.entity.living.BukkitHumanEntity;
import org.mcnative.bukkit.inventory.item.BukkitItemStack;
import org.mcnative.bukkit.location.BukkitLocation;
import org.mcnative.bukkit.player.permission.BukkitPermissionHandler;
import org.mcnative.bukkit.world.BukkitWorld;
import org.mcnative.common.McNative;
import org.mcnative.common.connection.ConnectionState;
import org.mcnative.common.connection.PendingConnection;
import org.mcnative.common.network.component.server.MinecraftServer;
import org.mcnative.common.network.component.server.ProxyServer;
import org.mcnative.common.network.component.server.ServerConnectReason;
import org.mcnative.common.network.component.server.ServerConnectResult;
import org.mcnative.common.player.*;
import org.mcnative.common.player.bossbar.BossBar;
import org.mcnative.common.player.data.MinecraftPlayerData;
import org.mcnative.common.player.scoreboard.BelowNameInfo;
import org.mcnative.common.player.scoreboard.Tablist;
import org.mcnative.common.player.scoreboard.sidebar.Sidebar;
import org.mcnative.common.player.sound.Instrument;
import org.mcnative.common.player.sound.Note;
import org.mcnative.common.player.sound.Sound;
import org.mcnative.common.player.sound.SoundCategory;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.protocol.packet.MinecraftPacket;
import org.mcnative.common.protocol.packet.type.MinecraftChatPacket;
import org.mcnative.common.protocol.packet.type.MinecraftTitlePacket;
import org.mcnative.common.protocol.support.ProtocolCheck;
import org.mcnative.common.serviceprovider.permission.PermissionHandler;
import org.mcnative.common.serviceprovider.permission.PermissionProvider;
import org.mcnative.common.text.components.MessageComponent;
import org.mcnative.service.Effect;
import org.mcnative.service.GameMode;
import org.mcnative.service.MinecraftService;
import org.mcnative.service.advancement.AdvancementProgress;
import org.mcnative.service.entity.Entity;
import org.mcnative.service.entity.living.Player;
import org.mcnative.service.inventory.Inventory;
import org.mcnative.service.inventory.item.ItemStack;
import org.mcnative.service.location.Location;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BukkitPlayer extends OfflineMinecraftPlayer implements Player, BukkitHumanEntity<org.bukkit.entity.Player> {

    private final org.bukkit.entity.Player original;
    private final PendingConnection connection;

    private ChatChannel chatChannel;

    private BukkitWorld world;
    private boolean permissibleInjected;

    public BukkitPlayer(org.bukkit.entity.Player original, PendingConnection connection,MinecraftPlayerData playerData) {
        super(playerData);
        this.original = original;
        this.connection = connection;
        this.permissibleInjected = false;
    }

    @Override
    public PermissionHandler getPermissionHandler() {
        if(permissionHandler == null){
            permissionHandler = McNative.getInstance().getRegistry().getService(PermissionProvider.class).getPlayerHandler(this);
            if(!permissibleInjected && !(permissionHandler instanceof BukkitPermissionHandler)){
                new McNativePermissible(original,this).inject();
                permissibleInjected = true;
            }
        }else if(!permissionHandler.isCached()){
            permissionHandler = permissionHandler.reload();
        }
        return permissionHandler;
    }

    @Override
    public InetSocketAddress getVirtualHost() {
        return connection.getVirtualHost();
    }

    @Override
    public MinecraftProtocolVersion getProtocolVersion() {
        return connection.getProtocolVersion();
    }

    @Override
    public ConnectionState getState() {
        return connection.getState();
    }

    @Override
    public InetSocketAddress getAddress() {
        return original.getAddress();
    }

    @Override
    public void disconnect(MessageComponent<?> reason, VariableSet variables) {
        connection.disconnect(reason,variables);
    }

    @Override
    public DeviceInfo getDevice() {
        return DeviceInfo.JAVA;
    }

    @Override
    public boolean isOnlineMode() {
        return Bukkit.getServer().getOnlineMode();
    }

    @Override
    public PlayerSettings getSettings() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public int getPing() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public ProxyServer getProxy() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public MinecraftServer getServer() {
        return MinecraftService.getInstance();
    }

    @Override
    public void connect(MinecraftServer target, ServerConnectReason reason) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public CompletableFuture<ServerConnectResult> connectAsync(MinecraftServer target, ServerConnectReason reason) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public ChatChannel getChatChannel() {
        return chatChannel;
    }

    @Override
    public void setChatChannel(ChatChannel channel) {
        this.chatChannel = channel;
    }

    @Override
    public void kick(MessageComponent<?> message, VariableSet variables) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void performCommand(String command) {
        original.performCommand(command);
    }

    @Override
    public void chat(String message) {
        original.chat(message);
    }

    @Override
    public void sendMessage(ChatPosition position, MessageComponent<?> message, VariableSet variables) {
        MinecraftChatPacket packet = new MinecraftChatPacket();
        packet.setPosition(position);
        packet.setMessage(message);
        packet.setVariables(variables);
        sendPacket(packet);
    }

    @Override
    public void sendTitle(Title title) {
        MinecraftTitlePacket timePacket = new MinecraftTitlePacket();
        timePacket.setAction(MinecraftTitlePacket.Action.SET_TIME);
        timePacket.setTime(title.getTiming());
        sendPacket(timePacket);

        if(title.getTitle() != null){
            MinecraftTitlePacket titlePacket = new MinecraftTitlePacket();
            titlePacket.setAction(MinecraftTitlePacket.Action.SET_TITLE);
            titlePacket.setMessage(title.getTitle());
            titlePacket.setVariables(title.getVariables());
            sendPacket(titlePacket);
        }

        if(title.getSubTitle() != null){
            MinecraftTitlePacket subTitle = new MinecraftTitlePacket();
            subTitle.setAction(MinecraftTitlePacket.Action.SET_SUBTITLE);
            subTitle.setMessage(title.getSubTitle());
            subTitle.setVariables(title.getVariables());
            sendPacket(subTitle);
        }
    }

    @Override
    public void resetTitle() {
        MinecraftTitlePacket packet = new MinecraftTitlePacket();
        packet.setAction(MinecraftTitlePacket.Action.RESET);
        sendPacket(packet);
    }

    @Override
    public void sendActionbar(MessageComponent<?> message, VariableSet variables) {
        MinecraftChatPacket packet = new MinecraftChatPacket();
        packet.setPosition(ChatPosition.ACTIONBAR);
        packet.setMessage(message);
        packet.setVariables(variables);
        sendPacket(packet);
    }

    @Override
    public void sendActionbar(MessageComponent<?> message, VariableSet variables, long staySeconds) {
        throw new UnsupportedOperationException("Coming soon");
    }

    @Override
    public void sendPacket(MinecraftPacket packet) {
        connection.sendPacket(packet);
    }

    @Override
    public void sendLocalLoopPacket(MinecraftPacket packet) {

    }

    @Override
    public void sendData(String channel, byte[] output) {

    }

    @Override
    public void playNote(Instrument instrument, Note note) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void playSound(Sound sound, SoundCategory category, float volume, float pitch) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void stopSound(Sound sound) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void stopSound(String sound, SoundCategory category) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void check(Consumer<ProtocolCheck> checker) {
        throw new UnsupportedOperationException("Currently not supported");
    }


    @Override
    public boolean isBlocking() {
        return original.isBlocking();
    }

    @Override
    public boolean isSleeping() {
        return original.isSleeping();
    }


    @Override
    public org.bukkit.entity.Player getOriginal() {
        return original;
    }

    @Override
    public BukkitWorld getBukkitWorld() {
        return world;
    }

    @Override
    public void openBook(ItemStack book) {
        this.original.openBook(((BukkitItemStack)book).getOriginal());
    }

    @Override
    public void hide(OnlineMinecraftPlayer forPlayer) {
        org.bukkit.entity.Player player = Bukkit.getPlayer(forPlayer.getUniqueId());
        if(player != null) {
            this.original.hidePlayer(McNativeLauncher.getPlugin(), player);
        }
    }

    @Override
    public void show(OnlineMinecraftPlayer forPlayer) {
        org.bukkit.entity.Player player = Bukkit.getPlayer(forPlayer.getUniqueId());
        if(player != null) {
            this.original.showPlayer(McNativeLauncher.getPlugin(), player);
        }
    }

    @Override
    public boolean canSee(OnlineMinecraftPlayer forPlayer) {
        org.bukkit.entity.Player player = Bukkit.getPlayer(forPlayer.getUniqueId());
       return player != null && this.original.canSee(player);
    }

    @Override
    public boolean isSneaking() {
        return this.original.isSneaking();
    }

    @Override
    public void setSneaking(boolean sneak) {
        this.original.setSneaking(sneak);
    }

    @Override
    public boolean isSprinting() {
        return  this.original.isSprinting();
    }

    @Override
    public void setSprinting(boolean sprinting) {
        this.original.setSprinting(sprinting);
    }

    @Override
    public boolean isSleepingIgnored() {
        return  this.original.isSleepingIgnored();
    }

    @Override
    public void setSleepingIgnored(boolean isSleeping) {
        this.original.setSleepingIgnored(isSleeping);
    }

    @Override
    public GameMode getGameMode() {
        return GameMode.valueOf(original.getGameMode().name());
    }

    @Override
    public void setGameMode(GameMode mode) {
        org.bukkit.GameMode bukkitGameMode = org.bukkit.GameMode.valueOf(mode.name());
        this.original.setGameMode(bukkitGameMode);
    }


    @Override
    public Location getCompassTarget() {
        return new BukkitLocation(this.original.getCompassTarget(), world);
    }

    @Override
    public void setCompassTarget(Location location) {
        this.original.setCompassTarget(((BukkitLocation)location).getOriginal());
    }

    @Override
    public long getPlayerTime() {
        return this.original.getPlayerTime();
    }

    @Override
    public void setPlayerTime(long time, boolean relative) {
        this.original.setPlayerTime(time, relative);
    }

    @Override
    public void resetPlayerTime() {
        this.original.resetPlayerTime();
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return this.original.isPlayerTimeRelative();
    }

    @Override
    public float getExperience() {
        return this.original.getExp();
    }

    @Override
    public void setExperience(float xp) {
        this.original.setExp(xp);
    }

    @Override
    public void addExperience(float xp) {
        this.original.setExp(this.original.getExp()+xp);
    }

    @Override
    public void removeExperience(float xp) {
        this.original.setExp(this.original.getExp()-xp);
    }

    @Override
    public int getLevel() {
        return this.original.getLevel();
    }

    @Override
    public void setLevel(int level) {
        this.original.setLevel(level);
    }

    @Override
    public void addLevel(int level) {
        this.original.setLevel(this.original.getLevel()+level);
    }

    @Override
    public void removeLevel(int level) {
        this.original.setLevel(this.original.getLevel()-level);
    }

    @Override
    public int getTotalExperience() {
        return this.original.getTotalExperience();
    }

    @Override
    public void setTotalExperience(int exp) {
        this.original.setTotalExperience(exp);
    }

    @Override
    public int getFoodLevel() {
        return this.original.getFoodLevel();
    }

    @Override
    public void setFoodLevel(int food) {
        this.original.setFoodLevel(food);
    }

    @Override
    public float getSaturation() {
        return this.original.getSaturation();
    }

    @Override
    public void setSaturation(float value) {
        this.original.setSaturation(value);
    }

    @Override
    public float getExhaustion() {
        return this.original.getExhaustion();
    }

    @Override
    public void setExhaustion(float value) {
        this.original.setExhaustion(value);
    }

    @Override
    public boolean isAllowFlight() {
        return this.original.getAllowFlight();
    }

    @Override
    public void setAllowFlight(boolean flight) {
        this.original.setAllowFlight(flight);
    }

    @Override
    public boolean isFlying() {
        return this.original.isFlying();
    }

    @Override
    public void setFlying(boolean value) {
        this.original.setFlying(value);
    }

    @Override
    public float getFlySpeed() {
        return this.original.getFlySpeed();
    }

    @Override
    public void setFlySpeed(float value) {
        this.original.setFlySpeed(value);
    }

    @Override
    public float getWalkSpeed() {
        return this.original.getWalkSpeed();
    }

    @Override
    public void setWalkSpeed(float value) {
        this.original.setWalkSpeed(value);
    }

    @Override
    public Entity getSpectatorTarget() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void setSpectatorTarget(Entity entity) {
        this.original.setSpectatorTarget(((BukkitEntity<?>)entity).getOriginal());
    }

    @Override
    public void playEffect(Location location, Effect effect, int data) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public AdvancementProgress getAdvancementProgress() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public Inventory getEnderchestInventory() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public Location getBedSpawnLocation() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void setBedSpawnLocation(Location location) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Internal
    public void setWorld(BukkitWorld world){
        this.world = world;
    }

    @Override
    public Sidebar getSidebar() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void setSidebar(Sidebar sidebar) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public Tablist getTablist() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void setTablist(Tablist tablist) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public BelowNameInfo getBelowNameInfo() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void setBelowNameInfo(BelowNameInfo info) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public Collection<BossBar> getActiveBossBars() {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void addBossBar(BossBar bossBar) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void removeBossBar(BossBar bossBar) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void setResourcePack(String url) {
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public void setResourcePack(String url, String hash) {
        throw new UnsupportedOperationException("Currently not supported");
    }
}
