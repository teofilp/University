package com.company;

import controller.ExecutionController;
import model.*;
import model.command.ExitCommand;
import model.command.RunCommand;
import model.expression.ArithmeticExpression;
import model.expression.ConstantExpression;
import model.expression.RelationalExpression;
import model.expression.VariableExpression;
import model.operation.ArithmeticOperator;
import model.operation.RelationalOperator;
import model.statement.*;
import model.type.BooleanType;
import model.type.IntType;
import model.type.StringType;
import model.value.BooleanValue;
import model.value.IntValue;
import model.value.StringValue;
import view.TextMenu;

import java.io.IOException;

public class Interpreter {

    public static void main(String[] args) throws CustomException, IOException {
        var ex1 = example1();
        var repo1 = new Repository<ProgramState>("output1.txt") {{
            add(ex1);
        }};
        var c1 = new ExecutionController(repo1);

        var ex2 = example2();
        var repo2 = new Repository<ProgramState>("output2.txt") {{
            add(ex2);
        }};
        var c2 = new ExecutionController(repo2);

        var ex3 = example3();
        var repo3 = new Repository<ProgramState>("output3.txt") {{
            add(ex3);
        }};
        var c3 = new ExecutionController(repo3);

        var ex4 = example4();
        var repo4 = new Repository<ProgramState>("output4.txt") {{
            add(ex4);
        }};
        var c4 = new ExecutionController(repo4);

        var ex5 = new ConditionalStatement(new PrintStatement(new ConstantExpression(new BooleanValue(true))), new PrintStatement(new ConstantExpression(new BooleanValue(false))),
                new RelationalExpression(new ConstantExpression(new IntValue(1)), new ConstantExpression(new IntValue(2)), RelationalOperator.Greater));
        var repo5 = new Repository<ProgramState>("output5.txt") {{
            add(new ProgramState(
                    new Stack<>(),
                    new Map<>(),
                    new List<>(),
                    new Map<>(),
                    ex5
            ));
        }};
        var c5 = new ExecutionController(repo5);

        var textMenu = new TextMenu();

        textMenu.addCommand(new ExitCommand("0", "Exit"));
        textMenu.addCommand(new RunCommand("1", "Example 1", c1));
        textMenu.addCommand(new RunCommand("2", "Example 2", c2));
        textMenu.addCommand(new RunCommand("3", "Example 3", c3));
        textMenu.addCommand(new RunCommand("4", "Example 4", c4));
        textMenu.addCommand(new RunCommand("5", "Example 5", c5));

        textMenu.run();
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

        return new ProgramState(
                new Stack<>(),
                new Map<>(),
                new List<>(),
                new Map<>(),
                c2
        );
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
        var ex1 = new ArithmeticExpression(new ConstantExpression(new IntValue(3)), new ConstantExpression(new IntValue(5)), ArithmeticOperator.Multiplication);
        // 2 + 3 * 5
        var ex2 = new ArithmeticExpression(new ConstantExpression(new IntValue(2)), ex1, ArithmeticOperator.Addition);

        // a = 2 + 3*5
        var s2 = new AssignmentStatement("a", ex2);

        // int b
        var s3 = new DeclarationStatement("b", new IntType());
        // 4 / 2
        var ex3 = new ArithmeticExpression(new ConstantExpression(new IntValue(4)), new ConstantExpression(new IntValue(2)), ArithmeticOperator.Division);
        // a - 4 / 2
        var ex4 = new ArithmeticExpression(new VariableExpression("a"), ex3, ArithmeticOperator.Subtraction);
        // a - 4/2 + 7
        var ex5 = new ArithmeticExpression(ex4, new ConstantExpression(new IntValue(7)), ArithmeticOperator.Addition);

        // b = a - 4/2 + 7
        var s4 = new AssignmentStatement("b", ex5);

        var s5 = new PrintStatement(new VariableExpression("b"));

        var c1 = new CompoundStatement(s1, s2);
        var c2 = new CompoundStatement(c1, s3);
        var c3 = new CompoundStatement(c2, s4);
        var c4 = new CompoundStatement(c3, s5);

        return new ProgramState(
                new Stack<>(),
                new Map<>(),
                new List<>(),
                new Map<>(),
                c4
        );
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

        return new ProgramState(
                new Stack<>(),
                new Map<>(),
                new List<>(),
                new Map<>(),
                c4
        );
    }

    private static ProgramState example4() {
        var s1 = new DeclarationStatement("varf", new StringType());
        var s2 = new AssignmentStatement("varf", new ConstantExpression(new StringValue("test.txt")));
        var s3 = new OpenFileStatement(new VariableExpression("varf"));
        var s4 = new DeclarationStatement("varc", new IntType());
        var s5 = new ReadFileStatement(new VariableExpression("varf"), "varc");
        var s6 = new PrintStatement(new VariableExpression("varc"));
        var s7 = new ReadFileStatement(new VariableExpression("varf"), "varc");
        var s8 = new PrintStatement(new VariableExpression("varc"));
        var s9 = new CloseFileStatement(new VariableExpression("varf"));

        var c1 = new CompoundStatement(s1, s2);
        var c2 = new CompoundStatement(c1, s3);
        var c3 = new CompoundStatement(c2, s4);
        var c4 = new CompoundStatement(c3, s5);
        var c5 = new CompoundStatement(c4, s6);
        var c6 = new CompoundStatement(c5, s7);
        var c7 = new CompoundStatement(c6, s8);
        var c8 = new CompoundStatement(c7, s9);

        return new ProgramState(
                new Stack<>(),
                new Map<>(),
                new List<>(),
                new Map<>(),
                c8
        );
    }
}
