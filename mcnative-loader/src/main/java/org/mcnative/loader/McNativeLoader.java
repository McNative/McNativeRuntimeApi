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

import net.pretronic.libraries.resourceloader.ResourceInfo;
import net.pretronic.libraries.resourceloader.ResourceLoader;
import net.pretronic.libraries.resourceloader.UpdateConfiguration;
import net.pretronic.libraries.resourceloader.VersionInfo;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class McNativeLoader extends ResourceLoader {

    private static final String VERSION_URL = "https://mirror.pretronic.net/v1/e5b65750-4dcc-4631-b275-06113b31a416/versions/latest?plain=true&qualifier={qualifier}";
    private static final String DOWNLOAD_URL = "https://mirror.pretronic.net/v1/e5b65750-4dcc-4631-b275-06113b31a416/versions/{version.build}/download?edition={edition}";

    private final static ResourceInfo MCNATIVE = new ResourceInfo("McNative",new File("plugins/McNative/lib/resources/mcnative/"));

    private final Logger logger;
    private final String platform;

    static {
        MCNATIVE.setVersionUrl(VERSION_URL);
    }

    public McNativeLoader(Logger logger, String platform) {
        super(MCNATIVE);
        this.logger = logger;
        this.platform = platform;
    }

    public boolean isAvailable(){
        try{
            Method availableMethod = Class.forName("org.mcnative.common.McNative").getMethod("isAvailable");
            return (boolean) availableMethod.invoke(null);
        }catch (Exception ignored){}
        return false;
    }

    public boolean install(){
        if(isAvailable()) return true;
        VersionInfo current = getCurrentVersion();
        VersionInfo latest = null;

        UpdateConfiguration configuration = getUpdateConfiguration();

        try{
            latest = getLatestVersion();
        }catch (Exception exception){
            logger.log(Level.SEVERE,"(McNative-Loader) "+exception.getMessage());
            if(current == null || current.equals(VersionInfo.UNKNOWN)){
                logger.log(Level.SEVERE,"(McNative-Loader) McNative is not available, shutting down");
                return false;
            }
        }

        if(latest != null){
            if(isLatestVersion()){
                logger.info("(McNative-Loader) McNative "+latest.getName()+" (Up to date)");
            }else{
                if(current == null || configuration.isEnabled()){
                    MCNATIVE.setDownloadUrl(DOWNLOAD_URL.replace("{edition}",platform));
                    logger.info("(McNative-Loader) Downloading McNative "+latest.getName());
                    try{
                        download(latest);
                        logger.info("(McNative-Loader) Successfully downloaded McNative");
                    }catch (Exception exception){
                        exception.printStackTrace();
                        if(current == null || current.equals(VersionInfo.UNKNOWN)){
                            exception.printStackTrace();
                            logger.log(Level.SEVERE,"(McNative-Loader) download failed, shutting down",exception);
                            return false;
                        }else{
                            logger.info("(McNative-Loader) download failed, trying to start an older version");
                        }
                    }
                }else{
                    logger.info("(McNative-Loader) automatically updating is disabled");
                    logger.info("(McNative-Loader) Latest Version: "+latest.getName());
                }
            }
        }
        return launch();
    }

    public boolean launch(){
        try{
            loadReflected((URLClassLoader) getClass().getClassLoader());
            Class<?> mcNativeClass = getClass().getClassLoader().loadClass("org.mcnative."+this.platform.toLowerCase()+".McNativeLauncher");
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
