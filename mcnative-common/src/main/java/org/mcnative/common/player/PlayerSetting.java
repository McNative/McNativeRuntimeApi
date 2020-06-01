/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 01.06.20, 13:20
 * @web %web%
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

import net.pretronic.libraries.document.Document;

public interface PlayerSetting {

    int getId();

    String getOwner();

    String getKey();

    String getValue();

    Object getObjectValue();

    byte getByteValue();

    int getIntValue();

    long getLongValue();

    double getDoubleValue();

    float getFloatValue();

    boolean getBooleanValue();

    Document getDocumentValue();

    void setValue(Object value);

    boolean equalsValue(Object value);

}
