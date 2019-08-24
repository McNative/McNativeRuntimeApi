/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.08.19, 17:27
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

package org.mcnative.proxy;

import net.prematic.libraries.document.annotations.DocumentFile;

import java.util.Arrays;
import java.util.Collection;

@DocumentFile(source = "mcnative.",type = "YAML",loadAll = true,appendMissing = true)
public class McNativeProxyConfiguration {

    public static boolean WHITELIST_ENABLED = false;
    public static String WHITELIST_MESSAGE = "&6You are not whitelisted on this server.";
    public static String WHITELIST_PERMISSION = "mcnative.whitelist.join";
    public static Collection<String> WHITELIST_PLAYERS = Arrays.asList("Dkrieger","cb7f0812-1fbb-4715-976e-a81e52be4b67");

}
