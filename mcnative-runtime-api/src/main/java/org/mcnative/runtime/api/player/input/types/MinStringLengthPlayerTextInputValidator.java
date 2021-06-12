package org.mcnative.runtime.api.player.input.types;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.Validate;
import org.mcnative.runtime.api.text.Text;
import org.mcnative.runtime.api.text.components.MessageComponent;
import org.mcnative.runtime.api.text.components.VariableSetMessageComponent;

public class MinStringLengthPlayerTextInputValidator extends TextPlayerTextInputValidator {

    private final int minLength;

    public MinStringLengthPlayerTextInputValidator(int minLength, MessageComponent<?> errorMessage) {
        super(input -> input.length() >= minLength, errorMessage);
        Validate.isTrue(minLength > 0, "Min length must be greater then 0");
        this.minLength = minLength;
    }

    public MinStringLengthPlayerTextInputValidator(int minLength) {
        this(minLength, Text.ofMessageKey("mcnative.textinput.validator.error.stringLength.min"));
    }

    @Override
    public MessageComponent<?> getErrorMessage() {
        return new VariableSetMessageComponent(this.errorMessage, VariableSet.create().add("minLength", this.minLength));
    }

    public int getMinLength() {
        return minLength;
    }
}
