package org.mcnative.runtime.api.player.input.types;

import net.pretronic.libraries.utility.Convert;
import org.mcnative.runtime.api.text.Text;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.function.Predicate;

public class NumberPlayerTextInputValidator extends TextPlayerTextInputValidator {


    public NumberPlayerTextInputValidator(MessageComponent<?> errorMessage) {
        super(input -> {
            try {
                Convert.toLong(input);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }, errorMessage);
    }

    public NumberPlayerTextInputValidator() {
        this(Text.ofMessageKey("mcnative.textinput.validator.error.number"));
    }
}
