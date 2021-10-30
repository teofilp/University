package model;

import java.util.stream.Stream;

public interface IList<T> {
    void add(T elem);
    T get(int position);
    Stream<T> getStream();
}
