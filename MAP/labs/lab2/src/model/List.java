package model;

import model.IList;

import java.util.ArrayList;
import java.util.stream.Stream;

public class List<T> implements IList<T> {
    private final ArrayList<T> list;

    public List() {
        list = new ArrayList<>();
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


}
