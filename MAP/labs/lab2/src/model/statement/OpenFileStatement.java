package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.type.StringType;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenFileStatement implements Statement {
    private Expression expression;

    public OpenFileStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var fileTable = state.getFileTable();
        var result = expression.evaluate(state.getSymbolTable(), state.getHeap());
        var value = getStringValue(result);

        addFile(fileTable, value);

        return state;
    }

    private void addFile(IMap<String, BufferedReader> fileTable, String fileName) throws CustomException {
        if (fileTable.containsKey(fileName)) {
            throw new CustomException(String.format("File %s already exists in FileTable", fileName));
        }

        try {
            fileTable.put(fileName, new BufferedReader(new FileReader(fileName)));
        } catch (FileNotFoundException ex) {
            throw new CustomException(String.format("File %s cannot be opened", fileName));
        }
    }

    private String getStringValue(Value value) throws CustomException {
        if (!value.getType().equals(new StringType())) {
            throw new CustomException("Expression has to be of type string");
        }

        return ((StringValue)value).getValue();
    }

    @Override
    public String toString() {
        return String.format("openFile(%s)", expression);
    }

    @Override
    public Statement clone() {
        return new OpenFileStatement(expression.clone());
    }
}
