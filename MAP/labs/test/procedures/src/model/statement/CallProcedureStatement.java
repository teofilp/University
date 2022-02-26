package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.type.Type;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class CallProcedureStatement implements Statement {
    private String procName;
    private ArrayList<Expression> parameters;

    public CallProcedureStatement(String procName, ArrayList<Expression> parameters) {
        this.procName = procName;
        this.parameters = parameters;
    }

    @Override
    public Statement clone() {
        return new CallProcedureStatement(procName, (ArrayList<Expression>)parameters.clone());
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var symbolTable = state.getSymbolTable();
        var symbolTables = state.getSymbolTables();
        var heap = state.getHeap();
        var procedureTable = state.getProcedureTable();
        var executionStack = state.getExecutionStack();

        if (!procedureTable.containsKey(procName)) throw new CustomException(String.format("cannot find procedure %s", procName));

        var procedureDefinition = procedureTable.get(procName);
        var formalParameters = procedureDefinition.getParameters();
        var newSymbolTable = symbolTable.clone();

        IntStream.range(0, parameters.size()).forEach((x) -> {
            try {
                newSymbolTable.put(formalParameters.get(x), parameters.get(x).evaluate(symbolTable, heap));
            } catch (CustomException e) {
                e.printStackTrace();
            }
        });

        symbolTables.push(newSymbolTable);

        executionStack.push(new ReturnStatement());
        executionStack.push(procedureDefinition.getStatement());

        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {

    }

    @Override
    public String toString() {
        return String.format("%s(%s)", procName, parameters.stream().map(Object::toString).reduce("", (acc, curr) -> acc + "," + curr));
    }
}
