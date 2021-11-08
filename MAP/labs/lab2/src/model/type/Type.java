package model.type;

import model.Cloneable;
import model.value.Value;

public interface Type extends Cloneable<Type> {
    boolean equals(Class c);
    Value getDefaultValue();
}
