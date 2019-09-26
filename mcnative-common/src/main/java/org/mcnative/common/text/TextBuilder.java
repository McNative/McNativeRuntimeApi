/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 04.08.19 10:44
 *
 * The McNative Project is under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.mcnative.common.text;

import org.mcnative.common.player.MinecraftPlayer;
import org.mcnative.common.text.components.TextComponent;
import org.mcnative.common.text.components.*;
import org.mcnative.common.text.event.ClickAction;
import org.mcnative.common.text.event.HoverAction;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextStyle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class TextBuilder {

    private TextColor color;
    private Set<TextStyle> styling;
    private ChatComponent currentComponent;
    private ChatComponent mainComponent;

    private TextBuilder parent;

    public TextBuilder() {}

    public TextBuilder(TextBuilder parent) {
        this.parent = parent;
    }

    public TextBuilder color(TextColor color){
        this.color = color;
        return this;
    }

    public TextBuilder styling(TextStyle... styling){
        this.styling = new HashSet<>(Arrays.asList(styling));
        return this;
    }


    public TextBuilder text(String text) {
        return createComponent(new TextComponent(text));
    }

    public TextBuilder keybind(String key){
        return createComponent(new KeybindComponent(key));
    }

    public TextBuilder score(String objective, String entity){
        return createComponent(new ScoreComponent(objective,entity));
    }

    public TextBuilder translation(String key){
        return createComponent(new TranslationComponent(key));
    }

    public TextBuilder messageKey(String key){
        return createComponent(new KeyComponent(key));
    }


    public TextBuilder onClick(Runnable runnable){
        return onClick(ClickAction.EXECUTE,runnable);
    }

    public TextBuilder onClick(Consumer<MinecraftPlayer> consumer){
        return onClick(ClickAction.EXECUTE,consumer);
    }

    public TextBuilder onClick(ClickAction action, Object value){
        if(currentComponent == null) throw new IllegalArgumentException("No component for clicking specified.");
        this.currentComponent.onClick(action,value);
        return this;
    }

    public TextBuilder onHover(HoverAction action, String hover){
        if(currentComponent == null) throw new IllegalArgumentException("No component for hovering specified.");
        this.currentComponent.onHover(action,hover);
        return this;
    }

    public TextBuilder include(TextBuilder builder){
        return include(builder.build());
    }

    public TextBuilder include(Consumer<TextBuilder> include){
        TextBuilder builder = new TextBuilder();
        include.accept(builder);
        return include(builder.build());
    }

    public TextBuilder include(ChatComponent component){
        return createComponent(component);
    }

    public TextBuilder include(){
        return new TextBuilder(this);
    }

    public TextBuilder out(){
        if(parent == null) throw new IllegalArgumentException("No parent builder defined");
        return parent.include(build());
    }


    public ChatComponent build(){
        return mainComponent!=null?mainComponent:new TextComponent();
    }


    private TextBuilder createComponent(ChatComponent component){
        if(this.currentComponent != null) this.currentComponent.addExtra(component);
        else this.mainComponent = component;
        this.currentComponent = component;
        if(color != null){
            component.setColor(color);
            color = null;
        }
        if(styling != null){
            component.setStyling(styling);
            styling = null;
        }
        return this;
    }



    //@Todo remove
    public void example(){
        new TextBuilder()
                .color(TextColor.BLUE)
                .text("Hallo du was geht?")
                .onClick(ClickAction.OPEN_URL,"https://google.com");

        new TextBuilder()
                .include(include -> include.color(TextColor.DARK_AQUA).text("[")
                        .color(TextColor.GREEN).styling(TextStyle.BOLD).text("Join")
                        .color(TextColor.DARK_AQUA).text("]")
                ).onClick(ClickAction.RUN_COMMAND,"/join game 1");

        new TextBuilder()
                .include()
                    .color(TextColor.DARK_AQUA).text("[")
                    .color(TextColor.GREEN).styling(TextStyle.BOLD).text("Join")
                    .color(TextColor.DARK_AQUA).text("]")
                .out().onClick(ClickAction.RUN_COMMAND,"/join game 1");
    }

}
