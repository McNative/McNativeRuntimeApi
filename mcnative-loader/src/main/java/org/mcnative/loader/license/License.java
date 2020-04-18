/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 13.04.20, 20:37
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

package org.mcnative.loader.license;

import net.pretronic.libraries.document.type.DocumentFileType;

import java.time.LocalDateTime;
import java.util.UUID;

public class License {

    private final UUID licenseId;
    private final UUID resourceId;

    private final String deviceId;
    private final String secret;

    private final String refreshToken;
    private LocalDateTime refreshTime;

    private LocalDateTime Expiry;

    private final String licensedTo;
    private final String signature;

    public License(UUID licenseId, UUID resourceId, String deviceId, String secret, String refreshToken, LocalDateTime refreshTime, LocalDateTime expiry, String licensedTo, String signature) {
        this.licenseId = licenseId;
        this.resourceId = resourceId;
        this.deviceId = deviceId;
        this.secret = secret;
        this.refreshToken = refreshToken;
        this.refreshTime = refreshTime;
        this.Expiry = expiry;
        this.licensedTo = licensedTo;
        this.signature = signature;
    }

    public UUID getLicenseId() {
        return licenseId;
    }

    public UUID getResourceId() {
        return resourceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getSecret() {
        return secret;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public LocalDateTime getRefreshTime() {
        return refreshTime;
    }

    public LocalDateTime getExpiry() {
        return Expiry;
    }

    public String getLicensedTo() {
        return licensedTo;
    }

    public String getSignature() {
        return signature;
    }

    public static License read(String input){
        return DocumentFileType.JSON.getReader().read(input).getAsObject(License.class);
    }
}
