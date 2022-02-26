package model;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class LockTable implements ILockTable {
    private IMap<Integer, Integer> map = new ConcurrentMap<>();
    private AtomicInteger nextFreeSpot = new AtomicInteger(1);

    public LockTable(IMap<Integer, Integer> map) {
        this.map = map;
    }

    public LockTable() {
    }

    @Override
    public ILockTable clone() {
        return new LockTable(map.clone());
    }

    @Override
    public synchronized int get(int key) {
        return map.get(key);
    }

    @Override
    public synchronized int create(int value) {
        var id = nextFreeSpot.getAndIncrement();

        map.put(id, value);

        return id;
    }

    @Override
    public synchronized void put(int descriptor, int value) {
        map.put(descriptor, value);
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
        return map.getKeys().stream().map(x -> String.format("%s -> %s", x, map.get(x))).reduce((acc, curr) -> acc + "\n" + curr).orElse("n\\a");
    }
}
