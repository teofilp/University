package model;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Semaphore implements ISemaphore {
    private IMap<Integer, Map.Entry<Integer, List<Integer>>> map = new ConcurrentMap<>();
    private AtomicInteger nextFreeSpot = new AtomicInteger(1);

    public Semaphore() {
    }

    public Semaphore(IMap<Integer, Map.Entry<Integer, List<Integer>>> map) {
        this.map = map;
    }

    @Override
    public ISemaphore clone() {
        return new Semaphore(map.clone());
    }

    @Override
    public int create(int count) {
        var position = nextFreeSpot.getAndIncrement();

        map.put(position, Map.entry(count, new ArrayList<>()));

        return position;
    }

    @Override
    public Map.Entry<Integer, List<Integer>> get(int key) {
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
}
