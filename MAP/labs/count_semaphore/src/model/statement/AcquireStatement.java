package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;

public class AcquireStatement implements Statement {
    private String id;

    public AcquireStatement(String id) {
        this.id = id;
    }

    @Override
    public Statement clone() {
        return new AcquireStatement(id);
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var symbolTable = state.getSymbolTable();
        var semaphoreTable = state.getSemaphoreTable();
        var executionStack = state.getExecutionStack();

        var descriptor = ((IntValue) symbolTable.get(id)).getValue();

        if (!semaphoreTable.containsKey(descriptor)) throw new CustomException(String.format("Cannot find descriptor %d", descriptor));

        var semEntry = semaphoreTable.get(descriptor);

        if (semEntry.getKey() > semEntry.getValue().size()) {
            if (!semEntry.getValue().contains(state.getId())) {
                semEntry.getValue().add(state.getId());
            }
        } else {
            executionStack.push(this.clone());
        }

        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {
        if (!typeEnv.containsKey(id)) throw new CustomException(String.format("Cannot find variable %s", id));
        if (!typeEnv.get(id).equals(new IntType())) throw new CustomException("Variable must be of type integer");
    }

    @Override
    public String toString() {
        return String.format("Acquire(%s)", id);
    }
}
