package model;

public class Stack<T> implements IStack<T> {
    private final java.util.Stack<T> stack;

    public Stack() {
        stack = new java.util.Stack<>();
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
}
