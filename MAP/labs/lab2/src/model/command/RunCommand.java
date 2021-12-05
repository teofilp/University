package model.command;

import controller.ExecutionController;
import model.CustomException;

import java.io.IOException;

public class RunCommand extends Command{
    private ExecutionController controller;

    public RunCommand(String key, String description, ExecutionController controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() throws CustomException, IOException, InterruptedException {
        controller.runAllV2();
    }
}
