package org.mcnative.licensing.platform;

import org.mcnative.common.McNative;
import org.mcnative.licensing.License;
import org.mcnative.licensing.LicenseVerifier;
import org.bukkit.plugin.Plugin;
import org.mcnative.licensing.ServerInfo;
import org.mcnative.licensing.utils.McNativeServerInfoUtil;

import java.lang.reflect.Method;

public class BukkitLicenseVerifier {

    public static License verify(Plugin plugin, String resourceId, String publicKey){
        checkMcNativeAvailable();
        ServerInfo serverInfo = McNativeServerInfoUtil.getDefaultServerInfo(plugin.getDataFolder(),plugin.getName());
        return LicenseVerifier.verify(resourceId,publicKey,serverInfo);
    }

    public static License verifyOrCheckout(Plugin plugin, String resourceId, String publicKey){
        checkMcNativeAvailable();
        ServerInfo serverInfo = McNativeServerInfoUtil.getDefaultServerInfo(plugin.getDataFolder(),plugin.getName());
        return LicenseVerifier.verifyOrCheckout(resourceId,publicKey,serverInfo);
    }

    public static void checkMcNativeAvailable(){
        try{
            Class<?> clazz = Class.forName("org.mcnative.common.McNative");
            Method isAvailable = clazz.getDeclaredMethod("isAvailable");
            if((boolean)isAvailable.invoke(null)) return;
        }catch (Exception ignored){}
        throw new IllegalArgumentException("McNative is not available (To run the plugin without McNative use the LicenseVerifier class)");
    }

}
