package model.statement;

import model.ProgramState;

public class NoOperationStatement implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        return state;
    }
}
