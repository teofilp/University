package model.statement;

import model.IStack;
import model.ProgramState;

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

        return state;
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
