package model.expression;

import model.CustomException;
import model.IMap;
import model.operation.ArithmeticOperation;
import model.operation.OperationHandler;
import model.type.IntType;
import model.value.IntValue;
import model.value.Value;

import java.util.HashMap;
import java.util.Map;

public class ArithmeticExpression implements Expression {
    private Expression leftOperand, rightOperand;
    private ArithmeticOperation operation;
    private Map<ArithmeticOperation, OperationHandler> operationHandler = new HashMap<>() {{
        put(ArithmeticOperation.Addition, (left, right) -> new IntValue(getIntegerValue(left) + getIntegerValue(right)));
        put(ArithmeticOperation.Subtraction, (left, right) -> new IntValue(getIntegerValue(left) - getIntegerValue(right)));
        put(ArithmeticOperation.Multiplication, (left, right) -> new IntValue(getIntegerValue(left) * getIntegerValue(right)));
        put(ArithmeticOperation.Division, (left, right) -> {
            if (getIntegerValue(right) == 0) throw new CustomException("right operand cannot be zero");
            return new IntValue(getIntegerValue(left) / getIntegerValue(right));
        });
    }};

    public ArithmeticExpression(Expression left, Expression right, ArithmeticOperation operation) {
        this.leftOperand = left;
        this.rightOperand = right;
        this.operation = operation;
    }

    @Override
    public Value evaluate(IMap<String, Value> valueTable) throws CustomException {
        var leftOperand = getIntValue(this.leftOperand, valueTable);
        var rightOperand = getIntValue(this.rightOperand, valueTable);

        return operationHandler.get(operation).handle(leftOperand, rightOperand);
    }

    private IntValue getIntValue(Expression expression, IMap<String, Value> valueTable) throws CustomException {
        var v = expression.evaluate(valueTable);

        if (!v.getType().equals(IntType.class)) {
            throw new CustomException(String.format("operand %s is not an integer", v));
        }

        return (IntValue)v;
    }

    private int getIntegerValue(Value v) {
        return ((IntValue) v).getValue();
    }
}
