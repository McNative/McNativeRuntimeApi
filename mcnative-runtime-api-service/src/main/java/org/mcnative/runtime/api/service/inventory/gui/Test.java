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
import org.mcnative.runtime.api.service.inventory.gui.element.DynamicElement;
import org.mcnative.runtime.api.service.inventory.gui.implemen.DefaultGuiManager;
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
            builder.createPage("test", 36, elements -> {

                elements.addElement(new BasicElement<TestContext, PageContext<TestContext>>(Slots.line(0)) {

                    @Override
                    protected ItemStack create(PageContext<TestContext> context) {
                        return ItemStack.newItemStack(Material.ACACIA_BUTTON)
                                .setDisplayName(context.root().getPlayer().getName());
                    }
                });


                elements.addElement(new BasicElement<TestContext, PageContext<TestContext>>(Slots.line(0)) {
                    @Override
                    public void handleClick(PageContext<TestContext> context, MinecraftPlayerInventoryClickEvent event) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("Slot: "+event.getSlot());
                    }

                    @Override
                    protected ItemStack create(PageContext<TestContext> context) {
                        return ItemStack.newItemStack(Material.STONE)
                                .setDisplayName(context.root().getPlayer().getDisplayName());
                    }
                });

                elements.addElement(new BasicElement<TestContext, PageContext<TestContext>>(Slots.slot(9)) {

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

                elements.addElement(new BasicElement<TestContext, PageContext<TestContext>>(Slots.slot(10)) {

                    @Override
                    public void handleClick(PageContext<TestContext> context, MinecraftPlayerInventoryClickEvent event) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("Navigation 2");
                    }

                    @Override
                    protected ItemStack create(PageContext<TestContext> context) {
                        return ItemStack.newItemStack(Material.GRASS)
                                .setLore("Test lore","lost")
                                .setDisplayName("Navigation 2");
                    }
                });

                elements.addElement(new ListPane<>(testContextPageContext -> new ArrayListSource<>(Arrays.asList(1, 5, 4, 2, 8)),
                        new DynamicElement<TestContext, PageContext<TestContext>, Integer>(Slots.lines(2,3)) {
                    @Override
                    protected ItemStack create(PageContext<TestContext> context, Integer value) {
                        return ItemStack.newItemStack(Material.CAKE)
                                .setDisplayName("§6"+value);
                    }

                    @Override
                    public void handleClick(PageContext<TestContext> context, MinecraftPlayerInventoryClickEvent event, Integer value) {
                        context.root().getPlayer().sendMessage("Int " + value + " is online");
                    }
                }, Slots.lines(2,3)));


                /*elements.addElement(new ListPane<TestContext, PageContext<TestContext>>(TestContext::getOnlinePlayers ,new DynamicElement<TestContext, OnlineMinecraftPlayer>() {
                    @Override
                    protected ItemStack create(PageContext<TestContext> context, OnlineMinecraftPlayer value) {
                        return ItemStack.newItemStack(Material.CAKE)
                                .setDisplayName(value.getName());
                    }

                    @Override
                    public void handleClick(PageContext<TestContext> context, MinecraftPlayerInventoryClickEvent event, OnlineMinecraftPlayer value) {
                        context.getPlayer().sendMessage("Player " + value.getName() + " is online");
                        context.getGuiContext()
                    }

                }, Slots.lines(2,3)));*/
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

        public TestContext(ConnectedMinecraftPlayer player) {
            super(player);
            this.player = player;
        }

        @Override
        public ConnectedMinecraftPlayer getPlayer() {
            return player;
        }
    }

}
