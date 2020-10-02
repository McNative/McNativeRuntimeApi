package org.mcnative.service.inventory.gui.components.controls.listview;

import org.mcnative.service.inventory.Inventory;
import org.mcnative.service.inventory.gui.components.FormComponent;
import org.mcnative.service.inventory.item.ItemStack;

import java.util.List;

public class ListView<T> implements FormComponent {

    private final ItemProvider<T> itemProvider;
    private final ItemBuilder<T> itemBuilder;

    public ListView(ItemProvider<T> itemProvider, ItemBuilder<T> itemBuilder) {
        this.itemProvider = itemProvider;
        this.itemBuilder = itemBuilder;
    }

    @Override
    public void render(Inventory inventory, int start, int end) {
        int amount = end-start;
        List<T> items = itemProvider.getItems(1,amount);

        if(items.size() > amount) throw new IllegalArgumentException("Item list is to long");

        int index = start;
        for (T item : items) {
            ItemStack result = itemBuilder.create(item);
            inventory.setItem(index,result);
            index++;
        }

        for (int i = index; i < end; i++) {
            inventory.remove(index);
        }
    }

    public static <T> ListViewBuilder<T> newBuilder(Class<T> itemClass){
        return new ListViewBuilder<>();
    }

}
