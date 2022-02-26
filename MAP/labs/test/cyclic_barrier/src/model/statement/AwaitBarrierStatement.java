package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;

public class AwaitBarrierStatement implements Statement {
    private String id;

    public AwaitBarrierStatement(String id) {
        this.id = id;
    }

    @Override
    public Statement clone() {
        return null;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var barrierTable = state.getBarrierTable();

        var value = state.getSymbolTable().get(id);
        var descriptor = ((IntValue) value).getValue();

        if (!barrierTable.containsKey(descriptor)) throw new CustomException(String.format("Cannot find barrier %s", id));

        var pair = barrierTable.get(descriptor);

        var waitingThreads = pair.getValue();
        var barrierSize = pair.getKey();

        if (waitingThreads.size() < barrierSize) {
            if (!waitingThreads.contains(state.getId())) {
                waitingThreads.add(state.getId());
            }

            state.getExecutionStack().push(this);
        }

        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {
        if (!typeEnv.containsKey(id)) throw new CustomException(String.format("Cannot find variable %s", id));
        if (!typeEnv.get(id).equals(new IntType())) throw new CustomException("Barrier descriptor must be an integer");
    }

    @Override
    public String toString() {
        return String.format("AwaitBarrier(%s)", id);
    }
}
