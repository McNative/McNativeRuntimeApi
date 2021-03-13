package org.mcnative.runtime.api.event.service.local;

import org.mcnative.runtime.api.event.MinecraftEvent;

public interface LocalServiceMaxPlayerCountEvent extends MinecraftEvent {

    int getMaxPlayerCount();

    void setMaxPlayerCount(int count);

}
