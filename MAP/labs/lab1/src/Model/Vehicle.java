package Model;

public abstract class Vehicle extends Entity {
    protected String color;

    public Vehicle(int id, String color) {
        super(id);
        setColor(color);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String makeSound() {
        return String.format("%s with color %s makes sound %s", getVehicleType(), getColor(), getSound());
    }

    public abstract String getVehicleType();
    public abstract String getSound();
}
