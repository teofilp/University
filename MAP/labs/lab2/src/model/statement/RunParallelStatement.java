package model.statement;

import model.CustomException;
import model.ProgramState;
import model.Stack;

public class RunParallelStatement implements Statement {
    private Statement statement;

    public RunParallelStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        return new ProgramState(new Stack<>(), state.getSymbolTable().clone(), state.getOutput(), state.getFileTable(), state.getHeap(), statement);
    }

    @Override
    public Statement clone() {
        return new RunParallelStatement(statement.clone());
    }

    @Override
    public String toString() {
        return String.format("fork(%s)", statement);
    }
}
