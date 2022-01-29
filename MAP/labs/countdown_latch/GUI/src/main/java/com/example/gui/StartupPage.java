package com.example.gui;

import com.company.Interpreter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.CustomException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class StartupPage implements Initializable {
    private final Stage stage;
    @FXML
    private ListView<String> programsLV;
    @FXML
    private Button pickButton;

    public StartupPage() throws IOException {
        stage = new Stage();

        var loader = new FXMLLoader(getClass().getResource("startup-page-view.fxml"));
        loader.setController(this);

        stage.setScene(new Scene(loader.load(), 1440, 800));
        stage.setTitle("Pick Program to run");
    }

    public void showStage() {
        stage.showAndWait();
    }

    @FXML
    protected void pickProgramToRun() throws IOException, CustomException {
        var selectedItem = programsLV.getSelectionModel().getSelectedItem();
        var selectedProgram = Interpreter.examples.getStream().filter(x -> x.getExecutionStack().toString().equals(selectedItem)).findFirst().get();
        new RunProgramPage(selectedProgram).showStage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        programsLV.setItems(FXCollections.observableList(Interpreter.examples.getStream().map(x -> x.getExecutionStack().toString()).collect(Collectors.toList())));
        pickButton.setOnAction((event) -> {
            try {
                pickProgramToRun();
            } catch (IOException | CustomException e) {
                e.printStackTrace();
            }
        });
    }
}