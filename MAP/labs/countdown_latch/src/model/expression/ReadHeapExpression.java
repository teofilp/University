package model.expression;

import model.CustomException;
import model.IHeap;
import model.IMap;
import model.type.ReferenceType;
import model.type.Type;
import model.value.ReferenceValue;
import model.value.Value;

public class ReadHeapExpression implements Expression {
    Expression expression;

    public ReadHeapExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value evaluate(IMap<String, Value> valueTable, IHeap heap) throws CustomException {
        var expressionResult = expression.evaluate(valueTable, heap);

        if (!(expressionResult instanceof ReferenceValue)) throw new CustomException("Expression must evaluate to a ReferenceValue");

        int address = ((ReferenceValue) expressionResult).getAddress();

        return heap.get(address);
    }

    @Override
    public Type typecheck(IMap<String, Type> typeEnv) throws CustomException {
        var type = expression.typecheck(typeEnv);
        if (!(type instanceof ReferenceType)) throw new CustomException("The argument of ReadHeap is not of type ReferenceType");

        return ((ReferenceType)type).getInnerType();
    }

    @Override
    public Expression clone() {
        return new ReadHeapExpression(expression.clone());
    }

    @Override
    public String toString() {
        return String.format("ReadHeap(%s)", expression.toString());
    }
}
