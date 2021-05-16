package org.mcnative.runtime.api.service.inventory.gui.context;

import net.pretronic.libraries.synchronisation.observer.Observable;
import net.pretronic.libraries.synchronisation.observer.ObserveCallback;
import net.pretronic.libraries.utility.map.Triple;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.gui.element.Element;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class PageContext<G extends GuiContext> {

    private final G guiContext;
    private final Inventory inventory;
    private final Collection<Triple<Element<G, ?, ?>, Observable<?, ?>, ObserveCallback<?,?>>> observables;

    public PageContext(G guiContext, Inventory inventory) {
        this.guiContext = guiContext;
        this.inventory = inventory;
        this.observables = ConcurrentHashMap.newKeySet();
    }

    public G root() {
        return guiContext;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void subscribe(Element<G, ?, ?> element, Observable<?, ?> observable) {
        for (Triple<Element<G, ?, ?>, Observable<?, ?>, ObserveCallback<?, ?>> entry : observables) {
            if(entry.getFirst().equals(element) && entry.getSecond().equals(observable)) return;
        }

        ObserveCallback<?,?> callback = new ObserveCallback<Observable, Object>() {
            @Override
            public void callback(Observable observable, Object o) {
                element.render(getPageContext(),null);
            }
        };

        observable.subscribeObserver(callback);
        observables.add(new Triple<>(element,observable,callback));
    }

    @SuppressWarnings("unchecked")
    private  <P extends PageContext<G> > P getPageContext() {
        return (P) this;
    }
}
