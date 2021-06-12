package org.mcnative.runtime.api.player.input.types;

import net.pretronic.libraries.utility.Validate;
import org.mcnative.runtime.api.player.input.PlayerTextInputValidator;
import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.function.Predicate;

public class TextPlayerTextInputValidator implements PlayerTextInputValidator {

    protected final Predicate<String> validator;
    protected final MessageComponent<?> errorMessage;

    public TextPlayerTextInputValidator(Predicate<String> validator, MessageComponent<?> errorMessage) {
        Validate.notNull(validator, errorMessage);
        this.validator = validator;
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean isValid(String s) {
        return validator.test(s);
    }

    @Override
    public MessageComponent<?> getErrorMessage() {
        return this.errorMessage;
    }
}
