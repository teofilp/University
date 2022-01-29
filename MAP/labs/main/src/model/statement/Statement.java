package model.statement;

import model.Cloneable;
import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.type.Type;

public interface Statement extends Cloneable<Statement> {
    ProgramState execute(ProgramState state) throws CustomException;
    void typeCheck(IMap<String, Type> typeEnv) throws CustomException;
}
