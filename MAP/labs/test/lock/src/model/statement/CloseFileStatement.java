package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.type.StringType;
import model.type.Type;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStatement implements Statement {
    private Expression expression;

    public CloseFileStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var fileTable = state.getFileTable();
        var result = expression.evaluate(state.getSymbolTable(), state.getHeap());
        var fileName = getStringValue(result);

        removeFile(fileTable, fileName);

        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {
        if (!expression.typecheck(typeEnv).equals(new StringType())) throw new CustomException("Expression must be of type string");
    }

    private void removeFile(IMap<String, BufferedReader> fileTable, String fileName) throws CustomException {
        if (!fileTable.containsKey(fileName)) {
            throw new CustomException(String.format("File %s cannot be close since it was not opened", fileName));
        }

        try {
            fileTable.get(fileName).close();
        } catch (IOException e) {
            throw new CustomException(String.format("Could not close file %s", fileName));
        }

        fileTable.remove(fileName);
    }

    private String getStringValue(Value value) throws CustomException {
        if (!value.getType().equals(new StringType())) {
            throw new CustomException("Expression has to be of type string");
        }

        return ((StringValue)value).getValue();
    }

    @Override
    public String toString() {
        return String.format("closeFile(%s)", expression);
    }

    @Override
    public Statement clone() {
        return new CloseFileStatement(expression.clone());
    }
}
