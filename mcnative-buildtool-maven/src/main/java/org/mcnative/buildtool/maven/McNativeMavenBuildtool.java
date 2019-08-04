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
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Mojo(name="McNative", defaultPhase = LifecyclePhase.GENERATE_RESOURCES,threadSafe = true)
public class McNativeMavenBuildtool extends AbstractMojo {

    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    private Document mcNativeYML;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try{
            Resource yml;


            for (Resource resource : project.getResources()) {
                if(resource.getTargetPath().startsWith("mcnative.")){
                    //resource.get
                }
            }


            createBukkitYML();
            createNukkitYML();

            FileUtils.copyDirectoryStructure(
                    new File("target/generated-resources/mcnative/"),
                    new File(project.getBuild().getOutputDirectory()));
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    private void createBukkitYML(){
        Document document = mcNativeYML.copy().toDocument();
        document.add("main",project.getGroupId()+".mcnative.wrapper.bukkit.McNativeBukkitWrapper");
        DocumentFileType.YAML.getWriter().write(new File("target/generated-resources/mcnative/plugin.yml"),document);
    }

    private void createNukkitYML(){
        Document document = mcNativeYML.copy().toDocument();
        document.add("main",project.getGroupId()+".mcnative.wrapper.bukkit.McNativeNukkitWrapper");
        DocumentFileType.YAML.getWriter().write(new File("target/generated-resources/mcnative/nukkit.yml"),document);
    }



    private void createFile(String name, CharSequence content) throws Exception{
        File destination = new File("target/generated-resources/mcnative/",content.toString());
        destination.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
        writer.write(content.toString());
        writer.close();
    }


}
