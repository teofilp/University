package model;

import model.statement.Statement;
import model.value.Value;

public class ProgramState {
    private IStack<Statement> executionStack;
    private IMap<String, Value> symbolTable;
    private IList<Value> output;

    public ProgramState(IStack<Statement> executionStack, IMap<String, Value> symbolTable, IList<Value> output, Statement program) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.executionStack.push(program);
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
}
