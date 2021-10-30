package Model;

public class Car extends Vehicle {
    public Car(int id, String color) {
        super(id, color);
    }

    @Override
    public String makeSound() {
        return "Vroom vroom";
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

    @Override
    public String getSound() {
        return "Vroom vroom";
    }
}
