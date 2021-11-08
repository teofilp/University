package model;

public interface IStack<T> extends Cloneable<IStack<T>> {
    void push(T elem);
    T pop();
    boolean isEmpty();
}
