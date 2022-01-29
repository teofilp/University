package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.type.ReferenceType;
import model.type.Type;
import model.value.ReferenceValue;
import model.value.Value;

public class WriteHeapStatement implements Statement {
    private String id;
    private Expression expression;

    public WriteHeapStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var refValue = getRefValue(state.getSymbolTable());
        var expressionResult = expression.evaluate(state.getSymbolTable(), state.getHeap());

        if (!refValue.getInnerType().equals(expressionResult.getType()))
            throw new CustomException(String.format("Cannot assign %s to %s", expressionResult.getType(), refValue.getInnerType()));

        state.getHeap().set(refValue.getAddress(), expressionResult);
        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {
        var variableType = typeEnv.get(id);
        var expressionType = expression.typecheck(typeEnv);

        if (!variableType.equals(new ReferenceType(expressionType))) throw new CustomException("Cannot assign to ref value rhs");
    }

    private ReferenceValue getRefValue(IMap<String, Value> symbolTable) throws CustomException {
        if (!symbolTable.containsKey(id)) throw new CustomException("Variable was not defined");

        var value = symbolTable.get(id);

        if (!(value instanceof ReferenceValue)) throw new CustomException("Variable must be of type ReferenceValue");

        return (ReferenceValue) value;
    }

    @Override
    public Statement clone() {
        return new WriteHeapStatement(id, expression.clone());
    }

    @Override
    public String toString() {
        return String.format("WriteHeap(%s, %s)", id, expression.toString());
    }
}
