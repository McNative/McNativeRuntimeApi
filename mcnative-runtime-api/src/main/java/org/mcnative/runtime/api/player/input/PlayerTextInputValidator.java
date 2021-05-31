package org.mcnative.runtime.api.player.input;

import org.mcnative.runtime.api.text.components.MessageComponent;

public interface PlayerTextInputValidator {

    boolean isValid(String input);

    MessageComponent<?> getErrorMessage(String message);

}
