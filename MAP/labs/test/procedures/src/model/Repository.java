package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Repository<T extends Cloneable<T>> implements IRepository<T> {
    IList<T> data;
    String fileName;

    public Repository(String fileName) {
        this.fileName = fileName;
        this.data = new List<>();
    }

    private Repository(IList<T> data, String fileName) {
        this.data = new List<>(data.getStream().map(Cloneable::clone).collect(Collectors.toCollection(ArrayList::new)));
        this.fileName = fileName;
    }

    @Override
    public void add(T elem) {
        data.add(elem);
    }

    @Override
    public T get(int id) {
        return data.get(id);
    }

    @Override
    public void log() throws IOException {
        StringBuilder builder = new StringBuilder().append(
                data.getStream().map(Object::toString).reduce("", (acc, curr) -> String.format("%s %s", acc, curr))
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.append(builder.toString());
        }
    }

    @Override
    public void log(T item) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.append(item.toString());
        }
    }

    @Override
    public IList<T> getItems() {
        return data;
    }

    @Override
    public void setItems(IList<T> newItems) {
        data.clear();
        newItems.getStream().forEach(x -> data.add(x));
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public IRepository<T> clone() {
        return new Repository<T>(data, fileName);
    }
}
