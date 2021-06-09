package org.mcnative.runtime.api.player.input.types;

import org.mcnative.runtime.api.text.Text;
import org.mcnative.runtime.api.text.components.MessageComponent;

public class StringLengthPlayerTextInputValidator extends TextPlayerTextInputValidator {

    public StringLengthPlayerTextInputValidator(int maxLength, MessageComponent<?> errorMessage) {
        super(input -> input.length() <= maxLength, Text.ofMessageKey("mcnative.textinput.validator.error.stringLength"));
    }
}
