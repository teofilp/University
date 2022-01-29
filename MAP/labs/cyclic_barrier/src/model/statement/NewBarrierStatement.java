package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;

import java.util.ArrayList;
import java.util.Map;

public class NewBarrierStatement implements Statement{
    String id;
    Expression expression;

    public NewBarrierStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public Statement clone() {
        return new NewBarrierStatement(id, expression);
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var barrierTable = state.getBarrierTable();
        var symbolTable = state.getSymbolTable();
        var heap = state.getHeap();

        var expressionValue = ((IntValue)expression.evaluate(symbolTable, heap)).getValue();
        var descriptor = barrierTable.put(Map.entry(expressionValue, new ArrayList<>()));

        symbolTable.put(id, new IntValue(descriptor));

        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {
        if (!typeEnv.containsKey(id)) throw new CustomException(String.format("Variable with id %s not found", id));
        if (!typeEnv.get(id).equals(new IntType())) throw new CustomException(String.format("variable with id %s is not of type int", id));
        if (!expression.typecheck(typeEnv).equals(new IntType())) throw new CustomException(String.format("barrier size must be of type integer!"));
    }

    @Override
    public String toString() {
        return String.format("NewBarrierStatement(%s, %s)", id, expression);
    }
}
