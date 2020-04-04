/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 18.03.20, 17:48
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

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.annotations.DocumentKey;
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.logging.PretronicLogger;
import net.pretronic.libraries.utility.map.Pair;
import org.mcnative.common.McNative;
import org.mcnative.common.plugin.configuration.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class McNativeBukkitConfiguration {

    @DocumentKey("autoUpdate.enabled")
    public static boolean AUTO_UPDATE_ENABLED = true;

    @DocumentKey("autoUpdate.qualifier")
    public static String AUTO_UPDATE_QUALIFIER = "BETA";

    @DocumentKey("player.displayName.format")
    public static String PLAYER_DISPLAY_NAME_FORMAT = "{color}{name}";

    public static Map<String,String> PLAYER_COLORS_COLORS = new LinkedHashMap<>();
    public static String PLAYER_COLORS_DEFAULT = "&7";

    static{
        PLAYER_COLORS_COLORS.put("mcnative.player.color.administrator","&4");
        PLAYER_COLORS_COLORS.put("mcnative.player.color.moderator","&c");
        PLAYER_COLORS_COLORS.put("mcnative.player.color.premium","&6");
    }

    public static boolean load(PretronicLogger logger, File location){
        logger.info(McNative.CONSOLE_PREFIX+"Searching configuration file");
        Pair<File, DocumentFileType> configSpec = Document.findExistingType(location,"config");
        File configFile;
        DocumentFileType type;

        if(configSpec == null){
            configFile = new File(location,"config.yml");
            type = DocumentFileType.YAML;
            try {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            } catch (IOException exception) {
                logger.error(McNative.CONSOLE_PREFIX+"Could not create configuration file (config.yml)",exception);
                exception.printStackTrace();
                return false;
            }
        }else{
            configFile = configSpec.getKey();
            type = configSpec.getValue();
        }
        logger.info(McNative.CONSOLE_PREFIX+"Loading configuration (config.{})",type.getEnding());

        try{
            FileConfiguration.FILE_TYPE = type;
            Document config = type.getReader().read(configFile);
            Document.loadConfigurationClass(McNativeBukkitConfiguration.class,config);
            type.getWriter().write(configFile,config);
        }catch (Exception exception){
            logger.info(McNative.CONSOLE_PREFIX+"Could not load configuration (config."+type.getEnding()+")",exception);
            exception.printStackTrace();
            return false;
        }
        return true;
    }
}
