package org.mcnative.runtime.api.player.client.labymod;

import java.util.List;
import java.util.function.Consumer;
import org.mcnative.runtime.api.player.client.labymod.widget.Widget;

public interface LabyModWidgetScreen {

    <T extends Widget<T>> LabyModWidgetScreen addWidget(String alias, Class<T> widgetClass, Consumer<T> widget);

    LabyModWidgetScreen getLayout(Consumer<WidgetScreenLayout> layout);

    List<Widget<?>> getWidgets();

    void open();

    void close();
}
