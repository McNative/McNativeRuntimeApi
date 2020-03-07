/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 27.10.19, 13:15
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

package org.mcnative.buildtool.maven.loader;

import net.prematic.libraries.document.Document;
import net.prematic.libraries.document.type.DocumentFileType;
import net.prematic.libraries.utility.http.HttpClient;
import net.prematic.libraries.utility.http.HttpResult;
import net.prematic.libraries.utility.io.FileUtil;
import net.prematic.libraries.utility.io.IORuntimeException;
import net.prematic.libraries.utility.io.archive.ZipArchive;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.util.FileUtils;
import org.mcnative.buildtool.maven.McNativePluginManifest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class McNativeLoaderCreator {

    private static final String MCNATIVE_LOADER_URL = "https://repository.prematic.net/repository/pretronic/org/mcnative/mcnative-loader/{version}/mcnative-loader-{version}-sources.jar";
    private static final String MCNATIVE_LOADER_BASE_NAME = "mcnative-loader-{version}.jar";
    private static final String MCNATIVE_LOADER_BASE_PACKAGE = "org.mcnative.loader";
    private static final String MCNATIVE_LOADER_BASE_PATH = MCNATIVE_LOADER_BASE_PACKAGE.replace(".","/");

    private final Log log;
    private final String version;

    private final String basePackage;
    private final File baseFolder;
    private final File location;

    private final File sourceDirectory;
    private final File resourceDirectory;

    public McNativeLoaderCreator(Log log, String version, String basePackage, File location, File sourceDirectory, File resourceDirectory) {
        this.log = log;
        this.version = version;
        this.basePackage = basePackage;
        this.baseFolder = new File(sourceDirectory,basePackage.replace(".","/"));
        this.location = new File(location,MCNATIVE_LOADER_BASE_NAME.replace("${version}",version));
        this.sourceDirectory = sourceDirectory;
        this.resourceDirectory = resourceDirectory;
    }

    public void downloadSource() throws MojoFailureException{
        if(location.exists()) return;
        location.getParentFile().mkdirs();
        log.info("Downloading McNative loader v"+version+".");
        HttpClient client = new HttpClient();

        client.setUrl(MCNATIVE_LOADER_URL.replace("${version}",this.version));

        HttpResult result = client.connect();
        if(result.getCode() == 200) result.save(location);
        else{
            String message = String.format("Could not download McNative loader version %s (%s).",version,result.getCode());
            throw new MojoFailureException(result,message,message);
        }
        result.close();
        log.info("Successfully downloaded Mcnative loader.");
    }

    public void unpackLoader() throws MojoExecutionException {
        log.info("Unpacking McNative loader");
        ZipArchive archive = new ZipArchive(location);
        archive.extract(sourceDirectory);
        try {
            FileUtils.deleteDirectory(new File(sourceDirectory,"META-INF/"));
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new MojoExecutionException("Could not unpack McNative loader",exception);
        }
    }

    public void renamePackages() {
        try {
            FileUtils.rename(new File(sourceDirectory,MCNATIVE_LOADER_BASE_PATH),baseFolder);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IORuntimeException(e);
        }
        FileUtil.processFilesHierarchically(sourceDirectory, file -> {
            Charset charset = StandardCharsets.UTF_8;
            String content = new String(Files.readAllBytes(file.toPath()), charset);
            content = content.replaceAll(MCNATIVE_LOADER_BASE_PACKAGE,basePackage);
            Files.write(file.toPath(), content.getBytes(charset));
        });
    }

    public void createManifests(LoaderConfiguration loaderConfiguration, McNativePluginManifest manifest){
        Document document = Document.newDocument(manifest);
        String loaderVersion;

        if(loaderConfiguration != null && loaderConfiguration.getVersion() != null) loaderVersion = "Loader "+loaderConfiguration.getVersion();
        else loaderVersion = "Loader "+manifest.getVersion().getName()+"-"+manifest.getVersion().getBuild();

        createBungeeCordManifest(document.copy(null),loaderVersion);
        createBukkitManifest(document.copy(null),loaderVersion);

        if(loaderConfiguration != null){
            if(loaderConfiguration.getVersion() == null) loaderConfiguration.setVersion(manifest.getVersion().getName());
            loaderConfiguration.setPlugin(manifest);
            createLoaderInfo(loaderConfiguration);
        }
    }

    private void createBungeeCordManifest(Document document,String loaderVersion){
        document.set("main",basePackage+".bootstrap.BungeeCordMcNativePluginBootstrap");
        document.set("version",loaderVersion);
        DocumentFileType.YAML.getWriter().write(new File(resourceDirectory,"bungee.yml"),document);
    }

    private void createBukkitManifest(Document document,String loaderVersion){
        document.set("main",basePackage+".bootstrap.BukkitMcNativePluginBootstrap");
        document.set("version",loaderVersion);
        document.rename("softDepends","softdepend");
        DocumentFileType.YAML.getWriter().write(new File(resourceDirectory,"plugin.yml"),document);
    }

    private void createLoaderInfo(LoaderConfiguration loaderConfiguration){
        DocumentFileType.JSON.getWriter()
                .write(new File(resourceDirectory,"mcnative-loader.json")
                ,Document.newDocument(loaderConfiguration));
    }

}
