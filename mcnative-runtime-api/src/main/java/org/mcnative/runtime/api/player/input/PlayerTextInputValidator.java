package org.mcnative.runtime.api.player.input;

import org.mcnative.runtime.api.player.input.types.BooleanPlayerTextInputValidator;
import org.mcnative.runtime.api.player.input.types.ColorPlayerTextInputValidator;
import org.mcnative.runtime.api.player.input.types.DecimalPlayerTextInputValidator;
import org.mcnative.runtime.api.player.input.types.NumberPlayerTextInputValidator;
import org.mcnative.runtime.api.text.components.MessageComponent;

public interface PlayerTextInputValidator {

    PlayerTextInputValidator BOOLEAN = new BooleanPlayerTextInputValidator();
    PlayerTextInputValidator COLOR = new ColorPlayerTextInputValidator();
    PlayerTextInputValidator DECIMAL = new DecimalPlayerTextInputValidator();
    PlayerTextInputValidator NUMBER = new NumberPlayerTextInputValidator();


    boolean isValid(String input);

    MessageComponent<?> getErrorMessage();
}
