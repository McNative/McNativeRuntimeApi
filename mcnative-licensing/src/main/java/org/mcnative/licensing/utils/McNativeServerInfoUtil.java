package org.mcnative.licensing.utils;

import org.mcnative.common.McNative;
import org.mcnative.common.McNativeServerIdentifier;
import org.mcnative.common.rollout.RolloutConfiguration;
import org.mcnative.common.rollout.RolloutProfile;
import org.mcnative.licensing.ServerInfo;

import java.io.File;

public class McNativeServerInfoUtil {

    public static ServerInfo getDefaultServerInfo(File basePath,String name){
        RolloutConfiguration configuration = McNative.getInstance().getRolloutConfiguration();
        RolloutProfile profile = configuration.getProfile(name);
        McNativeServerIdentifier identifier = McNative.getInstance().getMcNativeServerId();
        return new ServerInfo(new File(basePath,"license.dat")
                ,"https://"+profile.getServer()+"/license/{resourceId}/checkout"
                ,identifier.getId(),identifier.getSecret());
    }

}
