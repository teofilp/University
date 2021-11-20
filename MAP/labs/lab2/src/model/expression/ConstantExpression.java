package model.expression;

import model.IHeap;
import model.IMap;
import model.value.Value;

public class ConstantExpression implements Expression {
    private Value value;

    public ConstantExpression(Value v) {
        this.value = v;
    }

    @Override
    public Value evaluate(IMap<String, Value> valueTable, IHeap heap)  {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Expression clone() {
        return new ConstantExpression(value.clone());
    }
}
