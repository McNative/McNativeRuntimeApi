package org.mcnative.common.serviceprovider.message;

import net.pretronic.libraries.utility.Validate;

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
}
