package model.command;

import model.CustomException;

import java.io.IOException;

public abstract class Command {
    private final String key, description;

    public Command(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public abstract void execute() throws CustomException, IOException;

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
