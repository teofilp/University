package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.type.IntType;
import model.type.StringType;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements Statement {
    private String id;
    private Expression expression;

    public ReadFileStatement(Expression expression, String id) {
        this.expression = expression;
        this.id = id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var fileTable = state.getFileTable();
        var result = expression.evaluate(state.getSymbolTable());
        var fileName = getStringValue(result);
        var number = readNumber(fileTable, fileName);

        updateVariable(number, state.getSymbolTable());

        return state;
    }

    private void updateVariable(Value number, IMap<String, Value> symbolTable) throws CustomException {
        if (!symbolTable.containsKey(id)) {
            throw new CustomException(String.format("Variable with name %s was not defined", id));
        }

        Value value = symbolTable.get(id);

        if (!value.getType().equals(IntType.class)) {
            throw new CustomException(String.format("Variable %s has to be of type Int", id));
        }

        symbolTable.put(id, number);
    }

    private Value readNumber(IMap<String, BufferedReader> fileTable, String fileName) throws CustomException {
        if (!fileTable.containsKey(fileName)) {
            throw new CustomException(String.format("File %s was not found", fileName));
        }

        String line = readLine(fileName, fileTable.get(fileName));

        if (line == null) {
            return new IntType().getDefaultValue();
        }

        return new IntValue(Integer.parseInt(line));
    }

    private String readLine(String fileName, BufferedReader reader) throws CustomException {
        String line;

        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new CustomException(String.format("Could not read from file %s", fileName));
        }
        return line;
    }

    private String getStringValue(Value value) throws CustomException {
        if (!value.getType().equals(StringType.class)) {
            throw new CustomException("Expression has to be of type string");
        }

        return ((StringValue)value).getValue();
    }

    @Override
    public String toString() {
        return String.format("readFile(%s, %s)", expression, id);
    }

    @Override
    public Statement clone() {
        return new ReadFileStatement(expression.clone(), id);
    }
}
