package org.mcnative.runtime.api;

public interface ServerPerformance {

    float[] getRecentTps();

    int getUsedMemory();

    float getCpuUsage();
}
