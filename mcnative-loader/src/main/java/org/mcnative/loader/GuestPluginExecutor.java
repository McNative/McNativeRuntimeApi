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

import net.prematic.libraries.dependency.DependencyGroup;
import net.prematic.libraries.document.Document;
import net.prematic.libraries.document.type.DocumentFileType;
import net.prematic.libraries.logging.bridge.JdkPrematicLogger;
import net.prematic.libraries.plugin.RuntimeEnvironment;
import net.prematic.libraries.plugin.description.DefaultPluginDescription;
import net.prematic.libraries.plugin.description.PluginDescription;
import net.prematic.libraries.plugin.loader.DefaultPluginLoader;
import net.prematic.libraries.plugin.loader.PluginLoader;
import net.prematic.libraries.plugin.loader.classloader.BridgedPluginClassLoader;
import net.prematic.libraries.resourceloader.ResourceInfo;
import net.prematic.libraries.resourceloader.ResourceLoader;
import net.prematic.libraries.resourceloader.VersionInfo;
import org.mcnative.common.McNative;

import java.io.File;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuestPluginExecutor {

    private final File location;
    private final Logger logger;
    private final RuntimeEnvironment<McNative> environment;
    private PluginLoader loader;

    public GuestPluginExecutor(File location, Logger logger, String runtimeName) {
        this.location = location;
        this.logger = logger;
        this.environment = new RuntimeEnvironment<>(runtimeName, McNative.getInstance());
    }

    public boolean install(){
        InputStream stream = getClass().getClassLoader().getResourceAsStream("mcnative.json");
        if(stream != null){
            setupLoader(stream);
        }else{
            InputStream streamLoader = getClass().getClassLoader().getResourceAsStream("mcnative-loader.json");
            if(streamLoader == null){
                logger.log(Level.SEVERE,"Invalid or corrupt mcnative plugin (mcnative.json or mcnative-loader.json is not available)");
                return false;
            }else{
                try{
                    Document loaderInfo = DocumentFileType.JSON.getReader().read(streamLoader);
                    if(downloadResource(loaderInfo)){
                        stream = getClass().getClassLoader().getResourceAsStream("mcnative.json");
                        setupLoader(stream);
                    }else{
                        return false;
                    }
                }catch (Exception exception){
                    logger.log(Level.SEVERE,String.format("Could not install plugin %s",exception.getMessage()));
                    return false;
                }
            }
        }
        return true;
    }

    public boolean installDependencies(){
        InputStream stream = loader.getClassLoader().getResourceAsStream("dependencies.json");
        if(stream == null) return true;
        Document data = DocumentFileType.JSON.getReader().read(stream);
        try{
            DependencyGroup dependencies = McNative.getInstance().getDependencyManager().load(data);
            dependencies.install();
            dependencies.loadReflected((URLClassLoader) loader.getClassLoader().asJVMLoader());
        }catch (Exception exception){
            logger.log(Level.SEVERE,String.format("Could not install dependencies %s",exception.getMessage()));
            return false;
        }
        return true;
    }

    private void setupLoader(InputStream descriptionStream){
        PluginDescription description = DefaultPluginDescription.create(
                McNative.getInstance().getPluginManager()
                , DocumentFileType.JSON.getReader().read(descriptionStream));
        this.loader = new DefaultPluginLoader(McNative.getInstance().getPluginManager(),environment
                ,new JdkPrematicLogger(logger),new BridgedPluginClassLoader(getClass().getClassLoader())
                ,location,description,false);
    }

    private boolean downloadResource(Document loader){
        String name = loader.getString("plugin.name");

        ResourceInfo info = new ResourceInfo(name,new File("plugins/McNative/lib/resources/"+name));
        info.setVersionUrl(replaceLoaderVariables(loader,loader.getString("versionUrl"),null));

        ResourceLoader resourceLoader = new ResourceLoader(info);

        VersionInfo current = resourceLoader.getCurrentVersion();
        VersionInfo latest = null;

        info.setVersionUrl(replaceLoaderVariables(loader,loader.getString("versionUrl"),current));

        try{
            latest = resourceLoader.getLatestVersion();
        }catch (Exception exception){
            logger.log(Level.SEVERE,"(Resource-Loader) Could not get latest version ("+exception.getMessage()+")");
            if(current == null || current.equals(VersionInfo.UNKNOWN)){
                logger.log(Level.SEVERE,"(Resource-Loader) Resource is not available, shutting down");
                return false;
            }
        }

        if(latest != null){
            if(resourceLoader.isLatestVersion()){
                logger.info("(Resource-Loader) "+name+" "+latest.getName()+" - "+latest.getBuild()+" (Up to date)");
            }else{
                logger.info("(Resource-Loader) Downloading "+name+" "+latest.getName()+" - "+latest.getBuild());
                try{
                    resourceLoader.download(latest);
                    logger.info("(McNative-Loader) Successfully downloaded ");
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

        try{
            ClassLoader classLoader = getClass().getClassLoader();
            resourceLoader.loadReflected((URLClassLoader) classLoader);
        }catch (Exception exception){
            logger.log(Level.SEVERE,"(Resource-Loader) Could not load "+name+" ("+exception.getMessage()+")");
            return false;
        }

        return true;
    }

    private String replaceLoaderVariables(Document loaderConfig,String input,VersionInfo versionInfo){
        String output = input
                .replace("{plugin.name}",loaderConfig.getString("plugin.name"))
                .replace("{plugin.id}",loaderConfig.getString("plugin.id"))
                .replace("{plugin.name}",loaderConfig.getString("plugin.name"));
        if(versionInfo != null){
            output = output
                    .replace("{version.name}",versionInfo.getName())
                    .replace("{version.qualifier}",versionInfo.getQualifier())
                    .replace("{version.build}",String.valueOf(versionInfo.getBuild()));
        }
        return output;
    }

    public PluginLoader getLoader() {
        return loader;
    }

    public void loadGuestPlugin(){
        McNative.getInstance().getPluginManager().provideLoader(loader);
        loader.construct();
        loader.initialize();
        loader.load();
    }

    public void enableGuestPlugin(){
        loader.bootstrap();
    }

    public void disableGuestPlugin(){
        loader.disable();
    }

}
