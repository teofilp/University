package model.value;

import model.type.ReferenceType;
import model.type.Type;

public class ReferenceValue implements Value {
    public static int NULL_ADDRESS;
    Type innerType;
    int address;

    public ReferenceValue(Type innerType) {
        this.innerType = innerType;
        this.address = NULL_ADDRESS;
    }

    public ReferenceValue(Type innerType, int address) {
        this.innerType = innerType;
        this.address = address;
    }

    public Type getInnerType() {
        return innerType;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    @Override
    public Value clone() {
        return new ReferenceValue(getInnerType().clone());
    }

    @Override
    public Type getType() {
        return new ReferenceType(getInnerType());
    }

    @Override
    public String toString() {
        return String.format("Ref(%d -> %s)", address, innerType.toString());
    }
}
