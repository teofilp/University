package model.expression;

import model.IMap;
import model.value.Value;

public class ConstantExpression implements Expression {
    private Value value;

    public ConstantExpression(Value v) {
        this.value = v;
    }

    @Override
    public Value evaluate(IMap<String, Value> valueTable)  {
        return value;
    }
}
