package model.statement;

import model.CustomException;
import model.IMap;
import model.IStack;
import model.ProgramState;
import model.type.Type;

public class CompoundStatement implements Statement {
    private Statement first;
    private Statement second;

    public CompoundStatement(Statement first, Statement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        var executionStack = state.getExecutionStack();

        executionStack.push(second);
        executionStack.push(first);

        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {
        first.typeCheck(typeEnv);
        second.typeCheck(typeEnv);
    }

    @Override
    public String toString() {
        return String.format("%s | %s", first, second);
    }

    @Override
    public Statement clone() {
        return new CompoundStatement(first.clone(), second.clone());
    }
}
