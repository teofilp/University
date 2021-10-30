package Controller;

import Model.Entity;
import Model.Repository;
import Model.Vehicle;

import java.util.Arrays;
import java.util.stream.Stream;

public class VehiclesController {
    private Repository<Vehicle> vehicles;

    public VehiclesController(Repository<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public String getRedVehicles() {
        Stream<Vehicle> redVehicles = this.vehicles.getByColor("red");

        return redVehicles
                .reduce(
                        "",
                        (accumulator, item) -> String.format("%s %s, \n", accumulator, item.makeSound()),
                        (i1, i2) -> i1 + i2);
    }
}
