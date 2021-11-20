package model.type;

import model.value.IntValue;
import model.value.Value;

public class IntType implements Type {
    @Override
    public String toString() {
        return "int";
    }

    @Override
    public boolean equals(Object type) {
        return type.getClass().equals(getClass());
    }

    @Override
    public Value getDefaultValue() {
        return new IntValue(0);
    }

    @Override
    public Type clone() {
        return new IntType();
    }
}
