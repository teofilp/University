package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;

public class CreateSemaphoreStatement implements Statement {
    private String id;
    private Expression expression;

    public CreateSemaphoreStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public Statement clone() {
        return new CreateSemaphoreStatement(id, expression.clone());
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var symbolTable = state.getSymbolTable();
        var semaphoreTable = state.getSemaphoreTable();
        var heap = state.getHeap();

        var countValue = ((IntValue)expression.evaluate(symbolTable, heap)).getValue();
        var descriptor = semaphoreTable.create(countValue);

        symbolTable.put(id, new IntValue(descriptor));

        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {
        if (!typeEnv.containsKey(id)) throw new CustomException(String.format("Cannot find variable %s", id));
        if (!typeEnv.get(id).equals(new IntType())) throw new CustomException("Variable must be of type integer");
        if (!expression.typecheck(typeEnv).equals(new IntType())) throw new CustomException("Expression must be of type integer");
    }

    @Override
    public String toString() {
        return String.format("CreateSemaphore(%s, %s)", id, expression);
    }
}
