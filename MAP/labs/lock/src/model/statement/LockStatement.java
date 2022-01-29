package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;

public class LockStatement implements Statement {
    private String id;

    public LockStatement(String id) {
        this.id = id;
    }

    @Override
    public Statement clone() {
        return new LockStatement(id);
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var symbolTable = state.getSymbolTable();
        var lockTable = state.getLockTable();

        var descriptor = ((IntValue)symbolTable.get(id)).getValue();

        if (!lockTable.containsKey(descriptor)) throw new CustomException(String.format("Cannot find lockable object %s", id));

        if (lockTable.get(descriptor) == -1) {
            lockTable.put(descriptor, state.getId());
        } else {
            state.getExecutionStack().push(this);
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
        return String.format("Lock(%s)", id);
    }
}
