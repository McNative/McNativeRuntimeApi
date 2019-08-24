/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:46
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

package org.mcnative.wrapper.loader;

import net.prematic.libraries.document.Document;
import net.prematic.libraries.document.type.DocumentFileType;
import net.prematic.libraries.utility.http.HttpClient;
import net.prematic.libraries.utility.http.HttpResult;
import net.prematic.libraries.utility.map.Pair;

import java.io.File;

public class McNativeLoader {

    private static final String MCNATIVE_URL_VERSION = "http://localhost/mcnative/version";

    public static boolean isAvailable(){
        return false;
    }

    public static boolean isInstalled(){

    }

    public static boolean isNewestVersion(){

    }

    public static String getVersion(){

    }

    public static File install(String platformName){
        if(isInstalled()){
            if(isNewestVersion()){

            }
        }else{

        }
        /*
            - Check version
            - Download



         */



        return null;//Install location
    }


    public Pair<String,Integer> getLatestVersion(){
        HttpClient client = new HttpClient();
        client.setUrl(MCNATIVE_URL_VERSION);
        HttpResult result = client.connect();
        Document document = result.getContent(DocumentFileType.JSON.getReader());
        result.close();
        return new Pair<String, Integer>(document.getString("latest.name"),document.getInt("latest.build"));
    }

    public static void checkVersion(String cu){

    }

}
