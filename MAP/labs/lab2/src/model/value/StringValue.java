package model.value;

import model.type.StringType;
import model.type.Type;

public class StringValue implements Value {
    private String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", value);
    }

    @Override
    public Value clone() {
        return new StringValue(value);
    }
}
