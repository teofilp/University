package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class Stack<T> implements IStack<T> {
    private final java.util.Stack<T> stack;

    public Stack() {
        stack = new java.util.Stack<>();
    }

    private Stack(java.util.Stack<T> oldStack) {
        stack = (java.util.Stack<T>)oldStack.clone();
    }

    @Override
    public void push(T elem) {
        stack.push(elem);
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        var end = stack.size();
        var indexes = IntStream.range(0, end).map(i -> end - i - 1).toArray();
        for (var index: indexes) {
            builder.append(stack.get(index)).append(" ||| ");
        }

        return builder.toString();
    }

    @Override
    public IStack<T> clone() {
        return new Stack<>(stack);
    }
}
