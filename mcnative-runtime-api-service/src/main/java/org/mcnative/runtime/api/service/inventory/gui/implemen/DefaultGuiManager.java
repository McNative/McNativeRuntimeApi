package org.mcnative.runtime.api.service.inventory.gui.implemen;

import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.service.inventory.gui.Gui;
import org.mcnative.runtime.api.service.inventory.gui.GuiManager;
import org.mcnative.runtime.api.service.inventory.gui.builder.GuiBuilder;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.implemen.builder.DefaultGuiBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class DefaultGuiManager implements GuiManager {

    private final Collection<Gui<?>> guis;

    public DefaultGuiManager() {
        this.guis = new ArrayList<>();
        McNative.getInstance().getLocal().getEventBus().subscribe(ObjectOwner.SYSTEM,new GuiListener());
    }

    @Override
    public Collection<Gui<?>> getGuis() {
        return guis;
    }

    @Override
    public Gui<?> getGui(String name) {
        return Iterators.findOne(this.guis, gui -> gui.getName().equalsIgnoreCase(name));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C extends GuiContext> Gui<C> getGui(String name, Class<C> contextClass) {
        return (Gui<C>) getGui(name);
    }


    @Override
    public <C extends GuiContext> Gui<C> createGui(String name, Class<C> contextClass, Consumer<GuiBuilder<C>> builder) {
        DefaultGuiBuilder<C> guiBuilder = new DefaultGuiBuilder<>(contextClass);
        builder.accept(guiBuilder);
        Gui<C> gui = new DefaultGui<>(name,contextClass,guiBuilder.getPages(),guiBuilder.getDefaultPage());
        this.guis.add(gui);
        return gui;
    }

    @Override
    public boolean deleteGui(Gui<?> gui) {
        return this.guis.remove(gui);
    }

    @Override
    public boolean deleteGui(String name) {
        return deleteGui(getGui(name));
    }
}
