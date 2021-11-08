package model.expression;

import model.CustomException;
import model.IMap;
import model.operation.LogicalOperator;
import model.operation.OperatorHandler;
import model.type.BooleanType;
import model.value.BooleanValue;
import model.value.Value;

import java.util.HashMap;

public class LogicalExpression implements Expression {
    private final Expression left, right;
    private final LogicalOperator operator;
    private HashMap<LogicalOperator, OperatorHandler> map = new HashMap<>() {{
        put(LogicalOperator.And, (left, right) -> new BooleanValue(getBooleanValue(left) && getBooleanValue(right)));
        put(LogicalOperator.Or, (left, right) -> new BooleanValue(getBooleanValue(left) || getBooleanValue(right)));
    }};

    public LogicalExpression(Expression left, Expression right, LogicalOperator operation) {
        this.left = left;
        this.right = right;
        this.operator = operation;
    }

    @Override
    public Value evaluate(IMap<String, Value> symbolTable) throws CustomException {
        var leftValue = getBoolValue(left, symbolTable);
        var rightValue = getBoolValue(right, symbolTable);

        return map.get(operator).handle(leftValue, rightValue);
    }

    private BooleanValue getBoolValue(Expression expression, IMap<String, Value> symbolTable) throws CustomException {
        var value = expression.evaluate(symbolTable);

        if (!value.getType().equals(BooleanType.class)) {
            throw new CustomException(String.format("Expected boolean expression but found %s", value.getType().toString()));
        }

        return (BooleanValue) value;
    }

    private boolean getBooleanValue(Value value) { return ((BooleanValue) value).getValue(); }

    @Override
    public String toString() {
        return String.format("%s %s %s", left, operator.toString(), right);
    }

    @Override
    public Expression clone() {
        return new LogicalExpression(left.clone(), right.clone(), operator);
    }
}
