package model;

import java.io.IOException;
import java.util.ArrayList;

public interface IRepository<T> extends Cloneable<IRepository<T>> {
    void add(T elem);
    T get(int id);
    void log() throws IOException;
    void log(T item) throws IOException;
    IList<T> getItems();
    void setItems(IList<T> newItems);
    int size();
}
