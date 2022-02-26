package model;

public class ConcurrentMap<K, V> extends Map<K, V> {
    @Override
    public synchronized V get(K key) {
        return super.get(key);
    }

    @Override
    public synchronized void put(K key, V value) {
        super.put(key, value);
    }

    @Override
    public synchronized void remove(K key) {
        super.remove(key);
    }
}
