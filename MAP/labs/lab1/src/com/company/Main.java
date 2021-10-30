package com.company;

import Controller.VehiclesController;
import Model.*;
import View.Console;

public class Main {

    public static void main(String[] args) throws Exception {
        Repository<Vehicle> repository = new VehicleRepository(5);

        repository.add(new Bicycle(1, "red"));
        repository.add(new Car(2, "blue"));
        repository.add(new Motorcycle(3, "red"));
        repository.add(new Motorcycle(4, "red"));
        repository.add(new Car(5, "green"));

        VehiclesController controller = new VehiclesController(repository);
        Console console = new Console(controller);

        console.run();

        repository.add(new Bicycle(1, "blue"));
    }
}
