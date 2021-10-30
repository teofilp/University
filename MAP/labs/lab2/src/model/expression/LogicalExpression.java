package model.expression;

import model.CustomException;
import model.IMap;
import model.operation.LogicalOperation;
import model.operation.OperationHandler;
import model.type.BooleanType;
import model.value.BooleanValue;
import model.value.Value;

import java.util.HashMap;

public class LogicalExpression implements Expression {
    private final Expression left, right;
    private final LogicalOperation operation;
    private HashMap<LogicalOperation, OperationHandler> map = new HashMap<>() {{
        put(LogicalOperation.And, (left, right) -> new BooleanValue(getBooleanValue(left) && getBooleanValue(right)));
        put(LogicalOperation.Or, (left, right) -> new BooleanValue(getBooleanValue(left) || getBooleanValue(right)));
    }};

    public LogicalExpression(Expression left, Expression right, LogicalOperation operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    @Override
    public Value evaluate(IMap<String, Value> symbolTable) throws CustomException {
        var leftValue = getBoolValue(left, symbolTable);
        var rightValue = getBoolValue(right, symbolTable);

        return map.get(operation).handle(leftValue, rightValue);
    }

    private BooleanValue getBoolValue(Expression expression, IMap<String, Value> symbolTable) throws CustomException {
        var value = expression.evaluate(symbolTable);

        if (!value.getType().equals(BooleanType.class)) {
            throw new CustomException(String.format("Expected boolean expression but found %s", value.getType().toString()));
        }

        return (BooleanValue) value;
    }

    private boolean getBooleanValue(Value value) { return ((BooleanValue) value).getValue(); }
}
