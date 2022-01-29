package model;

public class ConcurrentList<T extends Cloneable<T>> extends List<T> {
    @Override
    public synchronized void add(T elem) {
        super.add(elem);
    }

    @Override
    public synchronized T get(int position) {
        return super.get(position);
    }
}
