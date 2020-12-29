/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:45
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

package org.mcnative.runtime.api.service;

public interface NBTTag {

    byte getByte(String key);

    boolean getBoolean(String key);

    short getShort(String key);

    int getInt(String key);

    long getLong(String key);

    float getFloat(String key);

    double getDouble(String key);

    String getString(String key);

    byte[] getByteArray(String key);

    int[] getIntArray(String key);

    boolean hasKey(String key);

    void setByte(String key, byte value);

    void setBoolean(String key, boolean value);

    void setShort(String key, byte value);

    void setInt(String key, byte value);

    void setLong(String key, byte value);

    void setFloat(String key, byte value);

    void setDouble(String key, byte value);

    void setString(String key, byte value);

    void setByteArray(String key, byte[] value);

    void setIntArray(String key, int[] value);

    static NBTTag newTag(){
        return null;
    }
}
