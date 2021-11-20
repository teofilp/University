package model.type;

import model.value.StringValue;
import model.value.Value;

public class StringType implements Type {
    @Override
    public boolean equals(Object c) {
        return getClass().equals(c.getClass());
    }

    @Override
    public Value getDefaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString() {
        return "String";
    }

    @Override
    public Type clone() {
        return new StringType();
    }
}
