package model;

import java.util.HashMap;

public class Map<K, V> implements IMap<K, V> {
    private final HashMap<K, V> map;

    public Map() {
        map = new HashMap<>();
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }
}
