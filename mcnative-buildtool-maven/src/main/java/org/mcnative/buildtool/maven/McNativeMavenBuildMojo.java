/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:43
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

package org.mcnative.buildtool.maven;

import net.prematic.libraries.document.Document;
import net.prematic.libraries.document.type.DocumentFileType;
import net.prematic.libraries.utility.http.HttpClient;
import net.prematic.libraries.utility.http.HttpResult;
import net.prematic.libraries.utility.io.FileUtil;
import net.prematic.libraries.utility.io.archive.ZipArchive;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Repository;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.DefaultProjectBuildingRequest;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuildingRequest;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.function.Consumer;

@Mojo(name="McNative", defaultPhase = LifecyclePhase.GENERATE_SOURCES,threadSafe = true)
public class McNativeMavenBuildMojo extends AbstractMojo {

    //@Todo change url
    private static final String MCNATIVE_WRAPPER_URL = "http://localhost/mcnative/download/wrapper/mcnative-wrapper-${version}.zip";

    private static final File MCNATIVE_SOURCE_DIRECTORY = new File("target/generated-sources/mcnative/");
    private static final File MCNATIVE_RESOURCE_DIRECTORY = new File("target/generated-resources/mcnative/");
    private static final File MCNATIVE_SOURCE_DIRECTORY_META_INF = new File(MCNATIVE_SOURCE_DIRECTORY,"META-INF/");
    private static final File MCNATIVE_SOURCE_DIRECTORY_DEPENDENCIES = new File(MCNATIVE_SOURCE_DIRECTORY,"dependencies.json");

    private static final String MCNATIVE_WRAPPER_BASE_NAME = "mcnative-wrapper-${version}.zip";
    private static final String MCNATIVE_WRAPPER_BASE_PACKAGE = "org.mcnative.wrapper";
    private static final File MCNATIVE_WRAPPER_BASE_PATH = new File(MCNATIVE_SOURCE_DIRECTORY,"org/mcnative/wrapper");

    private static final File MANIFEST_FILE_MCNATIVE = new File(MCNATIVE_RESOURCE_DIRECTORY,"mcnative.json");
    private static final File MANIFEST_FILE_BUKKIT = new File(MCNATIVE_RESOURCE_DIRECTORY,"plugin.yml");
    private static final File MANIFEST_FILE_BUNGEECORD = new File(MCNATIVE_RESOURCE_DIRECTORY,"bungee.yml");
    private static final File MANIFEST_FILE_NUKKIT = new File(MCNATIVE_RESOURCE_DIRECTORY,"nukkit.yml");

    @Parameter(defaultValue = "${project}", readonly = true, required = true )
    private MavenProject project;

    @Parameter( defaultValue = "${session}", readonly = true, required = true )
    private MavenSession session;

    @Parameter( property = "mcnative.location",defaultValue = "target/mcnative/")
    private String location;

    @Parameter( property = "mcnative.version" ,defaultValue = "1.0.0")
    private String version;

    @Parameter( property = "mcnative.manifest" )
    private McNativePluginManifest manifest;

    private File mcNativeWrapperLocation;
    private String pluginWrapperPackage;
    private File pluginWrapperPath;
    private Document pluginManifest;

    @Override
    public void execute() throws MojoFailureException {
        validate();

        this.pluginWrapperPackage = project.getGroupId()+".bootstrap";
        this.pluginWrapperPath = new File(MCNATIVE_SOURCE_DIRECTORY,pluginWrapperPackage.replace('.','/'));
        this.mcNativeWrapperLocation = new File(location+MCNATIVE_WRAPPER_BASE_NAME.replace("${version}",version));

        if(pluginWrapperPath.exists()){
            getLog().info("McNative plugin bootstrap already generated.");
            return;
        }

        this.manifest.getSoftdepends().add("McNative");
        this.pluginManifest = Document.newDocument(manifest);

        try{
            if(!mcNativeWrapperLocation.exists()) downloadMcNativeWrapper();
            MCNATIVE_RESOURCE_DIRECTORY.mkdirs();

            unpackWrapper(mcNativeWrapperLocation);
            renamePackages();

            createMcNativeYML();
            createBukkitYML();
            createBungeeCordYML();

            addDependencies();

            FileUtils.copyDirectoryStructure(MCNATIVE_RESOURCE_DIRECTORY,new File(project.getBuild().getOutputDirectory()));
        }catch (Exception exception){
            exception.printStackTrace();
            throw new MojoFailureException(exception,exception.getMessage(),exception.getMessage());
        }

        project.addCompileSourceRoot(MCNATIVE_SOURCE_DIRECTORY.getPath());
    }

    private void validate() throws MojoFailureException{
        if(this.manifest == null) throw new MojoFailureException("No McNative bootstrap manifest found.");
        if(this.manifest.getName() == null || this.manifest.getMain() == null || this.manifest.getVersion() == null)
            throw new MojoFailureException("Manifest requires a name, main and version.");
    }

    private void createMcNativeYML(){
        DocumentFileType.JSON.getWriter().write(MANIFEST_FILE_MCNATIVE,pluginManifest);
    }

    private void addDependencies(){

        project.getModel().getRepositories().forEach(new Consumer<Repository>() {
            @Override
            public void accept(Repository repository) {
                System.out.println(repository.getUrl());
            }
        });


        Repository bungeeCord = new Repository();
        bungeeCord.setId("BungeeCord-for-McNative");
        bungeeCord.setUrl("https://hub.spigotmc.org/nexus/content/repositories/snapshots/");

        Dependency bungee = new Dependency();
        bungee.setGroupId("net.md-5");
        bungee.setArtifactId("bungeecord-api");
        bungee.setVersion("1.14-SNAPSHOT");
        bungee.setScope("provided");

        Repository bukkitRepo = new Repository();
        bungeeCord.setId("Bukkit-for-McNative");
        bungeeCord.setUrl("https://hub.spigotmc.org/nexus/content/repositories/snapshots/");

        Dependency bukkit = new Dependency();
        bukkit.setGroupId("org.bukkit");
        bukkit.setArtifactId("bukkit");
        bukkit.setVersion("1.14.2-R0.1-SNAPSHOT");
        bukkit.setScope("provided");

        //project.getModel().addRepository(bukkitRepo);
        project.getModel().addDependency(bukkit);
        ProjectBuildingRequest buildingRequest = new DefaultProjectBuildingRequest(session.getProjectBuildingRequest());
    }

    private void createBukkitYML(){
        Document bukkitConfiguration = pluginManifest.copy().toDocument();
        bukkitConfiguration.set("main",pluginWrapperPackage+".bukkit.BukkitMcNativePluginBootstrap");
        DocumentFileType.YAML.getWriter().write(MANIFEST_FILE_BUKKIT,bukkitConfiguration);
    }

    private void createBungeeCordYML(){
        Document bungeeCordConfiguration = pluginManifest.copy().toDocument();
        bungeeCordConfiguration.set("main",pluginWrapperPackage+".bungeecord.BungeeCordMcNativePluginBootstrap");
        DocumentFileType.YAML.getWriter().write(MANIFEST_FILE_BUNGEECORD,bungeeCordConfiguration);
    }

    private void downloadMcNativeWrapper() throws MojoFailureException{
        getLog().info("Download McNative wrapper v"+version+".");
        HttpClient client = new HttpClient();

        client.setUrl(MCNATIVE_WRAPPER_URL.replace("${version}",this.version));

        HttpResult result = client.connect();
        if(result.getCode() == 200) result.save(mcNativeWrapperLocation);
        else{
            String message = String.format("Could not download McNative wrapper version %s (%s).",version,result.getCode());
            throw new MojoFailureException(result,message,message);
        }
        result.close();
        getLog().info("Successfully downloaded Mcnative wrapper.");
    }

    private void unpackWrapper(File location) throws IOException{
        getLog().info("Unpacking McNative wrapper");
        ZipArchive archive = new ZipArchive(location);
        archive.extract(MCNATIVE_SOURCE_DIRECTORY);
        FileUtils.deleteDirectory(MCNATIVE_SOURCE_DIRECTORY_META_INF);
    }

    private void renamePackages() throws IOException{
        FileUtils.rename(MCNATIVE_WRAPPER_BASE_PATH,pluginWrapperPath);
        FileUtil.processFilesHierarchically(MCNATIVE_SOURCE_DIRECTORY, file -> {
            try{
                Charset charset = StandardCharsets.UTF_8;
                String content = new String(Files.readAllBytes(file.toPath()), charset);
                content = content.replaceAll(MCNATIVE_WRAPPER_BASE_PACKAGE,pluginWrapperPackage);
                Files.write(file.toPath(), content.getBytes(charset));
            }catch (IOException exception){
                exception.printStackTrace();
            }
        });
    }
}
