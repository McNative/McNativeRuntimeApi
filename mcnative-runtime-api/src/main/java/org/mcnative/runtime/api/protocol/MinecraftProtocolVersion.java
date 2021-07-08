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

package org.mcnative.runtime.api.protocol;

/**
 * Each Minecraft edition has several versions. This enumeration is used to separate the different versions provide
 * support for old and new implementations
 */
public enum MinecraftProtocolVersion {

    UNKNOWN(-1,"Unknown (Maybe not supported)",MinecraftEdition.UNKNOWN),

    /** Java Edition */

    JE_1_17_1(756,"1.17.1",MinecraftEdition.JAVA),
    JE_1_17(755,"1.17",MinecraftEdition.JAVA),
    JE_1_16_4(754,"1.16.4",MinecraftEdition.JAVA),
    JE_1_16_3(753,"1.16.3",MinecraftEdition.JAVA),
    JE_1_16_2(751,"1.16.2",MinecraftEdition.JAVA),
    JE_1_16_1(736,"1.16.1",MinecraftEdition.JAVA),
    JE_1_16(735,"1.16",MinecraftEdition.JAVA),
    JE_1_15_2(578,"1.15.2",MinecraftEdition.JAVA),
    JE_1_15_1(575,"1.15.1",MinecraftEdition.JAVA),
    JE_1_15(573,"1.15",MinecraftEdition.JAVA),
    JE_1_14_4(498,"1.14.4",MinecraftEdition.JAVA),
    JE_1_14_3(490,"1.14.3",MinecraftEdition.JAVA),
    JE_1_14_2(485,"1.14.2",MinecraftEdition.JAVA),
    JE_1_14_1(480,"1.14.1",MinecraftEdition.JAVA),
    JE_1_14(477,"1.14",MinecraftEdition.JAVA),
    JE_1_13_2(404,"1.13.2",MinecraftEdition.JAVA),
    JE_1_13_1(401,"1.13.1",MinecraftEdition.JAVA),
    JE_1_13(393,"1.13",MinecraftEdition.JAVA),
    JE_1_12_2(340,"1.12.2",MinecraftEdition.JAVA, true),
    JE_1_12_1(338,"1.12.1",MinecraftEdition.JAVA, true),
    JE_1_12(335,"1.12",MinecraftEdition.JAVA, true),
    JE_1_11_2(316,"1.11.2",MinecraftEdition.JAVA, true),
    JE_1_11(315,"1.11",MinecraftEdition.JAVA, true),
    JE_1_10(210,"1.10",MinecraftEdition.JAVA, true),
    JE_1_9_4(110,"1.9.4",MinecraftEdition.JAVA, true),
    JE_1_9_2(109,"1.9.2",MinecraftEdition.JAVA, true),
    JE_1_9_1(108,"1.9.1",MinecraftEdition.JAVA, true),
    JE_1_9(107,"1.9",MinecraftEdition.JAVA, true),
    JE_1_8(47,"1.8",MinecraftEdition.JAVA, true),
    JE_1_7_10(5,"1.7.10",MinecraftEdition.JAVA, true),
    JE_1_7_5(4,"1.7.5",MinecraftEdition.JAVA, true),
    JE_1_7(3,"1.7",MinecraftEdition.JAVA, true),

    /** Bedrock Edition */

    BE_1_12(361,"1.12",MinecraftEdition.BEDROCK),
    BE_1_10(-1,"1.10",MinecraftEdition.BEDROCK),
    BE_1_9(-1,"1.9",MinecraftEdition.BEDROCK),
    BE_1_2(-1,"1.2",MinecraftEdition.BEDROCK),
    BE_1_1(-1,"1.1",MinecraftEdition.BEDROCK);

    private final int number;
    private final String name;
    private final MinecraftEdition edition;
    private final boolean legacy;

    MinecraftProtocolVersion(int number, String name, MinecraftEdition edition) {
        this(number, name, edition, false);
    }

    MinecraftProtocolVersion(int number, String name, MinecraftEdition edition, boolean legacy) {
        this.number = number;
        this.name = name;
        this.edition = edition;
        this.legacy = legacy;
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

    public boolean isSameEdition(MinecraftProtocolVersion version){
        return this.getEdition() == version.getEdition();
    }

    public boolean isNewer(MinecraftProtocolVersion newerVersion){
        return isSameEdition(newerVersion) && newerVersion.getNumber() < getNumber();
    }

    public boolean isOlder(MinecraftProtocolVersion newerVersion){
        return isSameEdition(newerVersion) && newerVersion.getNumber() > getNumber();
    }

    public boolean isNewerOrSame(MinecraftProtocolVersion newerVersion){
        return isSameEdition(newerVersion) && newerVersion.getNumber() <= getNumber();
    }

    public boolean isOlderOrSame(MinecraftProtocolVersion newerVersion){
        return isSameEdition(newerVersion) && newerVersion.getNumber() >= getNumber();
    }

    public boolean isLegacy() {
        return this.legacy;
    }

    public static MinecraftProtocolVersion of(MinecraftEdition edition, int number){
        for (MinecraftProtocolVersion value : values()) if(value.getEdition() == edition && value.getNumber() == number) return value;
        return UNKNOWN;
    }

    public static MinecraftProtocolVersion getLatest(MinecraftEdition edition){
         if(edition == MinecraftEdition.JAVA){
             return JE_1_16_4;
         }else{
             return BE_1_12;
         }
    }
}
