package model.statement;

import model.*;
import model.type.Type;
import model.value.Value;

public class RunParallelStatement implements Statement {
    private Statement statement;

    public RunParallelStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        return new ProgramState(new Stack<>(), cloneSymbolTable(state.getSymbolTable()), state.getOutput(), state.getFileTable(), state.getHeap(), statement, state.getSemaphoreTable());
    }

    private IMap<String, Value> cloneSymbolTable(IMap<String, Value> oldMap) {
        var newMap = new Map<String, Value>();

        oldMap.getKeys().stream().forEach(x -> {
            newMap.put(x, oldMap.get(x).clone());
        });

        return newMap;
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
