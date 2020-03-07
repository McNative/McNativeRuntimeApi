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
import org.mcnative.buildtool.maven.loader.ResourceLoaderInstaller;

import java.io.File;
import java.io.IOException;

@Mojo(name="McNative-Build", defaultPhase = LifecyclePhase.GENERATE_SOURCES,threadSafe = true)
public class McNativeBuildMojo extends AbstractMojo {

    private static final String MCNATIVE_LOADER_SOURCE_DIRECTORY_PATH = "/generated-sources/mcnative-loader/";
    private static final String MCNATIVE_LOADER_RESOURCE_DIRECTORY_PATH = "/generated-resources/mcnative-loader/";
    private static final String MCNATIVE_MANIFEST_FILE_PATH = MCNATIVE_LOADER_RESOURCE_DIRECTORY_PATH+"mcnative.json";

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    @Parameter( name = "mcnative-loader-location",defaultValue = "${project.basedir}/lib/")
    private String mcnativeLoaderLocation;

    @Parameter( name = "mcnative-loader-version" ,defaultValue = "1.0.0")
    private String mcnativeLoaderVersion;

    @Parameter( name = "resource-loader-version" ,defaultValue = "1.0.0")
    private String resourceLoaderVersion;

    @Parameter( property = "manifest",readonly = true,required =true)
    private McNativePluginManifest manifest;

    @Parameter( property = "loader",readonly = true,required =true)
    private LoaderConfiguration loader;

    @Override
    public void execute() throws MojoFailureException, MojoExecutionException {
        try{
            System.out.println("DEBUG POSITION 0");
            File output = new File(project.getBuild().getOutputDirectory());
            File sourceDirectory = new File(output.getParent(),MCNATIVE_LOADER_SOURCE_DIRECTORY_PATH);
            File resourceDirectory = new File(output.getParent(),MCNATIVE_LOADER_RESOURCE_DIRECTORY_PATH);
            File manifestFile = new File(output.getParent(),MCNATIVE_MANIFEST_FILE_PATH);

            sourceDirectory.mkdirs();
            resourceDirectory.mkdirs();

            System.out.println("DEBUG POSITION 1");

            System.out.println("PROJECT OUT | "+new File(project.getBuild().getOutputDirectory()).exists()+" | "+project.getBuild().getOutputDirectory());
            System.out.println("Source OUT | "+sourceDirectory.exists()+" | "+sourceDirectory.getAbsolutePath());
            System.out.println("Resource OUT | "+resourceDirectory.exists()+" | "+resourceDirectory.getAbsolutePath());
            System.out.println("MANIFEST OUT | "+manifestFile.exists()+" | "+manifestFile.getAbsolutePath());

            project.addCompileSourceRoot(sourceDirectory.getPath());

            System.out.println("DEBUG POSITION 1.5");

            this.manifest.createManifestFile(manifestFile);

            System.out.println("DEBUG POSITION 2");

            System.out.println("DEBUG POSITION 3");

            String basePackage = project.getGroupId()+".loader";
            ResourceLoaderInstaller installer = new ResourceLoaderInstaller(getLog(),resourceLoaderVersion
                    ,new File(mcnativeLoaderLocation),sourceDirectory);

            System.out.println("DEBUG POSITION 4");

            McNativeLoaderCreator creator = new McNativeLoaderCreator(getLog(),mcnativeLoaderVersion,basePackage
                    ,new File(mcnativeLoaderLocation),sourceDirectory,resourceDirectory);

            System.out.println("DEBUG POSITION 5");

            installer.downloadSource();
            creator.downloadSource();

            System.out.println("DEBUG POSITION 6");

            installer.unpackLoader();
            creator.unpackLoader();

            System.out.println("DEBUG POSITION 7");

            creator.renamePackages();

            System.out.println("DEBUG POSITION 8");

            creator.createManifests(loader,manifest);

            System.out.println("DEBUG POSITION 9");

            try {
                FileUtils.copyDirectoryStructure(resourceDirectory,new File(project.getBuild().getOutputDirectory()));
            } catch (IOException e) {
                e.printStackTrace();
                throw new MojoFailureException(e.getMessage());
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
