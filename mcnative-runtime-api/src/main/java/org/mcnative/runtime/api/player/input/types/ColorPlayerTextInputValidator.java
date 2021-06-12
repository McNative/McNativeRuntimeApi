package org.mcnative.runtime.api.player.input.types;

import net.pretronic.libraries.utility.Convert;
import org.mcnative.runtime.api.text.Text;
import org.mcnative.runtime.api.text.components.MessageComponent;
import org.mcnative.runtime.api.text.format.TextColor;

import java.util.function.Predicate;

public class ColorPlayerTextInputValidator extends TextPlayerTextInputValidator {


    public ColorPlayerTextInputValidator(MessageComponent<?> errorMessage) {
        super(input -> {
            try {
                TextColor.make(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }, errorMessage);
    }

    public ColorPlayerTextInputValidator() {
        this(Text.ofMessageKey("mcnative.textinput.validator.error.color"));
    }
}
