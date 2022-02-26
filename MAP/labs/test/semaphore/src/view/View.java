package view;

import controller.ExecutionController;
import model.CustomException;

import java.io.IOException;
import java.util.Scanner;

public class View {
    ExecutionController controller;

    public View(ExecutionController controller) {
        this.controller = controller;
    }

    public void run() throws CustomException, IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose a program (1, 2, 3, 4, 5 - exit)");
            int option = scanner.nextInt();
            if (option == 5) {
                break;
            }
            controller.runAll();
        }
    }
}
