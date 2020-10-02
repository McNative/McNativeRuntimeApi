package org.mcnative.licensing.platform;

import org.mcnative.common.plugin.MinecraftPlugin;
import org.mcnative.licensing.License;
import org.mcnative.licensing.LicenseVerifier;
import org.mcnative.licensing.ServerInfo;
import org.mcnative.licensing.utils.McNativeServerInfoUtil;

public class McNativeLicenseVerifier {

    public static License verify(MinecraftPlugin plugin, String resourceId, String publicKey){
        ServerInfo serverInfo = McNativeServerInfoUtil.getDefaultServerInfo(plugin.getDataFolder(),plugin.getName());
        return LicenseVerifier.verify(resourceId,publicKey,serverInfo);
    }

    public static License verifyOrCheckout(MinecraftPlugin plugin, String resourceId, String publicKey){
        ServerInfo serverInfo = McNativeServerInfoUtil.getDefaultServerInfo(plugin.getDataFolder(),plugin.getName());
        return LicenseVerifier.verifyOrCheckout(resourceId,publicKey,serverInfo);
    }

}
