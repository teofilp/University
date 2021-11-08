package view;

import model.CustomException;
import model.command.Command;
import model.IMap;
import model.Map;

import java.io.IOException;
import java.util.Scanner;

public class TextMenu {
    private IMap<String, Command> commands;

    public TextMenu() {
        commands = new Map<String, Command>();
    }

    public void addCommand(Command command) {
        commands.put(command.getKey(), command);
    }

    public void printMenu() {
        for (var command: commands.getValues()) {
            System.out.println(String.format("%s. %s", command.getKey(), command.getDescription()));
        }
    }

    public void run() throws CustomException, IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.println("Enter your option: ");
            String key = scanner.nextLine();

            var command = commands.get(key);

            if (command == null) {
                System.out.println("Invalid option!\n");
                continue;
            }

            command.execute();
        }
    }
}
