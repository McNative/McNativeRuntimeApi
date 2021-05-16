package org.mcnative.runtime.api.service.inventory.gui;

import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.player.OnlineMinecraftPlayer;
import org.mcnative.runtime.api.service.event.player.MinecraftPlayerJoinEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
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

import java.util.concurrent.TimeUnit;

public class Test {

    public static void test() {
        ItemStack itemStack = ItemStack.newItemStack(Material.STONE).setDisplayName("").getData(CrossbowItemData.class, damageAble -> damageAble.);

        DamageAble damageAble = (DamageAble) itemStack.getData();
        damageAble.setDamage(100);
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


                elements.addElement(Slots.line(0), new BasicElement<PageContext<TestContext>>(slots) {
                    @Override
                    public void handleClick(PageContext<TestContext> context, MinecraftPlayerInventoryClickEvent event) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("Slot: "+event.getSlot());
                    }

                    @Override
                    protected ItemStack create(PageContext<TestContext> context) {
                        return ItemStack.newItemStack(Material.STONE)
                                .setDisplayName(handleStream(context.getPlayer().getDisplayName()));
                    }



                });
                elements.addElement(Slots.slot(9), new BasicElement<TestContext>(slots) {

                    @Override
                    public void handleClick(TestContext context, MinecraftPlayerInventoryClickEvent event) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("Navigation 1");
                    }
                    @Override
                    protected ItemStack create(TestContext context) {
                        return ItemStack.newItemStack(Material.GRASS)
                                .setDisplayName("§5Navigation 1");
                    }
                });
                elements.addElement(Slots.slot(10), new BasicElement<TestContext>(slots) {

                    @Override
                    public void handleClick(TestContext context, MinecraftPlayerInventoryClickEvent event) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("Navigation 2");
                    }

                    @Override
                    protected ItemStack create(TestContext context) {
                        return ItemStack.newItemStack(Material.GRASS)
                                .setLore("Test lore","lost")
                                .setDisplayName("Navigation 2");
                    }
                });
                elements.addElement(Slots.lines(2,3), new ListPane<>(
                        TestContext::getOnlinePlayers
                        ,new DynamicElement<TestContext, OnlineMinecraftPlayer>() {
                    @Override
                    protected ItemStack create(TestContext context, OnlineMinecraftPlayer value) {
                        return ItemStack.newItemStack(Material.CAKE)
                                .setDisplayName(value.getName());
                    }

                    @Override
                    public void handleClick(PageContext<TestContext> context, MinecraftPlayerInventoryClickEvent event, OnlineMinecraftPlayer value) {
                        context.getPlayer().sendMessage("Player " + value.getName() + " is online");
                        context.getGuiContext()
                    }

                }));
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

    public static class TestContext implements GuiContext {

        private final ConnectedMinecraftPlayer player;
        private final ListSource<OnlineMinecraftPlayer> onlinePlayers;

        public TestContext(ConnectedMinecraftPlayer player){
            this.player = player;
            this.onlinePlayers = new ArrayListSource<>(McNative.getInstance().getLocal().getOnlinePlayers());
        }

        @Override
        public ConnectedMinecraftPlayer getPlayer() {
            return player;
        }

        public ListSource<OnlineMinecraftPlayer> getOnlinePlayers() {
            return onlinePlayers;
        }
    }

}
