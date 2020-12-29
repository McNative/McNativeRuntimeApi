package org.mcnative.runtime.api.text.format;

import net.pretronic.libraries.message.bml.variable.describer.VariableDescriber;
import net.pretronic.libraries.utility.Validate;

import java.util.function.Function;

public class ColoredString {

    private final Object data;

    public ColoredString(Object data) {
        Validate.notNull(data);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public String getDataAsString() {
        return data.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || data.equals(obj);
    }

    @Override
    public String toString() {
        return data.toString();
    }

    public static <T> void makeDescriberColored(VariableDescriber<T> describer){
        describer.getFunctions().replaceAll((name, function) -> o -> new ColoredString(function.apply(o)));
    }

    public static <T> void makeFunctionColored(VariableDescriber<T> describer, String functionName){
        Function<T, ?> inner = describer.getFunctions().get(functionName);
        describer.getFunctions().put(functionName,object -> new ColoredString(inner.apply(object)));
    }
}
