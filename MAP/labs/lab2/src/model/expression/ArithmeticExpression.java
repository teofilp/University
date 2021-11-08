package model.expression;

import model.CustomException;
import model.IMap;
import model.operation.ArithmeticOperator;
import model.operation.OperatorHandler;
import model.type.IntType;
import model.value.IntValue;
import model.value.Value;

import java.util.HashMap;
import java.util.Map;

public class ArithmeticExpression implements Expression {
    private Expression leftOperand, rightOperand;
    private ArithmeticOperator operator;
    private Map<ArithmeticOperator, OperatorHandler> operationHandler = new HashMap<>() {{
        put(ArithmeticOperator.Addition, (left, right) -> new IntValue(getIntegerValue(left) + getIntegerValue(right)));
        put(ArithmeticOperator.Subtraction, (left, right) -> new IntValue(getIntegerValue(left) - getIntegerValue(right)));
        put(ArithmeticOperator.Multiplication, (left, right) -> new IntValue(getIntegerValue(left) * getIntegerValue(right)));
        put(ArithmeticOperator.Division, (left, right) -> {
            if (getIntegerValue(right) == 0) throw new CustomException("right operand cannot be zero");
            return new IntValue(getIntegerValue(left) / getIntegerValue(right));
        });
    }};

    public ArithmeticExpression(Expression left, Expression right, ArithmeticOperator operation) {
        this.leftOperand = left;
        this.rightOperand = right;
        this.operator = operation;
    }

    @Override
    public Value evaluate(IMap<String, Value> valueTable) throws CustomException {
        var leftOperand = getIntValue(this.leftOperand, valueTable);
        var rightOperand = getIntValue(this.rightOperand, valueTable);

        return operationHandler.get(operator).handle(leftOperand, rightOperand);
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

    @Override
    public String toString() {
        return String.format("%s %s %s", leftOperand, operator.toString(), rightOperand);
    }

    @Override
    public Expression clone() {
        return new ArithmeticExpression(leftOperand.clone(), rightOperand.clone(), operator);
    }
}
