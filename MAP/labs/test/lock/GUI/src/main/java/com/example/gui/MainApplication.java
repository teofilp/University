package com.example.gui;

import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        StartupPage controller = new StartupPage();

        controller.showStage();
    }

    public static void main(String[] args) {
        launch();
    }
}