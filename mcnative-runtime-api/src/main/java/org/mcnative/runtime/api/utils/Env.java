package org.mcnative.runtime.api.utils;

import net.pretronic.libraries.utility.Convert;

import java.util.UUID;

public class Env {

    private final String name;
    private final Object value;

    public Env(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public int getAsInteger(){
        return Convert.toInteger(value);
    }

    public long getAsLong(){
        return Convert.toLong(value);
    }

    public byte getAsByte(){
        return Convert.toByte(value);
    }

    public boolean getAsBoolean(){
        return Convert.toBoolean(value);
    }

    public double getAsDouble(){
        return Convert.toDouble(value);
    }

    public short getAsShort(){
        return Convert.toShort(value);
    }

    public UUID getAsUUID(){
        return Convert.toUUID(value);
    }

    public char getAsCharacter(){
        return Convert.toCharacter(value);
    }

    public float getAsFloat(){
        return Convert.toFloat(value);
    }

}
