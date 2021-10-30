package model.statement;

import model.CustomException;
import model.ProgramState;

public interface Statement {
    ProgramState execute(ProgramState state) throws CustomException;
}
