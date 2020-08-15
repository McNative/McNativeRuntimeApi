/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.07.20, 10:43
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

package org.mcnative.common.serviceprovider.statistics;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.plugin.Plugin;
import net.pretronic.libraries.utility.SystemInfo;
import net.pretronic.libraries.utility.SystemUtil;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.common.McNative;

import java.io.File;
import java.io.InputStream;
import java.net.*;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class McNativeStatisticService {

    private final static String REPORTING_SERVICE_URL = "https://development-redirect-davide.pretronic.net/statistics/report/";

    private final boolean networkReporting;
    private final UUID localReportingId;

    public McNativeStatisticService() {
        this.networkReporting = McNative.getInstance().getPlatform().isProxy();
        localReportingId = loadReportingId();
       // start();
    }

    private UUID loadReportingId(){
        File file = new File("plugins/McNative/lib/runtime.dat");
        if(file.exists()){
            Document document = DocumentFileType.JSON.getReader().read(new File("plugins/McNative/lib/runtime.dat"));
            UUID uuid =  document.getObject("reportingId",UUID.class);
            if(uuid != null) return uuid;
        }
        UUID uuid = UUID.randomUUID();
        Document document = Document.newDocument();
        document.set("reportingId",uuid);
        DocumentFileType.JSON.getWriter().write(new File("plugins/McNative/lib/runtime.dat"),document,false);
        return uuid;
    }

    public void start(){
        sendComputerReport();
        sendServerReport("BOOTSTRAP");

        McNative.getInstance().getScheduler().createTask(ObjectOwner.SYSTEM)
                .delay(5,TimeUnit.SECONDS)
                .interval(30, TimeUnit.SECONDS)
                .async().execute(() -> {
            sendServerStatusReport();
            if(networkReporting){
                sendNetworkReport();
            }
        });
    }

    public void shutdown(){
        //sendServerReport("SHUTDOWN");
    }

    private void sendComputerReport() {
        Document document = Document.newDocument();
        document.set("HardwareId", getHardwareId());

        try{
            InetAddress address = InetAddress.getLocalHost();
            if(address != null){
                document.set("IpAddress",address.getHostAddress());
                NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);
                document.set("MacAddress",buildMacAddress(networkInterface.getHardwareAddress()));
            }
        }catch (Exception ignored){}

        Document operatingSystem = Document.newDocument();
        operatingSystem.set("name",SystemInfo.getOsName());
        operatingSystem.set("version",SystemInfo.getOsVersion());
        document.set("OperatingSystem", operatingSystem);

        Document javaVersion = Document.newDocument();
        javaVersion.set("name",SystemUtil.getJavaVersion());
        javaVersion.set("number",SystemUtil.getJavaBaseVersion());
        document.set("JavaVersion", SystemUtil.getJavaBaseVersion());

        submitReport("computer",document);
    }

    private void sendNetworkReport(){
        Document document = Document.newDocument();
        //document.set("Secret",McNative.getInstance().getMcNativeServerSecret());
        document.set("UniqueId",McNative.getInstance().getNetwork().getIdentifier().getUniqueId());
        document.set("Name",McNative.getInstance().getNetwork().getIdentifier().getName());
        document.set("Technology",McNative.getInstance().getNetwork().getTechnology());
        document.set("MaximumPlayerCount",McNative.getInstance().getNetwork().getMaxPlayerCount());
        document.set("OnlinePlayerCount",McNative.getInstance().getNetwork().getOnlineCount());

        submitReport("network",document);
    }

    private void sendServerReport(String lifecycleState){
        Document document = Document.newDocument();
       // document.set("Secret",McNative.getInstance().getMcNativeServerSecret());
        document.set("Platform",McNative.getInstance().getPlatform().getName());
        document.set("ProtocolVersion",McNative.getInstance().getPlatform().getProtocolVersion().getNumber());
        document.set("Name",McNative.getInstance().getLocal().getIdentifier().getName());
        document.set("UniqueId",McNative.getInstance().getLocal().getIdentifier().getUniqueId());

        document.set("ComputerHardwareId",getHardwareId());
        document.set("IpAddress",McNative.getInstance().getLocal().getAddress().getAddress().getHostAddress());
        document.set("Port",McNative.getInstance().getLocal().getAddress().getPort());
        document.set("plugins",buildPluginReport());
        document.set("LifecycleState",lifecycleState);

        if(McNative.getInstance().isNetworkAvailable()){
            document.set("NetworkId",McNative.getInstance().getNetwork().getIdentifier().getUniqueId());
        }else{
            document.set("ReportingId",localReportingId);
        }

        submitReport("server",document);
    }

    private void sendServerStatusReport(){
        Document document = Document.newDocument();
        //document.set("Secret",McNative.getInstance().getMcNativeServerSecret());
        document.set("MaximumPlayerCount",McNative.getInstance().getLocal().getStatusResponse().getMaxPlayers());
        document.set("OnlinePlayerCount",McNative.getInstance().getLocal().getOnlineCount());

        if(McNative.getInstance().isNetworkAvailable()){
            document.set("NetworkId",McNative.getInstance().getNetwork().getIdentifier().getUniqueId());
            document.set("Name",McNative.getInstance().getLocal().getIdentifier().getName());
        }else{
            document.set("ReportingId",localReportingId);
        }

        submitReport("server-status",document);
    }

    private Document buildPluginReport(){
        Document result = Document.factory().newArrayEntry("plugins");
        for (Plugin<?> plugin : McNative.getInstance().getPluginManager().getPlugins()) {
            Document document = Document.newDocument();
            document.set("Name",plugin.getDescription().getName());
            if(plugin.getDescription().getId() != null){
                document.set("UniqueId",plugin.getDescription().getId());
            }
            document.set("Version",plugin.getDescription().getVersion().getName());
            document.set("Author",plugin.getDescription().getAuthor());
            result.addEntry(document);
        }
        return result;
    }

    private void submitReport(String endpoint, Document document){
        try {
            URL url = new URL(REPORTING_SERVICE_URL+endpoint);
            System.out.println(REPORTING_SERVICE_URL+endpoint);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setInstanceFollowRedirects(false);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", "Minecraft-Server");
            DocumentFileType.JSON.getWriter().write(connection.getOutputStream(),document,false);
            connection.connect();

            System.out.println("----------------");
            System.out.println(DocumentFileType.JSON.getWriter().write(document,true));
            System.out.println("----------------");

            System.out.println(connection.getResponseCode());

            InputStream stream = connection.getErrorStream();
            if (stream == null) {
                stream = connection.getInputStream();
            }
            try (Scanner scanner = new Scanner(stream)) {
                scanner.useDelimiter("\\Z");
                System.out.println(scanner.next());
            }
            System.out.println("-----------------------");

        }catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    public static String getHardwareId(){
        try{
            ByteBuf buffer = Unpooled.directBuffer();
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()){
                NetworkInterface networkInterface = interfaces.nextElement();
                if(networkInterface.getHardwareAddress() != null){
                    buffer.writeBytes(networkInterface.getHardwareAddress());
                    for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                        buffer.writeShort(interfaceAddress.getNetworkPrefixLength());
                        buffer.writeBytes(interfaceAddress.getAddress().getAddress());
                    }
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

    private String buildMacAddress(byte[] mac){
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }
        return sb.toString();
    }

}
