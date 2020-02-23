/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.11.19, 16:25
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

package org.mcnative.loader;

import net.prematic.libraries.resourceloader.ResourceInfo;
import net.prematic.libraries.resourceloader.ResourceLoader;
import net.prematic.libraries.resourceloader.VersionInfo;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class McNativeLoader extends ResourceLoader {

    private final static ResourceInfo MCNATIVE = new ResourceInfo("McNative"
            ,"http://localhost/mcnative/download/latest.jar"
            ,"http://localhost/mcnative/latest.txt"
            ,new File("plugins/McNative/lib/resources/mcnative/"));

    private final Logger logger;
    private final String platform;

    public McNativeLoader(Logger logger, String platform) {
        super(MCNATIVE);
        this.logger = logger;
        this.platform = platform;
    }

    public boolean isAvailable(){
        try{
            Method availableMethod = Class.forName("org.mcnative.common.McNative").getMethod("isAvailable");
            return (boolean) availableMethod.invoke(null);
        }catch (Exception ignored){
            ignored.printStackTrace();
        }
        return false;
    }

    public boolean install(){//@Todo add update configuration options (In mcnative config / disable auto update)
        if(isAvailable()) return true;
        VersionInfo current = getCurrentVersion();
        VersionInfo latest = null;

        try{
            latest = getLatestVersion();
        }catch (Exception exception){
            logger.log(Level.SEVERE,"(McNative-Loader) Could not get latest version ("+exception.getMessage()+")");
            if(current == null){
                logger.log(Level.SEVERE,"(McNative-Loader) McNative not available, shutting down");
                return false;
            }
        }
        if(latest != null){
            if(isLatestVersion()){
                logger.info("(McNative-Loader) McNative "+latest.getName()+" - "+latest.getBuild()+" (Up to date)");
            }else{
                logger.info("(McNative-Loader) Downloading McNative "+latest.getName()+" - "+latest.getBuild());
                try{
                    download(latest);
                    logger.info("(McNative-Loader) Successfully downloaded McNative");
                }catch (Exception exception){
                    if(current == null || current.equals(VersionInfo.UNKNOWN)){
                        logger.info("(McNative-Loader) download failed, shutting down");
                        return false;
                    }else{
                        logger.info("(McNative-Loader) download failed, trying to start an older version");
                    }
                }
            }
        }
        return launch();
    }

    public boolean launch(){
        try{
            ClassLoader loader = getClass().getClassLoader();
            loadReflected((URLClassLoader) loader);
            Class<?> mcNativeClass = loader.loadClass("org.mcnative."+this.platform.toLowerCase()+".McNativeLauncher");
            Method launchMethod = mcNativeClass.getMethod("launchMcNative");
            launchMethod.invoke(null);
            return true;
        }catch (Exception exception){
            logger.log(Level.SEVERE,"Could not launch McNative.",exception);
        }
        return false;
    }

    public static boolean install(Logger logger,String platform){
        return new McNativeLoader(logger,platform).install();
    }
}
