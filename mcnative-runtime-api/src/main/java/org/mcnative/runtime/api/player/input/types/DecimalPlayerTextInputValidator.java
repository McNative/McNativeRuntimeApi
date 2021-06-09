package org.mcnative.runtime.api.player.input.types;

import net.pretronic.libraries.utility.Convert;
import org.mcnative.runtime.api.text.Text;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.function.Predicate;

public class DecimalPlayerTextInputValidator extends TextPlayerTextInputValidator {


    public DecimalPlayerTextInputValidator(MessageComponent<?> errorMessage) {
        super(input -> {
            try {
                Convert.toDouble(input);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }, errorMessage);
    }

    public DecimalPlayerTextInputValidator() {
        this(Text.ofMessageKey("mcnative.textinput.validator.error.decimal"));
    }
}
