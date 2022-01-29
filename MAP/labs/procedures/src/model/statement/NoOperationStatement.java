package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.type.Type;

public class NoOperationStatement implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {}

    @Override
    public String toString() {
        return "";
    }

    @Override
    public Statement clone() {
        return new NoOperationStatement();
    }
}
