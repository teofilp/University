package model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface IBarrierTable extends Cloneable<IBarrierTable> {
    Map.Entry<Integer, List<Integer>> get(Integer key);
    int put(Map.Entry<Integer, List<Integer>> value);
    boolean containsKey(int Key);
    Collection<Integer> getKeys();
}
