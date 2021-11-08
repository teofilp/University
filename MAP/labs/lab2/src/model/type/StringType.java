package model.type;

import model.value.StringValue;
import model.value.Value;

public class StringType implements Type{
    @Override
    public boolean equals(Class c) {
        return getClass().equals(c);
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
