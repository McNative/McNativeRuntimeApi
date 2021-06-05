package org.mcnative.runtime.api.text.components;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.message.language.Language;
import net.pretronic.libraries.utility.Validate;
import org.mcnative.runtime.api.connection.MinecraftConnection;
import org.mcnative.runtime.api.player.ConnectedMinecraftPlayer;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;

public class SpecifiedPlayerMessageComponent implements MessageComponent<SpecifiedPlayerMessageComponent> {

    private final ConnectedMinecraftPlayer player;
    private final MessageComponent<?> wrappedComponent;
    private final VariableSet variables;

    public SpecifiedPlayerMessageComponent(ConnectedMinecraftPlayer player, MessageComponent<?> wrappedComponent, VariableSet variables) {
        Validate.notNull(player, wrappedComponent);
        this.player = player;
        this.wrappedComponent = wrappedComponent;
        this.variables = variables;
    }

    @Override
    public void toPlainText(StringBuilder builder, VariableSet variables, Language language) {
        wrappedComponent.toPlainText(builder, this.variables, this.player.getLanguage());
    }

    @Override
    public Document compile(String key, MinecraftConnection connection, MinecraftProtocolVersion version, VariableSet variables, Language language) {
        return wrappedComponent.compile(key,player,version,this.variables,player.getLanguage());
    }

    @Override
    public void compileToString(StringBuilder builder, MinecraftConnection connection, MinecraftProtocolVersion version, VariableSet variables, Language language) {
        wrappedComponent.compileToString(builder, this.player, version, this.variables, this.player.getLanguage());
    }

    @Override
    public void decompile(Document data) {
        this.wrappedComponent.decompile(data);
    }

    @Override
    public SpecifiedPlayerMessageComponent copy() {
        return new SpecifiedPlayerMessageComponent(player, wrappedComponent, variables);
    }
}
