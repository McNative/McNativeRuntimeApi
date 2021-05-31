package org.mcnative.runtime.api;

import net.pretronic.libraries.document.Document;

public interface Setting {

    int getId();

    String getOwner();

    String getKey();

    String getValue();

    Object getObjectValue();

    byte getByteValue();

    int getIntValue();

    long getLongValue();

    double getDoubleValue();

    float getFloatValue();

    boolean getBooleanValue();

    Document getDocumentValue();

    void setValue(Object value);

    boolean equalsValue(Object value);

    long getCreated();

    long getUpdated();

    void setUpdated(long updated);
}
