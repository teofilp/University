package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;

public class CountDownLatch implements Statement {
    private String id;

    public CountDownLatch(String id) {
        this.id = id;
    }

    @Override
    public Statement clone() {
        return new CountDownLatch(id);
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var symbolTable = state.getSymbolTable();
        var latchTable = state.getLatchTable();

        var descriptor = ((IntValue)symbolTable.get(id)).getValue();

        if (!latchTable.containsKey(descriptor)) throw new CustomException("Cannot find descriptor in latch table");
        if (latchTable.get(descriptor) > 0) latchTable.put(descriptor, latchTable.get(descriptor) - 1);

        state.getOutput().add(new IntValue(state.getId()));

        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {
        if (!typeEnv.containsKey(id)) throw new CustomException(String.format("Cannot find variable %s", id));
        if (!typeEnv.get(id).equals(new IntType())) throw new CustomException("Variable must be of type integer");
    }

    @Override
    public String toString() {
        return String.format("CountDown(%s)", id);
    }
}
