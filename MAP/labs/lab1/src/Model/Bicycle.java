package Model;

public class Bicycle extends Vehicle {
    public Bicycle(int id, String color) {
        super(id, color);
    }

    @Override
    public String getVehicleType() {
        return "Bicycle";
    }

    @Override
    public String getSound() {
        return "Ting ting";
    }
}
