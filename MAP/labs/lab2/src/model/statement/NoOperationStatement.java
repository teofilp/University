package model.statement;

import model.ProgramState;

public class NoOperationStatement implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        return state;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public Statement clone() {
        return new NoOperationStatement();
    }
}
