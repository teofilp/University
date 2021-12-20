package model.expression;

import model.Cloneable;
import model.CustomException;
import model.IHeap;
import model.IMap;
import model.type.Type;
import model.value.Value;

public interface Expression extends Cloneable<Expression> {
    Value evaluate(IMap<String, Value> valueTable, IHeap heap) throws CustomException;
    Type typecheck(IMap<String, Type> typeEnv) throws CustomException;
}
