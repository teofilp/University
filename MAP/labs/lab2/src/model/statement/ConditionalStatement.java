package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.type.BooleanType;
import model.value.BooleanValue;
import model.value.Value;

import java.util.Map;

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
        var conditionHolds = getBooleanValue(symbolTable);

        state.pushStatement(conditionHolds.getValue() ? trueStatement : falseStatement);
        return state;
    }

    private BooleanValue getBooleanValue(IMap<String, Value> symbolTable) throws CustomException {
        var conditionValue = condition.evaluate(symbolTable);

        if (!conditionValue.getType().equals(BooleanType.class)) {
            throw new CustomException("Conditional expression is not of type boolean!");
        }

        return (BooleanValue) conditionValue;
    }
}
