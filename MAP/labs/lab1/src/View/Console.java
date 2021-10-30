package View;

import Controller.VehiclesController;

public class Console {

    private VehiclesController controller;

    public Console(VehiclesController controller) {
        this.controller = controller;
    }

    public String getRedVehicles() {
        return controller.getRedVehicles();
    }

    public void run() {
        System.out.println(getRedVehicles());
    }
}
