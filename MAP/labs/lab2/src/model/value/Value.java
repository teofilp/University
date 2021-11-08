package model.value;

import model.Cloneable;
import model.type.Type;

public interface Value extends Cloneable<Value> {
    Type getType();
}
