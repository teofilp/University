package model.statement;

import model.*;
import model.type.Type;

public class RunParallelStatement implements Statement {
    private Statement statement;

    public RunParallelStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        return new ProgramState(new Stack<>(), state.getSymbolTable().clone(), state.getOutput(), state.getFileTable(), state.getHeap(), statement, state.getBarrierTable());
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {
        statement.typeCheck(typeEnv);
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
