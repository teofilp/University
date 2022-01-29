package com.company;

import controller.ExecutionController;
import model.*;
import model.command.ExitCommand;
import model.command.RunCommand;
import model.expression.*;
import model.operation.ArithmeticOperator;
import model.operation.RelationalOperator;
import model.statement.*;
import model.type.BooleanType;
import model.type.IntType;
import model.type.ReferenceType;
import model.type.StringType;
import model.value.BooleanValue;
import model.value.IntValue;
import model.value.StringValue;
import view.TextMenu;

import java.io.IOException;
import java.util.ArrayList;

public class Interpreter {

    public static final List<ProgramState> examples = new List<>() {{
        add(example1());
        add(example2());
        add(example3());
        add(example4());
        add(example5());
        add(example6());
        add(example7());
        add(example8());
        add(example9());
        add(example10());
    }};

    public static void main(String[] args) throws CustomException, IOException, InterruptedException {
        var textMenu = new TextMenu();
        textMenu.addCommand(new ExitCommand("0", "Exit"));
        examples.getStream().forEach((x) -> addExample(x, textMenu));
        textMenu.run();
    }

    private static void addExample(ProgramState state, TextMenu menu) {
        var repo = new Repository<ProgramState>(String.format("output%d.txt", menu.getCommandsNo())) {{
            add(state);
        }};
        var c1 = new ExecutionController(repo);

        var index = menu.getCommandsNo();
        menu.addCommand(new RunCommand(String.valueOf(index), String.format("Example %d: [%s]", index, state.getExecutionStack()), c1));
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
                new ConcurrentList<>(),
                new ConcurrentMap<>(),
                new Heap(),
                c2,
                new ProcedureTable());
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
                new ConcurrentList<>(),
                new ConcurrentMap<>(),
                new Heap(),
                c4,
                new ProcedureTable());
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
                new ConcurrentList<>(),
                new ConcurrentMap<>(),
                new Heap(),
                c4,
                new ProcedureTable());
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
                new ConcurrentList<>(),
                new ConcurrentMap<>(),
                new Heap(),
                c8,
                new ProcedureTable());
    }

    private static ProgramState example5() {
        // Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        var s1 = new DeclarationStatement("v", new ReferenceType(new IntType()));
        var s2 = new NewReferenceStatement("v", new ConstantExpression(new IntValue(20)));
        var e1 = new ReadHeapExpression(new VariableExpression("v"));
        var s3 = new PrintStatement(e1);
        var s4 = new WriteHeapStatement("v", new ConstantExpression(new IntValue(30)));
        var e2 = new ReadHeapExpression(new VariableExpression("v"));
        var e3 = new ArithmeticExpression(e2, new ConstantExpression(new IntValue(5)), ArithmeticOperator.Addition);
        var s5 = new PrintStatement(e3);

        var c1 = new CompoundStatement(s1, s2);
        var c2 = new CompoundStatement(c1, s3);
        var c3 = new CompoundStatement(c2, s4);
        var c4 = new CompoundStatement(c3, s5);

        return new ProgramState(
                new Stack<>(),
                new Map<>(),
                new ConcurrentList<>(),
                new ConcurrentMap<>(),
                new Heap(),
                c4,
                new ProcedureTable());
    }

    private static ProgramState example6() {
        // Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))

        var s1 = new DeclarationStatement("v", new ReferenceType(new IntType()));
        var s2 = new NewReferenceStatement("v", new ConstantExpression(new IntValue(20)));
        var s3 = new DeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType())));
        var s4 = new NewReferenceStatement("a", new VariableExpression("v"));
        var s5 = new NewReferenceStatement("v", new ConstantExpression(new IntValue(30)));
        var e1 = new ReadHeapExpression(new VariableExpression("a"));
        var e2 = new ReadHeapExpression(e1);
        var s6 = new PrintStatement(e2);

        var c1 = new CompoundStatement(s1, s2);
        var c2 = new CompoundStatement(c1, s3);
        var c3 = new CompoundStatement(c2, s4);
        var c4 = new CompoundStatement(c3, s5);
        var c5 = new CompoundStatement(c4, s6);

        return new ProgramState(
                new Stack<>(),
                new Map<>(),
                new ConcurrentList<>(),
                new ConcurrentMap<>(),
                new Heap(),
                c5,
                new ProcedureTable());
    }

    private static ProgramState example7() {
        // int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        var s1 = new DeclarationStatement("v", new IntType());
        var s2 = new AssignmentStatement("v", new ConstantExpression(new IntValue(4)));

        var s3 = new PrintStatement(new VariableExpression("v"));
        var s4 = new AssignmentStatement("v",
                new ArithmeticExpression(
                        new VariableExpression("v"), new ConstantExpression(new IntValue(1)), ArithmeticOperator.Subtraction)
        );
        var s5 = new CompoundStatement(s3, s4);
        var e1 = new RelationalExpression(new VariableExpression("v"), new ConstantExpression(new IntValue(0)), RelationalOperator.Greater);

        var s6 = new WhileStatement(e1, s5);

        var s7 = new PrintStatement(new VariableExpression("v"));

        var c1 = new CompoundStatement(s1, s2);
        var c2 = new CompoundStatement(c1, s6);
        var c3 = new CompoundStatement(c2, s7);

        return new ProgramState(
                new Stack<>(),
                new Map<>(),
                new ConcurrentList<>(),
                new ConcurrentMap<>(),
                new Heap(),
                c3,
                new ProcedureTable());
    }

    private static ProgramState example8() {
        // Ref ref ref int v;
        // ref ref int w;
        // ref int x;
        // new(x, 3)
        // new (w, x)
        // new (v, w)
        // new (x, 4)
        // new (x, 5)
        // readHeap(readHeap(readHeap(v))))
        var s1 = new DeclarationStatement("v", new ReferenceType(new ReferenceType(new ReferenceType(new IntType()))));
        var s2 = new DeclarationStatement("w", new ReferenceType(new ReferenceType(new IntType())));
        var s3 = new DeclarationStatement("x", new ReferenceType(new IntType()));
        var s4 = new NewReferenceStatement("x", new ConstantExpression(new IntValue(3)));
        var s5 = new NewReferenceStatement("w", new VariableExpression("x"));
        var s6 = new NewReferenceStatement("v", new VariableExpression("w"));
        var s7 = new NewReferenceStatement("x", new ConstantExpression(new IntValue(4)));
        var e1 = new ReadHeapExpression(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("v"))));
        var s8 = new PrintStatement(e1);
        var s9 = new NewReferenceStatement("x", new ConstantExpression(new IntValue(5)));

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
                new ConcurrentList<>(),
                new ConcurrentMap<>(),
                new Heap(),
                c8,
                new ProcedureTable());
    }

    private static  ProgramState example9() {
        var s1 = new DeclarationStatement("v", new IntType());
        var s2 = new DeclarationStatement("a", new ReferenceType(new IntType()));
        var s3 = new AssignmentStatement("v", new ConstantExpression(new IntValue(10)));
        var s4 = new NewReferenceStatement("a", new ConstantExpression(new IntValue(22)));

        var s5 = new WriteHeapStatement("a", new ConstantExpression(new IntValue(30)));
        var s6 = new AssignmentStatement("v", new ConstantExpression(new IntValue(32)));
        var s7 = new PrintStatement(new VariableExpression("v"));
        var s8 = new PrintStatement(new ReadHeapExpression(new VariableExpression("a")));

        var c1 = new CompoundStatement(s1, s2);
        var c2 = new CompoundStatement(c1, s3);
        var c3 = new CompoundStatement(c2, s4); // int v; Ref int a; v = 10; new(a, 22)

        var c5 = new CompoundStatement(s5, s6);
        var c6 = new CompoundStatement(c5, s7);
        var c7 = new CompoundStatement(c6, s8);

        var s9 = new RunParallelStatement(c7); // fork part

        var s10 = new PrintStatement(new VariableExpression("v"));
        var s11 = new PrintStatement(new ReadHeapExpression(new VariableExpression("a")));

        var c8 = new CompoundStatement(c3, s9);
        var c9 = new CompoundStatement(c8, s10);
        var c10 = new CompoundStatement(c9, s11);

        return new ProgramState(
                new Stack<>(),
                new Map<>(),
                new ConcurrentList<>(),
                new ConcurrentMap<>(),
                new Heap(),
                c10,
                new ProcedureTable());
    }

    private static ProgramState example10() {
        var params = new ArrayList<String>() {{
            add("a");
            add("b");
        }};

        var s1 = new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("a"), new VariableExpression("b"), ArithmeticOperator.Addition));
        var s2 = new PrintStatement(new VariableExpression("v"));
        var c1 = new CompoundStatement(s1, s2); // sum body

        var s3 = new CreateProcedureStatement("sum", new ProcedureDefinition(params, c1)); // sum proc

        var s6 = new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("a"), new VariableExpression("b"), ArithmeticOperator.Multiplication));
        var c2 = new CompoundStatement(s6, s2); // product body
        var s7 = new CreateProcedureStatement("product", new ProcedureDefinition(params, c2)); // product proc

        // s3, s7

        var s8 = new DeclarationStatement("v", new IntType());
        var s9 = new DeclarationStatement("w", new IntType());
        var s10 = new AssignmentStatement("v", new ConstantExpression(new IntValue(2)));
        var s11 = new AssignmentStatement("w", new ConstantExpression(new IntValue(5)));

        var s12 = new CallProcedureStatement("sum", new ArrayList<>() {{
            add(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(new IntValue(10)), ArithmeticOperator.Multiplication));
            add(new VariableExpression("w"));
        }}); // call sum(v*10, w)

        var s13 = new PrintStatement(new VariableExpression("v")); // print(v)

        var s14 = new CallProcedureStatement("product", new ArrayList<>() {{
            add(new VariableExpression("v"));
            add(new VariableExpression("w"));
        }});

        var s15 = new CallProcedureStatement("sum", new ArrayList<>() {{
            add(new VariableExpression("v"));
            add(new VariableExpression("w"));
        }});

        var s16 = new RunParallelStatement(s15);

        var c3 = new CompoundStatement(s14, s16);

        var s17 = new RunParallelStatement(c3); // fork of fork

        var c4 = new CompoundStatement(s13, s17);
        var c5 = new CompoundStatement(s12, c4);
        var c6 = new CompoundStatement(s11, c5);
        var c7 = new CompoundStatement(s10, c6);
        var c8 = new CompoundStatement(s9, c7);
        var c9 = new CompoundStatement(s8, c8);
        var c10 = new CompoundStatement(s7, c9);
        var c11 = new CompoundStatement(s3, c10);

        return new ProgramState(
                new Stack<>(),
                new Map<>(),
                new ConcurrentList<>(),
                new ConcurrentMap<>(),
                new Heap(),
                c11,
                new ProcedureTable());
    }

}
