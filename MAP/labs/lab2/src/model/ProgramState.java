package model;

import model.statement.Statement;
import model.value.Value;

import javax.swing.plaf.SeparatorUI;
import java.io.BufferedReader;

public class ProgramState implements Cloneable<ProgramState> {
    private IStack<Statement> executionStack;
    private IMap<String, Value> symbolTable;
    private IMap<String, BufferedReader> fileTable;
    private IList<Value> output;

    public ProgramState(IStack<Statement> executionStack, IMap<String, Value> symbolTable, IList<Value> output, IMap<String, BufferedReader> fileTable, Statement program) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.fileTable = fileTable;
        this.output = output;
        this.executionStack.push(program);
    }

    private ProgramState(IStack<Statement> executionStack, IMap<String, Value> symbolTable, IList<Value> output, IMap<String, BufferedReader> fileTable) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.fileTable = fileTable;
        this.output = output;
    }

    public IStack<Statement> getExecutionStack() {
        return executionStack;
    }

    public IMap<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public IList<Value> getOutput() {
        return output;
    }

    public void pushStatement(Statement statement) {
        this.executionStack.push(statement);
    }

    @Override
    public String toString() {
        var separator = "\n --------------------\n";

        return new StringBuilder()
                .append("Program State").append(separator)
                .append("Symbol Table").append(separator)
                .append(symbolTable.toString()).append(separator)
                .append("File Table").append(separator)
                .append(fileTable.toString(false)).append(separator)
                .append("Execution Stack").append(separator)
                .append(executionStack.toString()).append(separator)
                .append("Output").append(separator)
                .append(output).append(separator)
                .toString();
    }

    public IMap<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    @Override
    public ProgramState clone() {
        return new ProgramState(executionStack.clone(), symbolTable.clone(), output.clone(), fileTable.clone());
    }
}
