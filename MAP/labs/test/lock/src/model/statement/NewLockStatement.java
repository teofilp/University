package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;

public class NewLockStatement implements Statement {
    private String id;

    public NewLockStatement(String id) {
        this.id = id;
    }

    @Override
    public Statement clone() {
        return new NewLockStatement(id);
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var lockTable = state.getLockTable();
        var symbolTable = state.getSymbolTable();

        var descriptor = lockTable.create(-1);

        symbolTable.put(id, new IntValue(descriptor));

        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {
        if (!typeEnv.containsKey(id)) throw new CustomException(String.format("Cannot find variable %s", id));
        if (!typeEnv.get(id).equals(new IntType())) throw new CustomException("Variable must be of type int");
    }

    @Override
    public String toString() {
        return String.format("NewLock(%s)", id);
    }
}
