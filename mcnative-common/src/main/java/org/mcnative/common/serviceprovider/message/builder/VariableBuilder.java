/*
 * (C) Copyright 2020 The PretronicLibraries Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 21.03.20, 17:04
 * @web %web%
 *
 * The PretronicLibraries Project is under the Apache License, version 2.0 (the "License");
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

package org.mcnative.common.serviceprovider.message.builder;

import net.pretronic.libraries.message.bml.builder.BasicMessageBuilder;
import net.pretronic.libraries.message.bml.builder.BuildContext;

public class VariableBuilder implements BasicMessageBuilder {

    public VariableBuilder(){}

    @Override
    public Object build(BuildContext context, boolean requiresString,Object[] parameters, Object next) {
        Object result = parameters.length > 0 ? context.getVariables().getValue((String) parameters[0]) : "[VAR NOT FOUND]";

        if (!requiresString) {
            if (context instanceof MinecraftBuildContext) {
                if (((MinecraftBuildContext) context).getType() == TextBuildType.COMPILE) {
                    if (next != null) {
                        return new Object[]{TextBuilder.buildCompileText(context,result != null ? result : "null", next)};
                    }else {
                        return new Object[]{TextBuilder.buildCompileText(context,result != null ? result : "null", null) != null ? result : "null"};
                    }
                }else if (((MinecraftBuildContext) context).getType() == TextBuildType.LEGACY) {
                    return TextBuilder.buildLegacyCompileText(result != null ? result.toString() : "null", next);
                }
            }
        }

        if(next != null) return result+next.toString();
        else return result;
    }

    @Override
    public boolean isUnformattedResultRequired() {
        return true;
    }
}
