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

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Base64;
import java.util.Enumeration;

public class LicenseManager {

    private final URL verifyUrl;
    private final URL refreshUrl;

    public LicenseManager(URL verifyUrl, URL refreshUrl) {
        this.verifyUrl = verifyUrl;
        this.refreshUrl = refreshUrl;
    }

    public License verifyByKey(File keyFile){
        return null;//return verifyByKey(FileUtil.readContent(keyFile));
    }

    public License verifyByUserId(String userId) throws Exception{
        HttpURLConnection connection = (HttpURLConnection) verifyUrl.openConnection();

        connection.addRequestProperty("Verify-Method","MCNATIVE-USER-ID");
        connection.addRequestProperty("Verify-Value",userId);
        connection.addRequestProperty("Verify-Device",getDeviceId());

        connection.setDoInput(true);
        try(InputStream stream = connection.getInputStream()){
            if(connection.getResponseCode() == 200){
                try{
                    byte[] content = new byte[stream.available()];
                    stream.read(content);
                    return License.read(new String(content));
                }catch (IOException exception){
                    exception.printStackTrace();
                }
                return null;
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    public License refresh(License license) throws Exception{

        HttpURLConnection connection = (HttpURLConnection) refreshUrl.openConnection();
        connection.addRequestProperty("License-Id",license.getLicenseId().toString());
        connection.addRequestProperty("License-Token",license.getRefreshToken());
        connection.addRequestProperty("License-Device",getDeviceId());

        connection.setDoInput(true);
        /*
        try(InputStream stream = connection.getInputStream()){
            if(connection.getResponseCode() == 200){
                return License.read(stream);
            }
        }catch (Exception exception){

        }
         */

        return null;
    }

    public String getDeviceId(){
        try{
            ByteBuf buffer = Unpooled.directBuffer();
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()){
                NetworkInterface networkInterface = interfaces.nextElement();
                if(networkInterface.getHardwareAddress() != null){
                    buffer.writeBytes(networkInterface.getHardwareAddress());
                }
                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    buffer.writeShort(interfaceAddress.getNetworkPrefixLength());
                    buffer.writeBytes(interfaceAddress.getAddress().getAddress());
                }
            }
            byte[] result = new byte[buffer.readableBytes()];
            buffer.readBytes(result);
            return Base64.getEncoder().encodeToString(result);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

}
