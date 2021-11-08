package model.statement;

import model.Cloneable;
import model.CustomException;
import model.ProgramState;

public interface Statement extends Cloneable<Statement> {
    ProgramState execute(ProgramState state) throws CustomException;
}
