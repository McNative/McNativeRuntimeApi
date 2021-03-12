/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 18.04.20, 20:20
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

package org.mcnative.runtime.api.protocol.packet.type.player;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import org.mcnative.runtime.api.protocol.packet.MinecraftPacket;
import org.mcnative.runtime.api.text.components.MessageComponent;

public class MinecraftPlayerListHeaderAndFooterPacket implements MinecraftPacket {

    private MessageComponent<?> header;
    private MessageComponent<?> footer;

    private VariableSet headerVariables;
    private VariableSet footerVariables;


    public MinecraftPlayerListHeaderAndFooterPacket() {
        this.headerVariables = VariableSet.createEmpty();
        this.footerVariables = VariableSet.createEmpty();
    }

    public MessageComponent<?> getHeader() {
        return header;
    }

    public void setHeader(MessageComponent<?> header) {
        this.header = header;
    }

    public MessageComponent<?> getFooter() {
        return footer;
    }

    public void setFooter(MessageComponent<?> footer) {
        this.footer = footer;
    }

    public VariableSet getHeaderVariables() {
        return headerVariables;
    }

    public void setHeaderVariables(VariableSet headerVariables) {
        this.headerVariables = headerVariables;
    }

    public VariableSet getFooterVariables() {
        return footerVariables;
    }

    public void setFooterVariables(VariableSet footerVariables) {
        this.footerVariables = footerVariables;
    }



}
