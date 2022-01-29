package model.value;

import model.Cloneable;
import model.IMap;
import model.type.Type;

public interface Value extends Cloneable<Value> {
    Type getType();
}
