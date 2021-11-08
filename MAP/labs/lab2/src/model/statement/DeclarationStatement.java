package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.type.Type;
import model.value.Value;

import java.util.Map;

public class DeclarationStatement implements Statement {
    private String id;
    private Type type;

    public DeclarationStatement(String id, Type type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var symbolTable = state.getSymbolTable();

        if (symbolTable.containsKey(id)) {
            throw new CustomException(String.format("Variable %s already declared", id));
        }

        symbolTable.put(id, type.getDefaultValue());

        return state;
    }

    @Override
    public String toString() {
        return String.format("%s %s", type, id);
    }

    @Override
    public Statement clone() {
        return new DeclarationStatement(id, type.clone());
    }
}
