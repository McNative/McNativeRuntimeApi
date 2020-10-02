package org.mcnative.service.inventory.gui.components.controls.listview;

public class ListViewBuilder<T> {

    private ItemProvider<T> itemProvider;
    private ItemBuilder<T> itemBuilder;

    public ListViewBuilder<T> setItemProvider(ItemProvider<T> itemProvider) {
        this.itemProvider = itemProvider;
        return this;
    }

    public ListViewBuilder<T> setItemBuilder(ItemBuilder<T> itemBuilder) {
        this.itemBuilder = itemBuilder;
        return this;
    }

    public ListView<T> build() {
        return new ListView<>(itemProvider, itemBuilder);
    }
}
