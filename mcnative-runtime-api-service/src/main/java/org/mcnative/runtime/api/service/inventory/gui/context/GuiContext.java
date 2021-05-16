package org.mcnative.runtime.api.service.inventory.gui.context;

import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;

public class GuiContext {

    private final ConnectedMinecraftPlayer player;
    private PageContext<?> pageContext;

    public GuiContext(ConnectedMinecraftPlayer player) {
        this.player = player;
    }

    public ConnectedMinecraftPlayer getPlayer() {
        return this.player;
    }

    public PageContext<?> getPageContext() {
        return pageContext;
    }

    public void setPageContext(PageContext<?> pageContext) {
        this.pageContext = pageContext;
    }
}
