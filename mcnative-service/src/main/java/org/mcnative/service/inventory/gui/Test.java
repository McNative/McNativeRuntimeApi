package org.mcnative.service.inventory.gui;

import org.mcnative.common.McNative;
import org.mcnative.common.player.ConnectedMinecraftPlayer;
import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.service.inventory.gui.components.controls.listview.ItemProvider;
import org.mcnative.service.inventory.gui.components.controls.listview.ListView;

import java.util.List;

public class Test {


    public void test(){
        ConnectedMinecraftPlayer player = McNative.getInstance().getLocal().getConnectedPlayer("Dkrieger");
        GUI gui = new GUI();

        Page page = new Page("mainPage",6);

       // page.addStaticItem(1);
       // page.addStaticItem(2);
      //  page.addStaticItem(3);
      //  page.addStaticItem(4);

        Page page2 = new Page("page2",6);


        //FriendList -> Friends anzeigt


        ListView<Project> projectListView = page.addComponent(ListView.newBuilder(Project.class)
                .setItemProvider(new ItemProvider<Project>() {
                    @Override
                    public List<Project> getItems(int page, int amount) {
                        return null;
                    }
                }).build());

        //FriendList -> Friends anzeigt


        //GUIView view = gui.create(player);
    }

    public static class Project {

        private final String name;
        private final String description;

        public Project(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }
}
