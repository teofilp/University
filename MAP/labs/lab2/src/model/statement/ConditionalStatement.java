package model.statement;

import model.CustomException;
import model.IHeap;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.type.BooleanType;
import model.value.BooleanValue;
import model.value.Value;

public class ConditionalStatement implements Statement {
    Statement trueStatement, falseStatement;
    Expression condition;

    public ConditionalStatement(Statement trueStatement, Statement falseStatement, Expression condition) {
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
        this.condition = condition;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var symbolTable = state.getSymbolTable();
        var conditionHolds = getBooleanValue(symbolTable, state.getHeap());

        state.pushStatement(conditionHolds.getValue() ? trueStatement : falseStatement);
        return state;
    }

    private BooleanValue getBooleanValue(IMap<String, Value> symbolTable, IHeap heap) throws CustomException {
        var conditionValue = condition.evaluate(symbolTable, heap);

        if (!conditionValue.getType().equals(new BooleanType())) {
            throw new CustomException("Conditional expression is not of type boolean!");
        }

        return (BooleanValue) conditionValue;
    }

    @Override
    public String toString() {
        return String.format("if (%s) { %s } else { %s }", condition, trueStatement, falseStatement);
    }

    @Override
    public Statement clone() {
        return new ConditionalStatement(trueStatement.clone(), falseStatement.clone(), condition.clone());
    }
}
