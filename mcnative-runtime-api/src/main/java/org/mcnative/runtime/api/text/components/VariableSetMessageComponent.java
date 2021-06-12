package org.mcnative.runtime.api.text.components;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.message.bml.variable.HashVariableSet;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.message.language.Language;
import net.pretronic.libraries.utility.Validate;
import org.mcnative.runtime.api.connection.MinecraftConnection;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;

public class VariableSetMessageComponent implements MessageComponent<VariableSetMessageComponent> {

    private final MessageComponent<?> wrappedComponent;
    private final VariableSet variables;

    public VariableSetMessageComponent(MessageComponent<?> wrappedComponent, VariableSet variables) {
        Validate.notNull(wrappedComponent, variables);
        this.wrappedComponent = wrappedComponent;
        this.variables = variables;
    }

    @Override
    public void toPlainText(StringBuilder builder, VariableSet variables, Language language) {
        wrappedComponent.toPlainText(builder, mergeVariables(variables), language);
    }

    @Override
    public Document compile(String key, MinecraftConnection connection, MinecraftProtocolVersion version, VariableSet variables, Language language) {
        return wrappedComponent.compile(key,connection,version,mergeVariables(variables),language);
    }

    @Override
    public void compileToString(StringBuilder builder, MinecraftConnection connection, MinecraftProtocolVersion version, VariableSet variables, Language language) {
        wrappedComponent.compileToString(builder, connection, version, mergeVariables(variables), language);
    }

    @Override
    public void decompile(Document data) {
        this.wrappedComponent.decompile(data);
    }

    @Override
    public VariableSetMessageComponent copy() {
        return new VariableSetMessageComponent(wrappedComponent, variables);
    }

    private VariableSet mergeVariables(VariableSet variables) {
        if(variables == null || variables.isEmpty()) return this.variables;
        VariableSet copied = new HashVariableSet(this.variables);
        copied.addAll(variables);
        return copied;
    }
}
