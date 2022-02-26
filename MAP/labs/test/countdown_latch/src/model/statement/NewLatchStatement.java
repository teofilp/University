package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;

public class NewLatchStatement implements Statement {
    private String id;
    private Expression expression;

    public NewLatchStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public Statement clone() {
        return new NewLatchStatement(id, expression.clone());
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var symbolTable = state.getSymbolTable();
        var heap = state.getHeap();
        var latchTable = state.getLatchTable();

        var latchCount = ((IntValue)expression.evaluate(symbolTable, heap)).getValue();
        var descriptor = latchTable.create(latchCount);

        symbolTable.put(id, new IntValue(descriptor));

        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {
        if (!typeEnv.containsKey(id)) throw new CustomException(String.format("Cannot find variable %s", id));
        if (!typeEnv.get(id).equals(new IntType())) throw new CustomException("Variable must be of type int");
        if (!expression.typecheck(typeEnv).equals(new IntType())) throw new CustomException("Expression must be of type integer");
    }

    @Override
    public String toString() {
        return String.format("NewLatch(%s)", id);
    }
}
