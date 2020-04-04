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
    private MessageComponent<?> currentComponent;
    private MessageComponent<?>  mainComponent;

    private TextBuilder parent;

    public TextBuilder() {
        this(new MessageComponentSet(),null);
    }

    public TextBuilder(MessageComponent<?>  mainComponent, TextBuilder parent) {
        this.mainComponent = mainComponent;
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

    public TextBuilder reset(){
        return color(TextColor.RESET).blank();
    }

    public TextBuilder blank(){
        return text("");
    }

    public TextBuilder insertion(String insertion){
        if(currentComponent == null) throw new IllegalArgumentException("No component for insertion specified.");
        getCurrentAsChatComponent().setInsertion(insertion);
        return this;
    }

    public TextBuilder text(String text) {
        return createComponent(new TextComponent(text));
    }

    public TextBuilder keybind(String key){
        return createComponent(new KeybindComponent(key));
    }

    public TextBuilder score(String objective, String entity, String value){
        return createComponent(new ScoreComponent(objective,entity,value));
    }

    public TextBuilder translation(String key){
        return createComponent(new TranslationComponent(key));
    }

    public TextBuilder messageKey(String key){
        throw new IllegalArgumentException(); //return createComponent(new MessageKeyComponent(key));
    }

    public TextBuilder onClick(Runnable runnable){
        return onClick(ClickAction.EXECUTE,runnable);
    }

    public TextBuilder onClick(Consumer<MinecraftPlayer> consumer){
        return onClick(ClickAction.EXECUTE,consumer);
    }

    public TextBuilder onClick(ClickAction action, Object value){
        if(currentComponent == null) throw new IllegalArgumentException("No component for clicking specified.");
        this.getCurrentAsChatComponent().onClick(action,value);
        return this;
    }

    public TextBuilder onHover(HoverAction action, String hover){
        if(currentComponent == null) throw new IllegalArgumentException("No component for hovering specified.");
        this.getCurrentAsChatComponent().onHover(action,hover);
        return this;
    }

    public TextBuilder include(TextBuilder builder){
        return include((ChatComponent<?>) builder.build());
    }

    public TextBuilder include(Consumer<TextBuilder> include){
        TextBuilder builder = new TextBuilder(null,null);
        include.accept(builder);
        return include((ChatComponent<?>) builder.build());
    }

    public TextBuilder include(ChatComponent<?> component){
        return createComponent(component);
    }

    public TextBuilder include(){
        return new TextBuilder(null,this);
    }

    public TextBuilder out(){
        if(parent == null) throw new IllegalArgumentException("No parent builder defined");
        return parent.include((ChatComponent<?>) build());
    }

    public MessageComponent<?> build(){
        return mainComponent!=null?mainComponent:new TextComponent();
    }

    private ChatComponent<?> getCurrentAsChatComponent(){
        if(currentComponent instanceof ChatComponent) return (ChatComponent<?>) currentComponent;
        else throw new IllegalArgumentException("Component is not a chat component");
    }

    private TextBuilder createComponent(ChatComponent<?> component){
        if(this.currentComponent != null) this.mainComponent.addExtra(component);
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
}
