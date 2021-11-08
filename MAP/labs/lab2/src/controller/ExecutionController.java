package controller;

import model.*;
import model.statement.Statement;

import java.io.IOException;

public class ExecutionController {
    private IRepository<ProgramState> programStates;

    public ExecutionController(IRepository<ProgramState> programStates) {
        this.programStates = programStates;
    }

    public ProgramState runOne() throws CustomException {
        ProgramState state = programStates.get(0).clone();
        IStack<Statement> executionStack = state.getExecutionStack();

        if (executionStack.isEmpty()) throw new CustomException("cannot run empty stack");

        Statement current = executionStack.pop();

        return current.execute(state);
    }

    public ProgramState runAll() throws CustomException, IOException {
        ProgramState state = programStates.get(0).clone();
        IStack<Statement> executionStack = state.getExecutionStack();

        while (!executionStack.isEmpty()) {
            System.out.println(state);
            programStates.log();

            executionStack.pop().execute(state);
        }

        System.out.println(state);
        programStates.log();

        return state;
    }
}
