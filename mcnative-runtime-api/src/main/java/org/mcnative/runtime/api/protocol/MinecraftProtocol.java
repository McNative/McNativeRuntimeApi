package org.mcnative.runtime.api.protocol;

import net.pretronic.libraries.utility.Iterators;
import org.mcnative.runtime.api.protocol.definition.MinecraftProtocolDefinition;

import java.util.ArrayList;
import java.util.Collection;

public class MinecraftProtocol {

    public final static Collection<MinecraftProtocolDefinition> DEFINITIONS = new ArrayList<>();

    public static MinecraftProtocolDefinition getDefinition(MinecraftProtocolVersion version) {
        return Iterators.findOne(DEFINITIONS, entry -> entry.getVersion().equals(version));
    }

    public static MinecraftProtocolDefinition registerDefinition(MinecraftProtocolVersion version){
        MinecraftProtocolDefinition definition = getDefinition(version);
        if(definition == null){
            definition = new MinecraftProtocolDefinition(version);
            DEFINITIONS.add(definition);
        }
        return definition;
    }

}
