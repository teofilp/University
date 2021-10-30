package controller;

import model.CustomException;
import model.IRepository;
import model.IStack;
import model.ProgramState;
import model.statement.Statement;

import java.util.stream.Collectors;

public class ExecutionController {
    private IRepository<ProgramState> programStates;

    public ExecutionController(IRepository<ProgramState> programStates) {
        this.programStates = programStates;
    }

    public ProgramState runOne(int position) throws CustomException {
        ProgramState state = programStates.get(position);
        IStack<Statement> executionStack = state.getExecutionStack();

        if (executionStack.isEmpty()) throw new CustomException("cannot run empty stack");

        Statement current = executionStack.pop();

        return current.execute(state);
    }

    public ProgramState runAll(int position) throws CustomException {
        ProgramState state = programStates.get(position);
        IStack<Statement> executionStack = state.getExecutionStack();

        while (!executionStack.isEmpty()) {
            executionStack.pop().execute(state);
        }
        System.out.println(state.getOutput().getStream().map(Object::toString).collect(Collectors.joining()));
        return state;
    }
}
