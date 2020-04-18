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

import javax.net.ssl.*;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class Test {//AAh/AAABAIAAAAAAAAAAAAAAAAAAAAABsFIWpzlTABjAqAF1AED+gAAAAAAAAADGbsRyvbKpsFIWpzlUAED+gAAAAAAAAH2HvzudrrMCslIWpzlTAED+gAAAAAAAAIVjRe/zsvzpAArNMnlKABjAqAEjAED+gAAAAAAAAMWkE65CEMmY8lIWpzlTAED+gAAAAAAAAOUXk6PdoGFXAP/449FOAED+gAAAAAAAAJX/mBe/eLSJ

    public static void main(String[] args) throws Exception{
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    @Override
                    public void checkClientTrusted(X509Certificate[] arg0, String arg1){
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] arg0, String arg1){
                    }

                }
        };

        SSLContext sc=null;
        try {
            sc = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        // Create all-trusting host name verifier
        HostnameVerifier validHosts = new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        };
        // All hosts will be valid
        HttpsURLConnection.setDefaultHostnameVerifier(validHosts);


        LicenseManager manager = new LicenseManager(new URL("https://localhost:44367/licensing/v1/edda82b3-344b-418a-a935-e507a1f9a9fc/verify/")
                ,new URL("https://localhost:44367/licensing/v1/edda82b3-344b-418a-a935-e507a1f9a9fc/refresh/"));

        License license = manager.verifyByUserId("xVuyHSgRwC4^7Ni7TDIz^V2QHf7SJYolQSm)Ypgy#hK");
    }

}
