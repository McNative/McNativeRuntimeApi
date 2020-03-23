/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 23.03.20, 14:23
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

package org.mcnative.common;

import net.pretronic.libraries.document.annotations.DocumentIgnored;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.components.MessageKeyComponent;
import org.mcnative.common.text.components.TextComponent;

public final class Messages {

    public static final MessageKeyComponent PREFIX_USER = Text.ofMessageKey("&8» &cMcNative &8|&f");
    @DocumentIgnored
    public static final String PREFIX_CONSOLE = "[McNative]";

    public static MessageKeyComponent COMMAND_PASTE_SUCCESSFUL = Text.ofMessageKey("command.mcNative.paste.successful");
    public static MessageKeyComponent COMMAND_INFO_ADMIN = Text.ofMessageKey("command.mcNative.info.admin");
    public static MessageKeyComponent COMMAND_INFO_USER = Text.ofMessageKey("command.mcNative.info.user");
}
