package com.company;

import controller.ExecutionController;
import model.*;
import model.expression.ArithmeticExpression;
import model.expression.ConstantExpression;
import model.expression.Expression;
import model.expression.VariableExpression;
import model.operation.ArithmeticOperation;
import model.statement.*;
import model.type.BooleanType;
import model.type.IntType;
import model.value.BooleanValue;
import model.value.IntValue;
import view.View;

public class Main {

    public static void main(String[] args) throws CustomException {
        var programStateIRepository = new Repository<ProgramState>();
        seedRepository(programStateIRepository);
        var controller = new ExecutionController(programStateIRepository);
        var view = new View(controller);

        view.run();
    }

    private static ProgramState example1() {
        // int v;
        // v = 2;
        // print(v);
        var s1 = new DeclarationStatement("v", new IntType());
        var s2 = new AssignmentStatement("v", new ConstantExpression(new IntValue(2)));
        var s3 = new PrintStatement(new VariableExpression("v"));

        var c1 = new CompoundStatement(s1, s2);
        var c2 = new CompoundStatement(c1, s3);

        var programState = new ProgramState(
                new Stack<>(),
                new Map<>(),
                new List<>(),
                c2
        );

        return programState;
    }

    private static ProgramState example2() {
//        int a;
//        a=2+3*5;
//        int b;
//        b=a-4/2 + 7;
//        Print(b)
        // int a
        var s1 = new DeclarationStatement("a", new IntType());
        // 3 * 5
        var ex1 = new ArithmeticExpression(new ConstantExpression(new IntValue(3)), new ConstantExpression(new IntValue(5)), ArithmeticOperation.Multiplication);
        // 2 + 3 * 5
        var ex2 = new ArithmeticExpression(new ConstantExpression(new IntValue(2)), ex1, ArithmeticOperation.Addition);

        // a = 2 + 3*5
        var s2 = new AssignmentStatement("a", ex2);

        // int b
        var s3 = new DeclarationStatement("b", new IntType());
        // 4 / 2
        var ex3 = new ArithmeticExpression(new ConstantExpression(new IntValue(4)), new ConstantExpression(new IntValue(2)), ArithmeticOperation.Division);
        // a - 4 / 2
        var ex4 = new ArithmeticExpression(new VariableExpression("a"), ex3, ArithmeticOperation.Subtraction);
        // a - 4/2 + 7
        var ex5 = new ArithmeticExpression(ex4, new ConstantExpression(new IntValue(7)), ArithmeticOperation.Addition);

        // b = a - 4/2 + 7
        var s4 = new AssignmentStatement("b", ex5);

        var s5 = new PrintStatement(new VariableExpression("b"));

        var c1 = new CompoundStatement(s1, s2);
        var c2 = new CompoundStatement(c1, s3);
        var c3 = new CompoundStatement(c2, s4);
        var c4 = new CompoundStatement(c3, s5);

        var programState = new ProgramState(
                new Stack<>(),
                new Map<>(),
                new List<>(),
                c4
        );
        return programState;
    }

    private static ProgramState example3() {
        // bool a;
        // a = false;
        // int v;
        // if a then v = 2 else v = 3
        // print(v)

        var s1 = new DeclarationStatement("a", new BooleanType());
        var s2 = new AssignmentStatement("a", new ConstantExpression(new BooleanValue(false)));

        var s3 = new DeclarationStatement("v", new IntType());

        var s4 = new AssignmentStatement("v", new ConstantExpression(new IntValue(2)));
        var s5 = new AssignmentStatement("v", new ConstantExpression(new IntValue(3)));

        var s6 = new ConditionalStatement(s4, s5, new VariableExpression("a"));
        var s7 = new PrintStatement(new VariableExpression("v"));

        var c1 = new CompoundStatement(s1, s2);
        var c2 = new CompoundStatement(c1, s3);
        var c3 = new CompoundStatement(c2, s6);
        var c4 = new CompoundStatement(c3, s7);

        var programState = new ProgramState(
                new Stack<>(),
                new Map<>(),
                new List<>(),
                c4
        );

        return programState;
    }

    private static void seedRepository(IRepository<ProgramState> programStateIRepository) {
        programStateIRepository.add(example1());
        programStateIRepository.add(example2());
        programStateIRepository.add(example3());
    }

}
