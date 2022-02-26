package model;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class LatchTable implements ILatchTable {
    private IMap<Integer, Integer> map = new ConcurrentMap<>();
    private AtomicInteger nextFreeSpot = new AtomicInteger(1);

    public LatchTable(IMap<Integer, Integer> map) {
        this.map = map;
    }

    public LatchTable() { }

    @Override
    public ILatchTable clone() {
        return new LatchTable(map.clone());
    }

    @Override
    public int create(int count) {
        var position = nextFreeSpot.getAndIncrement();

        map.put(position, count);

        return position;
    }

    @Override
    public void put(int key, int newCount) {
        map.put(key, newCount);
    }

    @Override
    public int get(int key) {
        return map.get(key);
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
    public String toString() {
        return map.getKeys().stream()
                .map(x -> String.format("%s -> %s", x, map.get(x)))
                .reduce((acc, curr) -> acc + "\n" + curr)
                .orElse("n\\a");
    }
}
