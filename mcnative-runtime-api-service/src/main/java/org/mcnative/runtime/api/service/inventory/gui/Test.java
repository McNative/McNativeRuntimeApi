package org.mcnative.runtime.api.service.inventory.gui;

import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.runtime.api.McNative;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.service.event.player.MinecraftPlayerJoinEvent;
import org.mcnative.runtime.api.service.event.player.inventory.MinecraftPlayerInventoryClickEvent;
import org.mcnative.runtime.api.service.inventory.Slots;
import org.mcnative.runtime.api.service.inventory.gui.context.GuiContext;
import org.mcnative.runtime.api.service.inventory.gui.element.BasicElement;
import org.mcnative.runtime.api.service.inventory.gui.implemen.DefaultGuiManager;
import org.mcnative.runtime.api.service.inventory.item.ItemStack;
import org.mcnative.runtime.api.service.inventory.item.material.Material;

import java.util.concurrent.TimeUnit;

public class Test {


    public static void execute(){

        GuiManager manager = new DefaultGuiManager();
        Gui<TestContext> gui = manager.createGui("Test", TestContext.class, builder -> {
            builder.setDefaultPage("test");
            builder.createPage("test", 18, elements -> {

                elements.addElement(Slots.line(0), new BasicElement<TestContext>() {
                    @Override
                    public void handleClick(TestContext context, MinecraftPlayerInventoryClickEvent event, Void value) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("Slot: "+event.getSlot());
                    }

                    @Override
                    protected ItemStack create(TestContext context) {
                        return ItemStack.newItemStack(Material.STONE).setDisplayName(context.getPlayer().getDisplayName());
                    }


                });
                elements.addElement(Slots.slot(9), new BasicElement<TestContext>() {

                    @Override
                    public void handleClick(TestContext context, MinecraftPlayerInventoryClickEvent event, Void value) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("Navigation 1");
                    }
                    @Override
                    protected ItemStack create(TestContext context) {
                        return ItemStack.newItemStack(Material.GRASS)
                                .setDisplayName("§5Navigation 1");
                    }
                });
                elements.addElement(Slots.slot(10), new BasicElement<TestContext>() {

                    @Override
                    public void handleClick(TestContext context, MinecraftPlayerInventoryClickEvent event, Void value) {
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

        public TestContext(ConnectedMinecraftPlayer player){
            this.player = player;
        }

        @Override
        public ConnectedMinecraftPlayer getPlayer() {
            return player;
        }
    }

}
