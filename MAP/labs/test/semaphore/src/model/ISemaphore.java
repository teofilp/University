package model;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ISemaphore extends Cloneable<ISemaphore> {
    int create(int count);
    Map.Entry<Integer, List<Integer>> get(int key);
    boolean containsKey(int key);
    Collection<Integer> getKeys();
}
