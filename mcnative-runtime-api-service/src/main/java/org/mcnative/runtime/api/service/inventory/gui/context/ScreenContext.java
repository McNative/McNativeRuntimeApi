package org.mcnative.runtime.api.service.inventory.gui.context;

import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.InventoryOwner;
import org.mcnative.runtime.api.service.inventory.gui.Screen;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;
import org.mcnative.runtime.api.stream.StreamOptional;
import org.mcnative.runtime.api.stream.StreamSubscription;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class ScreenContext<C extends GuiContext> implements InventoryOwner {

    private final static Function<Element<?, ?>,Collection<StreamSubscription<?>>> CREATOR = element -> new ArrayList<>();

    private final C guiContext;
    private Inventory inventory;
    private final Screen<C, ?> page;
    private final Map<Element<C,?>,Collection<StreamSubscription<?>>> subscriptions;

    public ScreenContext(C guiContext, Screen<C, ?> page) {
        this.guiContext = guiContext;
        this.page = page;
        this.subscriptions = new ConcurrentHashMap<>();
    }

    public C root() {
        return guiContext;
    }

    @SuppressWarnings("unchecked")
    public  <T extends ScreenContext<?>> T getRawPageContext() {
        return (T) this;
    }

    public Screen<C, ?> getPage() {
        return page;
    }

    @Override
    public Inventory getLinkedInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public <O> O subscribeAndGet(Element<C,?> element, StreamOptional<O> stream, Consumer<O> runnable){
        Collection<StreamSubscription<?>> subscriptions = this.subscriptions.computeIfAbsent(element, CREATOR);

        for (StreamSubscription<?> subscription : subscriptions) {
            if(subscription.getStream().belongTogether(stream)){
                return stream.get();
            }
        }

        subscriptions.add(stream.subscribe(runnable));
        return stream.get();
    }

    public void clear(Element<C,?> element){
        subscriptions.remove(element);
    }

    public void destroy(){
        for (Collection<StreamSubscription<?>> subscriptions : this.subscriptions.values()){
            for (StreamSubscription<?> subscription : subscriptions) {
                subscription.cancel();
            }
        }
        this.subscriptions.clear();
    }
}
