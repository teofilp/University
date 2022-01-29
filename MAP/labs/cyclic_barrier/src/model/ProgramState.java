package model;

import model.statement.Statement;
import model.value.Value;
import java.io.BufferedReader;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgramState implements Cloneable<ProgramState> {
    private static AtomicInteger nextId = new AtomicInteger(1);

    private IStack<Statement> executionStack;
    private IMap<String, Value> symbolTable;
    private IMap<String, BufferedReader> fileTable;
    private IList<Value> output;
    private IBarrierTable barrierTable;
    private IHeap heap;
    private int id;

    public ProgramState(IStack<Statement> executionStack, IMap<String, Value> symbolTable, IList<Value> output, IMap<String, BufferedReader> fileTable, IHeap heap, Statement program, IBarrierTable barrierTable) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.fileTable = fileTable;
        this.output = output;
        this.heap = heap;
        this.executionStack.push(program);
        this.barrierTable = barrierTable;
        this.id = nextId.getAndIncrement();
    }

    private ProgramState(IStack<Statement> executionStack, IMap<String, Value> symbolTable, IList<Value> output, IMap<String, BufferedReader> fileTable, IHeap heap, IBarrierTable barrierTable, int id) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.fileTable = fileTable;
        this.output = output;
        this.heap = heap;
        this.barrierTable = barrierTable;
        this.id = id;
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

    public IHeap getHeap() { return heap; }

    public void pushStatement(Statement statement) {
        this.executionStack.push(statement);
    }

    public boolean isCompleted() { return executionStack.isEmpty(); }

    public ProgramState runOne() throws CustomException {
        if (isCompleted()) throw new CustomException("cannot run empty stack");

        Statement current = executionStack.pop();

        return current.execute(this);
    }

    @Override
    public String toString() {
        var separator = "\n --------------------\n";

        return new StringBuilder()
                .append(String.format("Program State #%s", id)).append(separator)
                .append("Symbol Table").append(separator)
                .append(symbolTable.toString()).append(separator)
                .append("Heap").append(separator)
                .append(heap.toString()).append(separator)
                .append("File Table").append(separator)
                .append(fileTable.toString(false)).append(separator)
                .append("Execution Stack").append(separator)
                .append(executionStack.toString()).append(separator)
                .append("Barrier Table").append(separator)
                .append(barrierTable).append(separator)
                .append("Output").append(separator)
                .append(output).append(separator)
                .toString();
    }

    public int getId() {
        return id;
    }

    public IMap<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IBarrierTable getBarrierTable() {
        return barrierTable;
    }

    @Override
    public ProgramState clone() {
        return new ProgramState(executionStack.clone(), symbolTable.clone(), output.clone(), fileTable.clone(), heap.clone(), barrierTable.clone(), id);
    }
}
