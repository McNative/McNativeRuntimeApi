/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 31.08.19, 16:04
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

import java.util.ArrayList;
import java.util.List;

public class McNativePluginManifest {

    private String name;
    private String category;
    private String version;
    private String main;
    private String description;
    private String website;
    private String author;
    private List<String> depends, softdepends;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getVersion() {
        return version;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getWebsite() {
        return website;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getDepends() {
        return depends;
    }

    public List<String> getSoftdepends() {
        if(softdepends == null) softdepends = new ArrayList<>();
        return softdepends;
    }
}
