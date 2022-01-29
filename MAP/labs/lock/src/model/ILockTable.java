package model;

import java.util.Collection;

public interface ILockTable extends Cloneable<ILockTable> {
    int get(int key);
    int create(int value);
    void put(int descriptor, int value);
    boolean containsKey(int key);
    Collection<Integer> getKeys();
}
