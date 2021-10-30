package model.type;

import model.value.Value;

public interface Type {
    boolean equals(Class c);
    Value getDefaultValue();
}
