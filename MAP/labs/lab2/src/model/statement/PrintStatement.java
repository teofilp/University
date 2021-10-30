package model.statement;

import model.CustomException;
import model.IList;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.value.Value;

public class PrintStatement implements Statement {
    Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var output = state.getOutput();
        var symbolTable = state.getSymbolTable();

        output.add(expression.evaluate(symbolTable));

        return state;
    }
}
