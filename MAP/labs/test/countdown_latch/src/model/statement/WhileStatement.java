package model.statement;

import model.CustomException;
import model.IMap;
import model.ProgramState;
import model.expression.Expression;
import model.type.BooleanType;
import model.type.Type;
import model.value.BooleanValue;

public class WhileStatement implements Statement {
    private Expression expression;
    private Statement statement;

    public WhileStatement(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        var repeat = getConditionalExpressionResult(state).getValue();
        var executionStack = state.getExecutionStack();

        if (repeat) {
            executionStack.push(this.clone());
            executionStack.push(statement);
        }

        return null;
    }

    @Override
    public void typeCheck(IMap<String, Type> typeEnv) throws CustomException {
        var expressionType = expression.typecheck(typeEnv);

        if (!expressionType.equals(new BooleanType())) throw new CustomException("Expression must be of type boolean");

        statement.typeCheck(typeEnv.clone());
    }

    private BooleanValue getConditionalExpressionResult(ProgramState state) throws CustomException {
        var result = expression.evaluate(state.getSymbolTable(), state.getHeap());

        if (!(result instanceof BooleanValue)) throw new CustomException("Conditional expression must be of type Boolean");

        return (BooleanValue) result;
    }

    @Override
    public Statement clone() {
        return new WhileStatement(expression.clone(), statement.clone());
    }

    @Override
    public String toString() {
        return String.format("while(%s) { %s }", expression, statement);
    }
}
