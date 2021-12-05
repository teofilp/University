package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.value.ReferenceValue;
import model.value.Value;

public class NewReferenceStatement implements Statement {
    private String id;
    private Expression expression;

    public NewReferenceStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var refValue = getRefValue(state.getSymbolTable());
        var expressionResult = expression.evaluate(state.getSymbolTable(), state.getHeap());

        if (!refValue.getInnerType().equals(expressionResult.getType()))
            throw new CustomException(String.format("Cannot assign %s to %s", expressionResult.getType(), refValue.getInnerType()));

        var address = state.getHeap().allocate(expressionResult);
        state.getSymbolTable().put(id, new ReferenceValue(refValue.getInnerType(), address));

        return null;
    }

    private ReferenceValue getRefValue(IMap<String, Value> symbolTable) throws CustomException {
        if (!symbolTable.containsKey(id)) throw new CustomException("Variable was not defined");

        var value = symbolTable.get(id);

        if (!(value instanceof ReferenceValue)) throw new CustomException("Variable must be of type ReferenceValue");

        return (ReferenceValue) value;
    }

    @Override
    public Statement clone() {
        return new NewReferenceStatement(id, expression.clone());
    }

    @Override
    public String toString() {
        return String.format("new(%s, %s)", id, expression);
    }
}
