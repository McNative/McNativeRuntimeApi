package org.mcnative.runtime.api.player.input;

import org.mcnative.runtime.api.text.components.MessageComponent;

import java.util.function.Function;

public class PlayerTextInput {

    private String label;
    private String placeholder;
    private Function<String, MessageComponent<?>> validCheck;

}
