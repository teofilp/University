package controller;

import model.*;
import model.statement.Statement;
import model.type.ReferenceType;
import model.value.ReferenceValue;
import model.value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        var clonedStates = programStates.clone();
        var state = clonedStates.get(0);
        IStack<Statement> executionStack = state.getExecutionStack();

        while (!executionStack.isEmpty()) {
            logState(state, clonedStates);
            executionStack.pop().execute(state);
            collectGarbage(state);
        }

        logState(state,  clonedStates);

        return state;
    }

    private void collectGarbage(ProgramState state) throws CustomException {
        // incomplete: should also look at each value referenced by the available heap values so that I dont delete
        // them by mistake
        var usedAddresses = Stream.of(
                        getUsedAddresses(state.getSymbolTable()),
                        getHeapUsedAddresses(state.getSymbolTable(), state.getHeap())
                )
                .flatMap(x -> x)
                .collect(Collectors.toList());

        new ArrayList<>(state.getHeap().getAddresses()).stream()
                .filter(x -> !usedAddresses.contains(x))
                .forEach(x -> deallocateHeapValue(state.getHeap(), x));
    }

    private void deallocateHeapValue(IHeap heap, int x) {
        System.out.println("Deallocating address: " + x);
        try {
            heap.deallocate(x);
        } catch (CustomException ex) {}
    }

    private <T> Stream<Integer> getUsedAddresses(IMap<T, Value> hashMap){
        return hashMap.getValues().stream().filter(x -> x instanceof ReferenceValue)
                .map(x -> (ReferenceValue)x)
                .map(ReferenceValue::getAddress);
    }

    private Stream<Integer> getHeapUsedAddresses(IMap<String, Value> symbolTable, IHeap heap) throws CustomException {
        ArrayList<Integer> usedAddresses = new ArrayList<>();
        ArrayList<ReferenceValue> list = new ArrayList<>(
                symbolTable.getValues().stream().filter(x -> x instanceof ReferenceValue)
                .map(x -> (ReferenceValue)x)
                        .collect(Collectors.toCollection(ArrayList::new))
        );

        while (list.size() > 0) {
            var first = list.remove(0);

            if (first.getAddress() == ReferenceValue.NULL_ADDRESS) continue;

            usedAddresses.add(first.getAddress());

            if (first.getInnerType() instanceof ReferenceType) {
                list.add((ReferenceValue) heap.get(first.getAddress()));
            }
        }

        return usedAddresses.stream();
    }

    private void logState(ProgramState state, IRepository<ProgramState> states) throws IOException {
        System.out.println(state);
        states.log();
    }
}
