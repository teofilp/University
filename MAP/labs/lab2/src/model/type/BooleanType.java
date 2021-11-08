package model.type;

import model.value.BooleanValue;
import model.value.Value;

public class BooleanType implements Type {
    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public boolean equals(Class type) {
        return type.equals(getClass());
    }

    @Override
    public Value getDefaultValue() {
        return new BooleanValue(false);
    }

    @Override
    public Type clone() {
        return new BooleanType();
    }
}
