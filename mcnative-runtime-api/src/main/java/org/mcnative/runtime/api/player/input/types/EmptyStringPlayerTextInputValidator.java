package org.mcnative.runtime.api.player.input.types;

import org.mcnative.runtime.api.text.Text;
import org.mcnative.runtime.api.text.components.MessageComponent;

public class EmptyStringPlayerTextInputValidator extends TextPlayerTextInputValidator {

    public EmptyStringPlayerTextInputValidator(MessageComponent<?> errorMessage) {
        super(input -> !input.trim().isEmpty(), errorMessage);
    }

    public EmptyStringPlayerTextInputValidator() {
        this(Text.ofMessageKey("mcnative.textinput.validator.error.stringLength.notEmpty"));
    }
}
