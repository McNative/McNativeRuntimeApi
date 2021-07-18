package org.mcnative.runtime.api.service.inventory.gui.context;

import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.service.inventory.gui.Gui;

public class GuiContext {

    private final Gui<?> gui;
    private final ConnectedMinecraftPlayer player;
    private ScreenContext<?> screenContext;

    public GuiContext(Gui<?> gui, ConnectedMinecraftPlayer player) {
        this.gui = gui;
        this.player = player;
    }

    public Gui<?> getGui() {
        return gui;
    }

    public ConnectedMinecraftPlayer getPlayer() {
        return this.player;
    }

    public ScreenContext<?> getScreenContext() {
        return screenContext;
    }

    public boolean hasPageOpen(){
        return screenContext != null;
    }

    public void setScreenContext(ScreenContext<?> screenContext) {
        this.screenContext = screenContext;
    }
}
