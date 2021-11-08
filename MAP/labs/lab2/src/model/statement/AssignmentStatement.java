package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.value.Value;

import java.util.Map;

public class AssignmentStatement implements Statement {
    private final String id;
    private final Expression expression;

    public AssignmentStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var symbolTable = state.getSymbolTable();
        var newValue = expression.evaluate(symbolTable);
        var existingValue = symbolTable.get(id);

        if (existingValue == null) {
            throw new CustomException(String.format("Variable %s was not declared", id));
        }

        if (!existingValue.getType().equals(newValue.getType().getClass())) {
            throw new CustomException(String.format("Incompatible type between %s and %s", existingValue.getType().toString(), newValue.getType().toString()));
        }

        symbolTable.put(id, newValue);
        return state;
    }

    @Override
    public String toString() {
        return String.format("%s = %s", id, expression);
    }

    @Override
    public Statement clone() {
        return new AssignmentStatement(id, expression.clone());
    }
}
