package model;

public interface IMap<K, V> {
    V get(K key);
    void put(K key, V value);
    boolean containsKey(K key);
}
