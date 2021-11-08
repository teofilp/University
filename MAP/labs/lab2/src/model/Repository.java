package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Repository<T> implements IRepository<T> {
    IList<T> data;
    String fileName;

    public Repository(String fileName) {
        this.fileName = fileName;
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

    @Override
    public void log() throws IOException {
        StringBuilder builder = new StringBuilder().append(
                data.getStream().map(Object::toString).reduce("", (acc, curr) -> String.format("%s %s", acc, curr))
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.append(builder.toString());
        }
    }
}
