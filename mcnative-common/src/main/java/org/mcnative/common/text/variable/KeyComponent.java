/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.08.19, 18:59
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

package org.mcnative.common.text.variable;


import org.mcnative.common.text.MessageComponent;
import org.mcnative.common.text.event.ClickAction;
import org.mcnative.common.text.event.HoverAction;
import org.mcnative.common.text.event.TextEvent;

public class KeyComponent implements MessageComponent<KeyComponent> {

    private final String key;
    private final VariableSet variables;

    private TextEvent<ClickAction> clickEvent;
    private TextEvent<HoverAction> hoverEvent;

    public KeyComponent(String key) {
        this(key,new VariableSet());
    }

    public KeyComponent(String key, VariableSet variables) {
        this.key = key;
        this.variables = variables;
    }

    public VariableSet getVariables() {
        return variables;
    }

    public KeyComponent addVariable(Variable variable){
        this.variables.add(variable);
        return this;
    }

    public KeyComponent addVariable(String name, Object source){
        return addVariable(new Variable(name,source));
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
    public KeyComponent setClickEvent(TextEvent<ClickAction> event) {
        this.clickEvent = event;
        return this;
    }

    @Override
    public KeyComponent setHoverEvent(TextEvent<HoverAction> event) {
        this.hoverEvent = event;
        return this;
    }

    @Override
    public void toPlainText(StringBuilder builder) {

    }

    @Override
    public void compile(StringBuilder builder) {

    }
}
