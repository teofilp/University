package model.expression;

import model.Cloneable;
import model.CustomException;
import model.IMap;
import model.value.Value;

public interface Expression extends Cloneable<Expression> {
    Value evaluate(IMap<String, Value> valueTable) throws CustomException;
}
