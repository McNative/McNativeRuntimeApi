package org.mcnative.runtime.api.service.inventory.gui;

import net.pretronic.dkfriends.api.player.friend.Friend;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.player.OnlineMinecraftPlayer;
import org.mcnative.runtime.api.service.event.player.MinecraftPlayerJoinEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.inventory.Inventory;
import org.mcnative.runtime.api.service.inventory.Slots;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.context.PageContext;
import org.mcnative.runtime.api.service.inventory.gui.element.BasicElement;
import org.mcnative.runtime.api.service.inventory.gui.implemen.DefaultGuiManager;
import org.mcnative.runtime.api.service.inventory.gui.pane.ArrayListPaginationSource;
import org.mcnative.runtime.api.service.inventory.gui.pane.ArrayListSource;
import org.mcnative.runtime.api.service.inventory.gui.pane.ListPane;
import org.mcnative.runtime.api.service.inventory.gui.pane.ListSource;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;
import org.mcnative.runtime.api.service.inventory.item.data.CrossbowItemData;
import org.mcnative.runtime.api.service.inventory.item.data.DamageAble;
import org.mcnative.runtime.api.service.inventory.item.material.Material;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Test {

    public static void test() {


    }

    public static void execute(){

        GuiManager manager = new DefaultGuiManager();
        Gui<TestContext> gui = manager.createGui("Test", TestContext.class, builder -> {
            builder.setDefaultPage("test");
            builder.createPage("test", 54, testContextPageContext -> "§5"+testContextPageContext.root().getPlayer().getName(), elements -> {

                elements.addElement(new BasicElement<TestContext, PageContext<TestContext>>(Slots.line(0)) {

                    @Override
                    protected ItemStack create(PageContext<TestContext> context) {
                        return ItemStack.newItemStack(Material.STONE)
                                .setDisplayName(context.root().getPlayer().getName());
                    }
                });


                elements.addElement(new BasicElement<TestContext, PageContext<TestContext>>(Slots.line(1)) {
                    @Override
                    public void handleClick(PageContext<TestContext> context, MinecraftPlayerInventoryClickEvent event) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("Slot: " + event.getSlot());
                    }

                    @Override
                    protected ItemStack create(PageContext<TestContext> context) {
                        return ItemStack.newItemStack(Material.STONE)
                                .setDisplayName(context.root().getPlayer().getDisplayName());
                    }
                });

                elements.addElement(new BasicElement<TestContext, PageContext<TestContext>>(Slots.slot(18)) {

                    @Override
                    public void handleClick(PageContext<TestContext> context, MinecraftPlayerInventoryClickEvent event) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("Navigation 1");
                    }

                    @Override
                    protected ItemStack create(PageContext<TestContext> context) {
                        return ItemStack.newItemStack(Material.GRASS)
                                .setDisplayName("§5Navigation 1");
                    }
                });

                elements.addElement(new BasicElement<TestContext, PageContext<TestContext>>(Slots.slot(19)) {

                    @Override
                    public void handleClick(PageContext<TestContext> context, MinecraftPlayerInventoryClickEvent event) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("Navigation 2");
                    }

                    @Override
                    protected ItemStack create(PageContext<TestContext> context) {
                        return ItemStack.newItemStack(Material.GRASS)
                                .setLore("Test lore", "lost")
                                .setDisplayName("Navigation 2");
                    }
                });

                elements.addElement(new ListPane<TestContext, PageContext<TestContext>, Integer>(pageContext -> pageContext.root().source, Slots.lines(3)) {
                    @Override
                    protected ItemStack create(PageContext<TestContext> context, int slot, Integer value) {
                        return ItemStack.newItemStack(Material.CAKE)
                                .setDisplayName("§6" + value);
                    }

                    @Override
                    public void handleClick(PageContext<TestContext> context, MinecraftPlayerInventoryClickEvent even, Integer value) {
                        context.root().getPlayer().sendMessage("Int " + value + " is online");
                    }
                });
                elements.addElement(new BasicElement<TestContext, PageContext<TestContext>>(Slots.slot(36)) {

                    @Override
                    public void handleClick(PageContext<TestContext> context, MinecraftPlayerInventoryClickEvent event) {
                        context.root().source.previousPage();
                    }

                    @Override
                    protected ItemStack create(PageContext<TestContext> context) {
                        return ItemStack.newItemStack(Material.GRASS).setDisplayName("Previous Page");
                    }
                });

                elements.addElement(new BasicElement<TestContext, PageContext<TestContext>>(Slots.slot(37)) {

                    @Override
                    public void handleClick(PageContext<TestContext> context, MinecraftPlayerInventoryClickEvent event) {
                        context.root().source.nextPage();
                    }

                    @Override
                    protected ItemStack create(PageContext<TestContext> context) {
                        return ItemStack.newItemStack(Material.GRASS).setDisplayName("Next Page");
                    }
                });
            });
        });

        McNative.getInstance().getLocal().getEventBus()
                .subscribe(ObjectOwner.SYSTEM,MinecraftPlayerJoinEvent.class, o -> {
                    McNative.getInstance().getScheduler().createTask(ObjectOwner.SYSTEM)
                            .delay(2, TimeUnit.SECONDS)
                            .execute(() -> {
                                try {
                                    gui.open(o.getPlayer().getAsConnectedPlayer());
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            });
                });
    }

    public static class TestContext extends GuiContext {

        private final ConnectedMinecraftPlayer player;
        private final ArrayListPaginationSource<Integer> source;

        public TestContext(ConnectedMinecraftPlayer player) {
            super(player);
            this.player = player;
            source = new ArrayListPaginationSource<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16),9);
        }

        @Override
        public ConnectedMinecraftPlayer getPlayer() {
            return player;
        }
    }

}
