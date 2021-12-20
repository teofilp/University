package model.expression;

import model.CustomException;
import model.IHeap;
import model.IMap;
import model.operation.LogicalOperator;
import model.operation.OperatorHandler;
import model.type.BooleanType;
import model.type.Type;
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
    public Value evaluate(IMap<String, Value> symbolTable, IHeap heap) throws CustomException {
        var leftValue = getBoolValue(left, symbolTable, heap);
        var rightValue = getBoolValue(right, symbolTable, heap);

        return map.get(operator).handle(leftValue, rightValue);
    }

    @Override
    public Type typecheck(IMap<String, Type> typeEnv) throws CustomException {
        var leftType = left.typecheck(typeEnv);
        var rightType = right.typecheck(typeEnv);

        if (!leftType.equals(new BooleanType())) throw new CustomException("Left operand is not of type boolean");
        if (!rightType.equals(new BooleanType())) throw new CustomException("Right operand is not of type boolean");

        return new BooleanType();
    }

    private BooleanValue getBoolValue(Expression expression, IMap<String, Value> symbolTable, IHeap heap) throws CustomException {
        var value = expression.evaluate(symbolTable, heap);

        if (!value.getType().equals(new BooleanType())) {
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
