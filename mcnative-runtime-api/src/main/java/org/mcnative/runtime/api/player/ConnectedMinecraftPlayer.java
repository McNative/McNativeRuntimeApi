/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 28.12.19, 22:26
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

package org.mcnative.runtime.api.player;

import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.Validate;
import org.mcnative.runtime.api.connection.MinecraftConnection;
import org.mcnative.runtime.api.connection.PendingConnection;
import org.mcnative.runtime.api.player.bossbar.BossBar;
import org.mcnative.runtime.api.player.chat.ChatChannel;
import org.mcnative.runtime.api.player.client.CustomClient;
import org.mcnative.runtime.api.player.input.ConfirmResult;
import org.mcnative.runtime.api.player.input.PlayerTextInputValidator;
import org.mcnative.runtime.api.player.input.YesNoResult;
import org.mcnative.runtime.api.player.scoreboard.BelowNameInfo;
import org.mcnative.runtime.api.player.scoreboard.sidebar.Sidebar;
import org.mcnative.runtime.api.player.tablist.Tablist;
import org.mcnative.runtime.api.text.components.MessageComponent;
import org.mcnative.runtime.api.text.components.SpecifiedPlayerMessageComponent;
import org.mcnative.runtime.api.text.format.TextColor;
import org.mcnative.runtime.api.utils.positioning.PositionAble;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface ConnectedMinecraftPlayer extends OnlineMinecraftPlayer, MinecraftConnection, PositionAble, CommandSender{

    PendingConnection getConnection();

    PlayerClientSettings getClientSettings();

    CustomClient getCustomClient();


    <T extends CustomClient> T getCustomClient(Class<T> clazz);

    boolean isCustomClient();

    boolean isCustomClient(String name);

    boolean isCustomClient(Class<? extends CustomClient> clazz);

    void setCustomClient(CustomClient client);


    ChatChannel getPrimaryChatChannel();

    void setPrimaryChatChannel(ChatChannel channel);


    Sidebar getSidebar();

    void setSidebar(Sidebar sidebar);


    Tablist getTablist();

    void setTablist(Tablist tablist);


    Collection<BossBar> getActiveBossBars();

    void addBossBar(BossBar bossBar);

    void removeBossBar(BossBar bossBar);


    BelowNameInfo getBelowNameInfo();

    void setBelowNameInfo(BelowNameInfo info);


    void sendResourcePackRequest(String url);

    void sendResourcePackRequest(String url, String hash);


    void requestTextInput(String label, String placeholder, Consumer<String> callback, PlayerTextInputValidator... validators);

    void requestBooleanInput(String label, String placeholder, Consumer<Boolean> callback, PlayerTextInputValidator... validators);

    void requestNumberInput(String label, String placeholder, Consumer<Long> callback, PlayerTextInputValidator... validators);

    void requestDecimalInput(String label, String placeholder, Consumer<Double> callback, PlayerTextInputValidator... validators);

    void requestColorInput(String label, String placeholder, Consumer<TextColor> callback, PlayerTextInputValidator... validators);

    <T> void requestObjectInput(String label, String placeholder, Function<String, T> converter, Consumer<T> callback, PlayerTextInputValidator... validators);


    void requestConfirmInput(String label, Consumer<ConfirmResult> callback);

    void requestYesNoInput(String label, Consumer<YesNoResult> callback);

    void requestOkInput(String label, Consumer<Boolean> callback);

    void requestButtonInput(String label, String buttonText, Consumer<Boolean> callback);

    @Override
    default boolean isPlayer(){
        return true;
    }

    default MessageComponent<?> toSpecifiedMessageComponent(MessageComponent<?> component, VariableSet variables) {
        Validate.notNull(component, variables);
        return new SpecifiedPlayerMessageComponent(this, component, variables);
    }

    default MessageComponent<?> toSpecifiedMessageComponent(MessageComponent<?> component) {
        return toSpecifiedMessageComponent(component, VariableSet.createEmpty());
    }
}
