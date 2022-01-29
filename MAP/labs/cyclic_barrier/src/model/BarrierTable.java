package model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BarrierTable implements IBarrierTable {
    IMap<Integer, Map.Entry<Integer, List<Integer>>> map = new model.ConcurrentMap<>();
    AtomicInteger nextEmptySpot = new AtomicInteger(1);

    public BarrierTable() {}

    private BarrierTable(IMap<Integer, Map.Entry<Integer, List<Integer>>> map) {
        this.map = map;
    }

    @Override
    public Map.Entry<Integer, List<Integer>> get(Integer key) {
        return map.get(key);
    }

    @Override
    public int put(Map.Entry<Integer, List<Integer>> value) {
        var id = nextEmptySpot.getAndIncrement();
        map.put(id, value);
        return id;
    }

    @Override
    public boolean containsKey(int key) {
        return map.containsKey(key);
    }

    @Override
    public Collection<Integer> getKeys() {
        return map.getKeys();
    }

    @Override
    public IBarrierTable clone() {
        return new BarrierTable(map.clone());
    }
}
