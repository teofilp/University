package model;

public class Repository<T> implements IRepository<T> {
    IList<T> data;

    public Repository() {
        this.data = new List<>();
    }

    @Override
    public void add(T elem) {
        data.add(elem);
    }

    @Override
    public T get(int id) {
        return data.get(id);
    }
}
