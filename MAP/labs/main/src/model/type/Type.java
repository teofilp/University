package model.type;

import model.Cloneable;
import model.value.Value;

public interface Type extends Cloneable<Type> {
    boolean equals(Object other);
    Value getDefaultValue();
}
