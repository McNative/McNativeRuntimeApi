package org.mcnative.runtime.api.player.input.types;

import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.Validate;
import org.mcnative.runtime.api.text.Text;
import org.mcnative.runtime.api.text.components.MessageComponent;
import org.mcnative.runtime.api.text.components.VariableSetMessageComponent;

public class MaxStringLengthPlayerTextInputValidator extends TextPlayerTextInputValidator {

    private final int maxLength;

    public MaxStringLengthPlayerTextInputValidator(int maxLength, MessageComponent<?> errorMessage) {
        super(input -> input.length() <= maxLength, errorMessage);
        Validate.isTrue(maxLength > 0, "Max length must be greater then 0");
        this.maxLength = maxLength;
    }

    public MaxStringLengthPlayerTextInputValidator(int maxLength) {
        this(maxLength, Text.ofMessageKey("mcnative.textinput.validator.error.stringLength.max"));
    }

    @Override
    public MessageComponent<?> getErrorMessage() {
        return new VariableSetMessageComponent(this.errorMessage, VariableSet.create().add("maxLength", this.maxLength));
    }

    public int getMaxLength() {
        return maxLength;
    }
}
