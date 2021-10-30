package Model;

public class Motorcycle extends Vehicle {
    public Motorcycle(int id, String color) {
        super(id, color);
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }

    @Override
    public String getSound() {
        return "Small vroom vroom";
    }
}
