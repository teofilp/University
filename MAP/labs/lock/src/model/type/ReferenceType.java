package model.type;

import model.value.ReferenceValue;
import model.value.Value;

public class ReferenceType implements Type {
    Type innerType;

    public ReferenceType(Type innerType) {
        this.innerType = innerType;
    }

    public Type getInnerType() {
        return innerType;
    }

    @Override
    public Type clone() {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass().equals(getClass()) &&
                ((ReferenceType) other).getInnerType().equals(this.getInnerType());
    }

    @Override
    public Value getDefaultValue() {
        return new ReferenceValue(getInnerType());
    }

    @Override
    public String toString() {
        return String.format("Ref(%s)", innerType);
    }
}
