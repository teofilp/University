package model.expression;

import model.CustomException;
import model.IHeap;
import model.IMap;
import model.operation.OperatorHandler;
import model.operation.RelationalOperator;
import model.type.BooleanType;
import model.type.IntType;
import model.type.Type;
import model.value.BooleanValue;
import model.value.IntValue;
import model.value.Value;

import java.util.HashMap;
import java.util.Map;

public class RelationalExpression implements Expression {
    private Expression leftOperand, rightOperand;
    private RelationalOperator operator;
    private Map<RelationalOperator, OperatorHandler> operationHandler = new HashMap<>() {{
        put(RelationalOperator.Smaller, (left, right) -> new BooleanValue(getIntegerValue(left) < getIntegerValue(right)));
        put(RelationalOperator.SmallerOrEqual, (left, right) -> new BooleanValue(getIntegerValue(left) <= getIntegerValue(right)));
        put(RelationalOperator.Equal, (left, right) -> new BooleanValue(getIntegerValue(left) == getIntegerValue(right)));
        put(RelationalOperator.NotEqual, (left, right) -> new BooleanValue(getIntegerValue(left) != getIntegerValue(right)));
        put(RelationalOperator.Greater, (left, right) -> new BooleanValue(getIntegerValue(left) > getIntegerValue(right)));
        put(RelationalOperator.GreaterOrEqual, (left, right) -> new BooleanValue(getIntegerValue(left) >= getIntegerValue(right)));
    }};

    public RelationalExpression(Expression left, Expression right, RelationalOperator operation) {
        this.leftOperand = left;
        this.rightOperand = right;
        this.operator = operation;
    }

    @Override
    public Value evaluate(IMap<String, Value> valueTable, IHeap heap) throws CustomException {
        var leftOperand = getIntValue(this.leftOperand, valueTable, heap);
        var rightOperand = getIntValue(this.rightOperand, valueTable, heap);

        return operationHandler.get(operator).handle(leftOperand, rightOperand);
    }

    @Override
    public Type typecheck(IMap<String, Type> typeEnv) throws CustomException {
        var leftType = leftOperand.typecheck(typeEnv);
        var rightType = rightOperand.typecheck(typeEnv);

        if (!leftType.equals(new IntType())) throw new CustomException("Left operand is not of type int");
        if (!rightType.equals(new IntType())) throw new CustomException("Right operand is not of type int");

        return new BooleanType();
    }

    private IntValue getIntValue(Expression expression, IMap<String, Value> valueTable, IHeap heap) throws CustomException {
        var v = expression.evaluate(valueTable, heap);

        if (!v.getType().equals(new IntType())) {
            throw new CustomException(String.format("operand %s is not an integer", v));
        }

        return (IntValue)v;
    }

    private int getIntegerValue(Value v) {
        return ((IntValue) v).getValue();
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", leftOperand, operator.toString(), rightOperand);
    }

    @Override
    public Expression clone() {
        return new RelationalExpression(leftOperand.clone(), rightOperand.clone(), operator);
    }
}
