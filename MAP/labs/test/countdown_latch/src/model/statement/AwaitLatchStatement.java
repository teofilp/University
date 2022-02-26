package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;

public class AwaitLatchStatement implements Statement {
    private String id;

    public AwaitLatchStatement(String id) {
        this.id = id;
    }

    @Override
    public Statement clone() {
        return new AwaitLatchStatement(id);
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var symbolTable = state.getSymbolTable();
        var latchTable = state.getLatchTable();
        var executionStack = state.getExecutionStack();

        var descriptor = ((IntValue)symbolTable.get(id)).getValue();

        if (!latchTable.containsKey(descriptor)) throw new CustomException("Descriptor was not found in latch table");

        if (latchTable.get(descriptor) != 0) executionStack.push(this.clone());

        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {
        if (!typeEnv.containsKey(id)) throw new CustomException(String.format("Cannot find variable %s", id));
        if (!typeEnv.get(id).equals(new IntType())) throw new CustomException("Descriptor must be of type integer");
    }

    @Override
    public String toString() {
        return String.format("AwaitLatch(%s)", id);
    }
}
