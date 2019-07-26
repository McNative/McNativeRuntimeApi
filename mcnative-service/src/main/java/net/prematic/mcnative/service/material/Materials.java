/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 25.07.19 15:58
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

package net.prematic.mcnative.service.material;

public class Materials {

    public final Material AIR = createDefault("Air","air",0);

    public final Material STONE = createDefault("Stone","stone",1);


    private Material createDefault(String name, String textType, int id){
        return createDefault(name,textType, id, (byte) 0,name);
    }

    private Material createDefault(String name, String textType, int id, byte subId){
        return createDefault(name,textType, id, subId,name);
    }

    private Material createDefault(String name, String textType, int id, byte subId, String legacyName){
        return new Material(name,textType,new LegacyData(legacyName,id,subId));
    }
}
