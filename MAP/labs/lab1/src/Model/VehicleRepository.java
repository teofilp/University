package Model;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class VehicleRepository implements Repository<Vehicle> {
    private final Vehicle[] items;
    private int size;
    private final int maxSize;

    public VehicleRepository(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.items = new Vehicle[maxSize];
    }

    @Override
    public Vehicle get(int id) {
        Stream<Vehicle> filtered = getFiltered(item -> item.id == id);
        return filtered.findAny().get();
    }

    @Override
    public Stream<Vehicle> getByColor(String color) {
        return getFiltered(item -> item != null && Objects.equals(item.color, color));
    }

    @Override
    public void add(Vehicle item) throws Exception {
        if (size == maxSize) {
            throw new Exception("Cannot add any more vehicles");
        }
        items[size++] = item;
    }

    private Stream<Vehicle> getFiltered(Predicate<? super Vehicle> whereClause) {
        return Arrays.stream(items).filter(whereClause);
    }
}
