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

    @Override
    public String toString() {
        return map.getKeys()
                .stream()
                .map(x -> String.format("%s -> (%d -> %s)", x, map.get(x).getKey(), map.get(x).getValue().stream().map(Object::toString).reduce((acc, curr) -> acc + ", " + curr).orElse("[]")))
                .reduce((acc, curr) -> acc + "\n" + curr)
                .orElse("n\\a");
    }
}
