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

package org.mcnative.common.text.outdated;


import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextStyle;
import org.mcnative.common.text.variable.Variable;
import org.mcnative.common.text.variable.VariableSet;

import java.util.Arrays;
import java.util.Collection;

public class KeyComponent extends AbstractChatComponent<KeyComponent> {

    private final String key;
    private final VariableSet variables;

    public KeyComponent(String key) {
        this(key,new VariableSet());
    }

    public KeyComponent(String key, VariableSet variables) {
        this(key,variables,TextColor.WHITE);
    }

    public KeyComponent(String key, VariableSet variables,TextColor color) {
        super(color);
        this.key = key;
        this.variables = variables;
    }

    public KeyComponent(String key, VariableSet variables, TextColor color, Collection<TextStyle> styling) {
        super(color, styling);
        this.key = key;
        this.variables = variables;
    }

    public String getKey() {
        return key;
    }

    public VariableSet getVariables() {
        return variables;
    }

    public KeyComponent addVariable(String name, Object value){
        return addVariable(new Variable(name,value));
    }

    public KeyComponent addVariable(Variable variable){
        this.variables.add(variable);
        return this;
    }

    public KeyComponent addVariable(Variable... variables){
        this.variables.addAll(Arrays.asList(variables));
        return this;
    }

    @Override
    public void toPlainText(StringBuilder builder) {

    }

    @Override
    public void compile(StringBuilder builder) {

    }

    //&5Hallo klick doch &9bitte auf folgenden &8Link https://test.com
    //&8Hallo [player]

    //{&8Hallo [player]}



}