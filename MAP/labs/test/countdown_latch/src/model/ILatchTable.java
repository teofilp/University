package model;

import java.util.Collection;

public interface ILatchTable extends Cloneable<ILatchTable> {
    int create(int count);
    void put(int key, int newCount);
    int get(int key);
    boolean containsKey(int key);
    Collection<Integer> getKeys();
}
