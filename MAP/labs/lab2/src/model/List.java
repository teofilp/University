package model;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class List<T extends Cloneable> implements IList<T>{
    private final java.util.ArrayList<T> list;

    public List() {
        list = new ArrayList<>();
    }

    public List(ArrayList<T> old) {
        this();
        list.addAll(old);
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
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
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
        ArrayList<T> arr = new ArrayList<>();
        list.forEach(x -> arr.add((T) x.clone()));
        return new List<T>(arr);
    }
}
