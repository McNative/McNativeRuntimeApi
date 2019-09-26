/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 09.08.19, 18:11
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

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public interface VariableSet extends Set<Variable> {

    Set<Variable> getVariables();

    VariableSet add(String name, Object source);

    VariableSet remove(String name);



    static VariableSet of(Variable... variables){
        return new HashVariableSet(Arrays.asList(variables));
    }

    static VariableSet of(Collection<Variable> variables){
        return new HashVariableSet(variables);
    }

    static VariableSet newEmptySet(){
        return EmptyVariableSet.newEmptySet();
    }
}
