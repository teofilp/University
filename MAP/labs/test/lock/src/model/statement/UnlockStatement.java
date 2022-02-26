package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;

public class UnlockStatement implements Statement {
    private String id;

    public UnlockStatement(String id) {
        this.id = id;
    }

    @Override
    public Statement clone() {
        return new UnlockStatement(id);
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var symbolTable = state.getSymbolTable();
        var lockTable = state.getLockTable();

        var descriptor = ((IntValue)symbolTable.get(id)).getValue();

        if (!lockTable.containsKey(descriptor)) return null;

        if (lockTable.get(descriptor) == state.getId()) {
            lockTable.put(descriptor, -1);
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
        return String.format("Unlock(%s)", id);
    }
}
