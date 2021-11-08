package model.expression;

import model.IMap;
import model.value.Value;


public class VariableExpression implements Expression {
    private String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public Value evaluate(IMap<String, Value> valueTable) {
        return valueTable.get(id);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public Expression clone() {
        return new VariableExpression(id);
    }
}
