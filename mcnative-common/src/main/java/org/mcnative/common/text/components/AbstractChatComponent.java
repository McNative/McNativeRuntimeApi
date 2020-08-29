/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 24.09.19, 20:24
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

package org.mcnative.common.text.components;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.type.DocumentFileType;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.message.language.Language;
import org.mcnative.common.connection.MinecraftConnection;
import org.mcnative.common.protocol.MinecraftProtocolVersion;
import org.mcnative.common.text.Text;
import org.mcnative.common.text.event.ClickAction;
import org.mcnative.common.text.event.HoverAction;
import org.mcnative.common.text.event.TextEvent;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextFont;
import org.mcnative.common.text.format.TextStyle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractChatComponent<T extends AbstractChatComponent<?>> implements ChatComponent<T>{

    private TextColor color;
    private TextFont font;
    private Set<TextStyle> styling;

    private TextEvent<ClickAction> clickEvent;
    private TextEvent<HoverAction> hoverEvent;
    private String insertion;

    private Collection<MessageComponent<?>> extras;

    public AbstractChatComponent(){
        this(null);
    }

    public AbstractChatComponent(TextColor color){
        this(color,new HashSet<>());
    }

    public AbstractChatComponent(TextColor color, Set<TextStyle> styling){
        this.color = color;
        this.styling = styling;
        this.extras = new ArrayList<>();
    }

    @Override
    public TextEvent<ClickAction> getClickEvent() {
        return clickEvent;
    }

    @Override
    public TextEvent<HoverAction> getHoverEvent() {
        return hoverEvent;
    }

    @Override
    public T setClickEvent(TextEvent<ClickAction> event) {
        this.clickEvent = event;
        return (T) this;
    }

    @Override
    public T setHoverEvent(TextEvent<HoverAction> event) {
        this.hoverEvent = event;
        return (T) this;
    }

    @Override
    public String getInsertion() {
        return this.insertion;
    }

    @Override
    public T setInsertion(String insertion) {
        this.insertion = insertion;
        return (T) this;
    }

    @Override
    public Collection<MessageComponent<?>> getExtras() {
        return extras;
    }

    @Override
    public T addExtra(MessageComponent<?> component) {
        extras.add(component);
        return (T) this;
    }

    @Override
    public T removeExtra(MessageComponent<?> component) {
        extras.remove(component);
        return (T) this;
    }

    @Override
    public TextColor getColor() {
        if(color == null) return TextColor.WHITE;
        return color;
    }

    @Override
    public T setColor(TextColor color) {
        this.color = color;
        return (T) this;
    }

    @Override
    public TextFont getFont() {
        return font;
    }

    @Override
    public T setFont(TextFont font) {
        this.font = font;
        return (T) this;
    }

    @Override
    public Set<TextStyle> getStyling() {
        return styling;
    }

    @Override
    public T setStyling(Set<TextStyle> styling) {
        this.styling = styling;
        return (T) this;
    }

    @Override
    public void toPlainText(StringBuilder builder, VariableSet variables, Language language) {
        if(extras != null && !extras.isEmpty()){
            extras.forEach(component -> component.toPlainText(builder, variables,language));
        }
    }

    @Override
    public Document compile(String key, MinecraftConnection connection, MinecraftProtocolVersion version, VariableSet variables, Language language) {
        if(version.isOlder(MinecraftProtocolVersion.JE_1_8)){
            throw new UnsupportedOperationException("Use compileToString for version 1.7.9 or lower");
        }
        if(variables == null) variables = VariableSet.createEmpty();
        Document document = Document.newDocument(key);
        if(isBold()) document.set("bold",true);
        if(isItalic()) document.set("italic",true);
        if(isUnderlined()) document.set("underlined",true);
        if(isStrikeThrough()) document.set("strikethrough",true);
        if(isObfuscated()) document.set("obfuscated",true);
        if(this.color != null) document.set("color",color.compileColor(version));
        if(this.font != null && version.isNewerOrSame(MinecraftProtocolVersion.JE_1_16)) document.set("font",font);
        if(insertion != null) document.set("insertion",variables.replace(insertion));
        if(this.clickEvent != null){
            Document event = Document.newDocument();
            if(clickEvent.getAction().isDirectEvent()){
                event.set("action",clickEvent.getAction().getName().toLowerCase());
                event.set("value",clickEvent.getValue().toString());
            }else{
                event.set("action","run_command");
                event.set("value","mcnOnTextClick");//@Todo add custom event managing / Todo Register temp command
            }
            document.set("clickEvent",event);
        }
        if(this.hoverEvent != null){
            Document event = Document.newDocument();
            event.set("action",hoverEvent.getAction().getName().toLowerCase());

            ChatComponent<?> value;
            if(hoverEvent.getValue() instanceof ChatComponent) value = ((ChatComponent<?>) hoverEvent.getValue());
            else value = new TextComponent(hoverEvent.getValue().toString());
            event.entries().add(value.compile("value",connection,version, variables,language));
            //event.add("value",value.compile(connection, variables, language,variables,language));

            document.set("hoverEvent",event);
        }
        if(extras != null && !extras.isEmpty()){
            Document[] extras = new Document[this.extras.size()];
            int index = 0;
            for (MessageComponent<?> extra : this.extras) {
                extras[index] = extra.compile("value",connection,version, variables,language);
                index++;
            }
            document.set("extra",extras);
        }
        return document;
    }

    @Override
    public void compileToString(StringBuilder builder, MinecraftConnection connection, MinecraftProtocolVersion version, VariableSet variables, Language language) {
        if(version.isNewerOrSame(MinecraftProtocolVersion.JE_1_8)){
            Document document = compile(null,connection,version,variables,language);
            builder.append(DocumentFileType.JSON.getWriter().write(document,false));
        }else{
            if(color != null) builder.append(Text.FORMAT_CHAR).append(color.getCode());
            if(isBold()) builder.append(Text.FORMAT_CHAR).append(TextStyle.BOLD.getCode());
            if(isItalic()) builder.append(Text.FORMAT_CHAR).append(TextStyle.ITALIC.getCode());
            if(isUnderlined()) builder.append(Text.FORMAT_CHAR).append(TextStyle.UNDERLINE.getCode());
            if(isStrikeThrough()) builder.append(Text.FORMAT_CHAR).append(TextStyle.STRIKETHROUGH.getCode());
            if(isObfuscated()) builder.append(Text.FORMAT_CHAR).append(TextStyle.OBFUSCATED.getCode());

            compileLegacyText(builder,connection,version,variables,language);

            for (MessageComponent<?> extra : extras) {
                extra.compileToString(builder, connection,version, variables, language);
            }
        }
    }

    abstract void compileLegacyText(StringBuilder builder, MinecraftConnection connection, MinecraftProtocolVersion version, VariableSet variables, Language language);

    @Override
    public void decompile(Document data) {
        if(data.getBoolean("bold")) setBold(true);
        if(data.getBoolean("italic")) setItalic(true);
        if(data.getBoolean("underline")) setUnderlined(true);
        if(data.getBoolean("strikeThrough")) setStrikeThrough(true);
        if(data.getBoolean("obfuscated")) setObfuscated(true);

        String color = data.getString("color");
        if(color != null) this.color = TextColor.of(color);

        String insertion = data.getString("insertion");
        if(insertion != null) setInsertion(insertion);

        Document clickEvent = data.getDocument("clickEvent");
        if(clickEvent != null){
            this.clickEvent = new TextEvent<>(ClickAction.of(clickEvent.getString("action")),clickEvent.getString("value"));
        }

        Document hoverEvent = data.getDocument("hoverEvent");
        if(hoverEvent != null){
            this.hoverEvent = new TextEvent<>(HoverAction.of(hoverEvent.getString("action")),hoverEvent.getString("value"));
        }
        Document extra = data.getDocument("extra");
        if(extra != null){
            this.extras = Text.decompileArray(extra);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <N extends ChatComponent<?>> N getAs(Class<N> aClass) {
        return (N) this;
    }

    @Override
    public String toString() {
        return toPlainText();
    }
}
