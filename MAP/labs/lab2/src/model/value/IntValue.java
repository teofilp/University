package model.value;

import model.type.IntType;
import model.type.Type;

public class IntValue implements Value {
    int value;

    public IntValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Type getType() { return  new IntType(); }
}
