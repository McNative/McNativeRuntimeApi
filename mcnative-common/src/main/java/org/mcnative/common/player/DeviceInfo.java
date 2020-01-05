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

package org.mcnative.common.player;

public class DeviceInfo {

    public static DeviceInfo JAVA = new DeviceInfo("Java","Java",OperatingSystem.UNKNOWN, UIType.JAVA);

    private final String name, id;
    private final OperatingSystem operatingSystem;
    private final UIType uiType;

    public DeviceInfo(String name, String id, OperatingSystem operatingSystem, UIType uiType) {
        this.name = name;
        this.id = id;
        this.operatingSystem = operatingSystem;
        this.uiType = uiType;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public UIType getUIType() {
        return uiType;
    }

    public enum OperatingSystem {

        UNKNOWN(),
        ANDROID(),
        IOS(),
        WINDOWS(),
        MAC_OS(),
        XBOX(),
        NINTENDO_SWITCH();

    }

    public enum UIType {

        JAVA(),

        CLASSIC(),

        POCKET();

    }
}
