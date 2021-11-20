package model;

import java.io.IOException;

public interface IRepository<T> extends Cloneable<IRepository<T>> {
    void add(T elem);
    T get(int id);
    void log() throws IOException;
}
