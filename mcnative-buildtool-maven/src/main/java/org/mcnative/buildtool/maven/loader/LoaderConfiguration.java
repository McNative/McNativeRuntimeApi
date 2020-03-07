/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.11.19, 17:18
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

import org.apache.maven.plugins.annotations.Parameter;
import org.mcnative.buildtool.maven.McNativePluginManifest;

public class LoaderConfiguration {

    private String version;

    @Parameter(required = true)
    private String versionUrl;

    @Parameter(required = true)
    private String downloadUrl;

    private McNativePluginManifest plugin;

    public String getVersion() {
        return version;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public McNativePluginManifest getPlugin() {
        return plugin;
    }

    public void setPlugin(McNativePluginManifest plugin) {
        this.plugin = plugin;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
