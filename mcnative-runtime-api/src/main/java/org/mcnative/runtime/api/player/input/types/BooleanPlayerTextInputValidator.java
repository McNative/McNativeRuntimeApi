package org.mcnative.runtime.api.player.input.types;

import net.pretronic.libraries.utility.Convert;
import org.mcnative.runtime.api.text.Text;
import org.mcnative.runtime.api.text.components.MessageComponent;

public class BooleanPlayerTextInputValidator extends TextPlayerTextInputValidator {


    public BooleanPlayerTextInputValidator(MessageComponent<?> errorMessage) {
        super(input -> {
            try {
                return Convert.toBoolean(input);
            } catch (IllegalArgumentException e) {
                return false;
            }
        }, errorMessage);
    }

    public BooleanPlayerTextInputValidator() {
        this(Text.ofMessageKey("mcnative.textinput.validator.error.boolean"));
    }
}
