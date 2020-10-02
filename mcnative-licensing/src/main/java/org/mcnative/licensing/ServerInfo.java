package org.mcnative.licensing;

import java.io.File;

public class ServerInfo {

    private final File licenseLocation;
    private final String licenseServer;
    private final String serverId;
    private final String serverSecret;

    public ServerInfo(File licenseLocation,String licenseServer,String serverId, String serverSecret) {
        this.licenseLocation = licenseLocation;
        this.licenseServer = licenseServer;
        this.serverId = serverId;
        this.serverSecret = serverSecret;
    }

    public File getLicenseLocation() {
        return licenseLocation;
    }

    public String getLicenseServer() {
        return licenseServer;
    }

    public String getServerId() {
        return serverId;
    }

    public String getServerSecret() {
        return serverSecret;
    }
}
