/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 17.08.19, 10:15
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

import org.mcnative.common.text.format.TextColor;
import org.mcnative.common.text.format.TextStyle;

import java.util.Collection;

public class ScoreComponent extends AbstractChatComponent<ScoreComponent>{

    private String entityName;
    private String objective;

    public ScoreComponent(String entityName, String objective){
        this(entityName,objective,TextColor.WHITE);
    }

    public ScoreComponent(String entityName, String objective,TextColor color) {
        super(color);
        this.entityName = entityName;
        this.objective = objective;
    }

    public ScoreComponent(String entityName, String objective,TextColor color, Collection<TextStyle> styling) {
        super(color, styling);
        this.entityName = entityName;
        this.objective = objective;
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

    @Override
    public void toPlainText(StringBuilder builder) {

    }

    @Override
    public void compile(StringBuilder builder) {

    }
}
