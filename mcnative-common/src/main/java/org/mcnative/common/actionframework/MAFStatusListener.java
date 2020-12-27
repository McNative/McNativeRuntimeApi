package org.mcnative.common.actionframework;

import org.mcnative.actionframework.sdk.client.StatusListener;
import org.mcnative.common.McNative;

public class MAFStatusListener implements StatusListener {

    @Override
    public void onConnect() {
        McNative.getInstance().getLogger().info("[MAF] Connected to McNative Action Framework");
    }

    @Override
    public void onDisconnect() {
        McNative.getInstance().getLogger().info("[MAF] Disconnected from McNative Action Framework");
    }
}
