package model.expression;

import model.CustomException;
import model.IMap;
import model.value.Value;

public interface Expression {
    Value evaluate(IMap<String, Value> valueTable) throws CustomException;
}
