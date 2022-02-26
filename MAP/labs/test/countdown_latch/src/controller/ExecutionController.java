package controller;

import model.*;
import model.statement.Statement;
import model.type.ReferenceType;
import model.value.ReferenceValue;
import model.value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutionController {
    private IRepository<ProgramState> programStates;
    private OnUpdateHandler updateHandler;
    private IList<ProgramState> clonedStates;

    public ExecutionController(IRepository<ProgramState> programStates) {
        this.programStates = programStates;
    }

    public ExecutionController(IRepository<ProgramState> programStates, OnUpdateHandler updateHandler) throws CustomException {
        this(programStates);
        this.updateHandler = updateHandler;
        this.clonedStates = programStates.getItems().clone();
        typeCheck(clonedStates.getStream().findFirst().get().getExecutionStack());
        updateHandler.onUpdate(clonedStates);
    }

    public ProgramState runAll() throws CustomException, IOException {
        var clonedStates = programStates.clone();
        var state = clonedStates.get(0);
        IStack<Statement> executionStack = state.getExecutionStack();

        while (!executionStack.isEmpty()) {
            logState(state, clonedStates);
            executionStack.pop().execute(state);
            collectGarbage(clonedStates.getItems());
        }

        logState(state,  clonedStates);

        return state;
    }

    public void typeCheck(IStack<Statement> executionStack) throws CustomException {
        var st = executionStack.pop();
        st.typeCheck(new Map<>());
        executionStack.push(st);
    }

    public void runAllV2() throws InterruptedException, CustomException {
        typeCheck(programStates.getItems().getStream().findFirst().get().getExecutionStack());
        var executor = Executors.newFixedThreadPool(2);
        var oldItems = programStates.getItems().clone(); // used to come to old version to be able to rerun
        var list = removeCompletedPrograms(programStates.getItems());
        list.getStream().forEach(System.out::println);

        while (list.size() > 0) {
            runOnceForAll(executor, list);
            list.getStream().forEach(System.out::println);
            list = removeCompletedPrograms(programStates.getItems());
            collectGarbage(list);
        }

        executor.shutdownNow();
        programStates.setItems(oldItems);
    }

    public void runOnce() throws CustomException, InterruptedException {
        var executor = Executors.newFixedThreadPool(2);
        var list = removeCompletedPrograms(clonedStates);
        list.getStream().forEach(System.out::println);

        runOnceForAllGui(executor, list);
        list.getStream().forEach(System.out::println);
        list = removeCompletedPrograms(clonedStates);
        collectGarbage(list);

        updateHandler.onUpdate(clonedStates);

        executor.shutdownNow();
    }

    private void runOnceForAllGui(ExecutorService executor, IList<ProgramState> states) throws InterruptedException {
        states.getStream().forEach(this::logStates);

        ArrayList<Callable<ProgramState>> callables = states.getStream()
                .map(x -> (Callable<ProgramState>)(x::runOne))
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<ProgramState> newStates = executor.invokeAll(callables).stream()
                .map(this::tryGetProgramState)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));

        newStates.forEach(states::add);
        clonedStates = states;
    }

    private void runOnceForAll(ExecutorService executor, IList<ProgramState> states) throws InterruptedException {
        states.getStream().forEach(this::logStates);

        ArrayList<Callable<ProgramState>> callables = states.getStream()
                .map(x -> (Callable<ProgramState>)(x::runOne))
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<ProgramState> newStates = executor.invokeAll(callables).stream()
                .map(this::tryGetProgramState)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));

        newStates.forEach(states::add);
        programStates.setItems(states);
    }

    private ProgramState tryGetProgramState(Future promise) {
        try {
            return (ProgramState) promise.get();
        } catch (ExecutionException | InterruptedException ex) {}

        return null;
    }

    private void collectGarbage(IList<ProgramState> states) throws CustomException {
        if (states.size() == 0) return;

        var heap = states.get(0).getHeap();
        var symbolTables = states.getStream().map(ProgramState::getSymbolTable).collect(Collectors.toList());
        var usedAddresses = Stream.of(
                        getUsedAddresses(symbolTables),
                        getHeapUsedAddresses(symbolTables, heap)
                )
                .flatMap(x -> x)
                .collect(Collectors.toList());

        new ArrayList<>(heap.getAddresses()).stream()
                .filter(x -> !usedAddresses.contains(x))
                .forEach(x -> deallocateHeapValue(heap, x));
    }

    private void deallocateHeapValue(IHeap heap, int x) {
        System.out.println("Deallocating address: " + x);
        try {
            heap.deallocate(x);
        } catch (CustomException ex) {}
    }

    private <T> Stream<Integer> getUsedAddresses(java.util.List<IMap<String, Value>> hashMaps) {
        return hashMaps.stream().flatMap(IMap::getStreamValues).filter(x -> x instanceof ReferenceValue)
                .map(x -> (ReferenceValue)x)
                .map(ReferenceValue::getAddress);
    }

    private Stream<Integer> getHeapUsedAddresses(java.util.List<IMap<String, Value>> symbolTables, IHeap heap) throws CustomException {
        ArrayList<Integer> usedAddresses = new ArrayList<>();
        ArrayList<ReferenceValue> list = symbolTables.stream().flatMap(IMap::getStreamValues).filter(x -> x instanceof ReferenceValue)
                .map(x -> (ReferenceValue) x).collect(Collectors.toCollection(ArrayList::new));

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

    private IList<ProgramState> removeCompletedPrograms(IList<ProgramState> states) {
        return new List(states.getStream().filter(x -> !x.isCompleted()).collect(Collectors.toCollection(ArrayList::new)));
    }

    private void logState(ProgramState state, IRepository<ProgramState> states) throws IOException {
        System.out.println(state);
        states.log();
    }

    public void logStates(ProgramState state) {
        try {
            programStates.log(state);
        } catch (IOException ex) {}
    }

    public void triggerUpdate() {
        updateHandler.onUpdate(clonedStates);
    }
}
