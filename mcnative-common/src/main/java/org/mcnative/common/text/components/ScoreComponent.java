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

import net.prematic.libraries.document.Document;
import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextStyle;
import org.mcnative.common.text.variable.VariableSet;

import java.util.Set;

public class ScoreComponent extends AbstractChatComponent<ScoreComponent>{

    private String entityName;
    private String objective;
    private String value;

    public ScoreComponent() {}

    public ScoreComponent(String entityName, String objective, String value) {
        this.entityName = entityName;
        this.objective = objective;
        this.value = value;
    }

    public ScoreComponent(String entityName, String objective, String value,TextColor color) {
        super(color);
        this.entityName = entityName;
        this.objective = objective;
        this.value = value;
    }

    public ScoreComponent(String entityName, String objective, String value,TextColor color, Set<TextStyle> styling) {
        super(color, styling);
        this.entityName = entityName;
        this.objective = objective;
        this.value = value;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void toPlainText(StringBuilder builder, VariableSet variables) {
        builder.append("{objective=").append(objective).append(".entity=").append(entityName).append(",value=").append(value).append("}");
    }

    @Override
    public void compile(Document document,VariableSet variables) {
        Document score = Document.newDocument();
        score.add("name",entityName);
        score.add("objective", objective);
        score.add("value",value);
        super.compile(variables).add("score",score);
    }
}
