/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:44
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

package org.mcnative.common.protocol;

public enum ProtocolVersion {

    BE_1_12(361,"1.12",MinecraftEdition.BEDROCK),


    JE_1_14_4(498,"1.14.4",MinecraftEdition.JAVA),
    JE_1_14_3(490,"1.14.3",MinecraftEdition.JAVA),
    JE_1_14_2(485,"1.14.2",MinecraftEdition.JAVA),
    JE_1_14_1(480,"1.14.1",MinecraftEdition.JAVA),
    JE_1_14(477,"1.14",MinecraftEdition.JAVA),
    JE_1_13_2(404,"1.13.2",MinecraftEdition.JAVA),
    JE_1_13_1(401,"1.13.1",MinecraftEdition.JAVA),
    JE_1_13(393,"1.13",MinecraftEdition.JAVA),
    JE_1_12_2(340,"1.12.2",MinecraftEdition.JAVA),
    JE_1_12_1(338,"1.12.1",MinecraftEdition.JAVA),
    JE_1_12(335,"1.12",MinecraftEdition.JAVA),
    JE_1_11_2(315,"1.11.2",MinecraftEdition.JAVA),
    JE_1_11(315,"1.11",MinecraftEdition.JAVA),
    JE_1_10(210,"1.10.X",MinecraftEdition.JAVA),
    JE_1_9_4(109,"1.9.4",MinecraftEdition.JAVA),
    JE_1_9_2(109,"1.9.2",MinecraftEdition.JAVA),
    JE_1_9_1(108,"1.9.1",MinecraftEdition.JAVA),
    JE_1_9(107,"1.9",MinecraftEdition.JAVA),
    JE_1_8(47,"1.8",MinecraftEdition.JAVA),
    JE_1_7_10(5,"1.7.10",MinecraftEdition.JAVA),
    JE_1_7_5(4,"1.7.5",MinecraftEdition.JAVA),
    JE_1_7(3,"1.7",MinecraftEdition.JAVA);

    private final int number;
    private final String name;
    private final MinecraftEdition edition;

    ProtocolVersion(int number, String name, MinecraftEdition edition) {
        this.number = number;
        this.name = name;
        this.edition = edition;
    }

    public int getNumber() {
        return number;
    }

    public MinecraftEdition getEdition() {
        return edition;
    }

    public String getName() {
        return name;
    }
}
