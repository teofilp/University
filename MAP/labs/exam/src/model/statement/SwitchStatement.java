package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.expression.RelationalExpression;
import model.operation.RelationalOperator;
import model.type.Type;

public class SwitchStatement implements Statement {
    Expression cond, firstCond, secondCond;
    Statement case1Statement, case2Statement, defaultStatement;

    public SwitchStatement(Expression cond, Expression firstCond, Expression secondCond, Statement case1Statement, Statement case2Statement, Statement defaultStatement) {
        this.cond = cond;
        this.firstCond = firstCond;
        this.secondCond = secondCond;
        this.case1Statement = case1Statement;
        this.case2Statement = case2Statement;
        this.defaultStatement = defaultStatement;
    }

    @Override
    public Statement clone() {
        return new SwitchStatement(
                cond.clone(),
                firstCond.clone(),
                secondCond.clone(),
                case1Statement.clone(),
                case2Statement.clone(),
                defaultStatement.clone());
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var ifCond = new RelationalExpression(cond, firstCond, RelationalOperator.Equal);
        var secondIf = new RelationalExpression(cond, secondCond, RelationalOperator.Equal);

        var nestedIf = new ConditionalStatement(case2Statement, defaultStatement, secondIf);
        var topIf = new ConditionalStatement(case1Statement, nestedIf, ifCond);

        state.getExecutionStack().push(topIf);

        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {
        var condType = cond.typecheck(typeEnv);
        var cond1Type = firstCond.typecheck(typeEnv);
        var cond2Type = secondCond.typecheck(typeEnv);

        if (!condType.equals(cond1Type) || !cond1Type.equals(cond2Type)) throw new CustomException("Conditions must have the same type");

        case1Statement.typeCheck(typeEnv);
        case2Statement.typeCheck(typeEnv);
        defaultStatement.typeCheck(typeEnv);
    }

    @Override
    public String toString() {
        return String.format("switch(%s){\n" +
                "\t case(%s): %s\n" +
                "\t case(%s): %s\n" +
                "\t default: %s\n" +
                "}", cond, firstCond, case1Statement, secondCond, case2Statement, defaultStatement);
    }
}
