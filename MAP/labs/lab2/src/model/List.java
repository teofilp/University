package model;

import model.IList;

import java.util.ArrayList;
import java.util.stream.Stream;

public class List<T> implements IList<T> {
    private final java.util.ArrayList<T> list;

    public List() {
        list = new ArrayList<>();
    }

    public List(ArrayList<T> old) {
        list = (ArrayList<T>) old.clone();
    }

    @Override
    public void add(T elem) {
        list.add(elem);
    }

    @Override
    public T get(int position) {
        return list.get(position);
    }

    @Override
    public Stream<T> getStream() {
        return list.stream();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append("[ ");

        for (T item: list) {
            stringBuilder.append(item).append(", ");
        }

        return stringBuilder.append(" ]").toString();
    }

    @Override
    public IList<T> clone() {
        return new List<T>(list);
    }
}
