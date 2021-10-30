package Model;

import java.util.function.Predicate;
import java.util.stream.Stream;

public interface Repository<T extends Entity> {
    T get(int id);
    Stream<T> getByColor(String color);
    void add(T item) throws Exception;
}
