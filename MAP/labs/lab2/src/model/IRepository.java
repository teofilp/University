package model;

public interface IRepository<T> {
    void add(T elem);
    T get(int id);
}
