package model;

import java.util.Collection;
import java.util.stream.Stream;

public interface IMap<K, V> extends Cloneable<IMap<K, V>> {
    V get(K key);
    void put(K key, V value);
    boolean containsKey(K key);
    void remove(K key);
    String toString(boolean withValues);
    Collection<V> getValues();
    Stream<V> getStreamValues();
    Collection<K> getKeys();
}
