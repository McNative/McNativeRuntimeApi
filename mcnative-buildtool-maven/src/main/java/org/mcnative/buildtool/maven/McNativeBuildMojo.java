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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.FileUtils;
import org.mcnative.buildtool.maven.loader.LoaderConfiguration;
import org.mcnative.buildtool.maven.loader.McNativeLoaderCreator;

import java.io.File;
import java.io.IOException;

@Mojo(name="McNative-Build", defaultPhase = LifecyclePhase.GENERATE_SOURCES,threadSafe = true)
public class McNativeBuildMojo extends AbstractMojo {

    private static final File MCNATIVE_LOADER_SOURCE_DIRECTORY = new File("target/generated-sources/mcnative-loader/");
    private static final File MCNATIVE_LOADER_RESOURCE_DIRECTORY = new File("target/generated-resources/mcnative-loader/");
    private static final File MCNATIVE_MANIFEST_FILE = new File(MCNATIVE_LOADER_RESOURCE_DIRECTORY,"mcnative.json");

    @Parameter(defaultValue = "${project}", readonly = true, required = true )
    private MavenProject project;

    @Parameter( property = "mcnative.loader.location",defaultValue = "${project.basedir}/McNative/")
    private String loaderLocation;

    @Parameter( property = "mcnative.loader.version" ,defaultValue = "1.0.0")
    private String loaderVersion;

    @Parameter( property = "mcnative.manifest",readonly = true,required =true)
    private McNativePluginManifest manifest;

    @Parameter( property = "mcnative.loader.configuration",readonly = true,required =true)
    private LoaderConfiguration loaderConfig;

    @Override
    public void execute() throws MojoFailureException, MojoExecutionException {
        project.addCompileSourceRoot(MCNATIVE_LOADER_SOURCE_DIRECTORY.getPath());
        if(MCNATIVE_MANIFEST_FILE.exists()){
            getLog().warn("McNative manifest already generated.");
            return;
        }
        this.manifest.createManifestFile(MCNATIVE_MANIFEST_FILE);

        MCNATIVE_LOADER_SOURCE_DIRECTORY.mkdirs();
        MCNATIVE_LOADER_RESOURCE_DIRECTORY.mkdirs();

        String basePackage = project.getGroupId()+".loader";
        McNativeLoaderCreator creator = new McNativeLoaderCreator(getLog(),loaderVersion,basePackage
                ,new File(loaderLocation),MCNATIVE_LOADER_SOURCE_DIRECTORY,MCNATIVE_LOADER_RESOURCE_DIRECTORY);
        creator.downloadLoaderSource();
        creator.unpackLoader();
        creator.renamePackages();
        creator.createManifests(loaderConfig,manifest);

        try {
            FileUtils.copyDirectoryStructure(MCNATIVE_LOADER_RESOURCE_DIRECTORY,new File(project.getBuild().getOutputDirectory()));
        } catch (IOException e) {
            throw new MojoFailureException(e.getMessage());
        }
    }
}
