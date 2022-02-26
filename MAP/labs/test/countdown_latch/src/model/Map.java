package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

public class Map<K, V> implements IMap<K, V> {
    private final HashMap<K, V> map;

    public Map() {
        map = new HashMap<>();
    }

    private Map(HashMap<K, V> oldMap) {
        map = (HashMap<K, V>) oldMap.clone();
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

    @Override
    public void remove(K key) {
        map.remove(key);
    }

    @Override
    public String toString(boolean withValues) {
        if (withValues) {
            return toString();
        }

        return map.keySet().stream()
                .map(Objects::toString)
                .reduce("", (acc, curr) -> String.format("%s \n %s", acc, curr));
    }

    @Override
    public Collection<V> getValues() {
        return map.values();
    }

    @Override
    public Stream<V> getStreamValues() {
        return map.values().stream();
    }

    @Override
    public Collection<K> getKeys() {
        return map.keySet();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder().append("{ ");

        for (K key: map.keySet().stream().toList()) {
            builder.append(key).append(": ").append(map.get(key)).append(", ");
        }

        return builder.append(" }").toString();
    }

    @Override
    public IMap<K, V> clone() {
        return new Map<>(map);
    }
}
