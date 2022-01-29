package model.value;

import model.type.BooleanType;
import model.type.Type;

public class BooleanValue implements Value {
    boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public Type getType() { return new BooleanType(); }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Value clone() {
        return new BooleanValue(value);
    }
}
