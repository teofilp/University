package com.example.gui;

import controller.ExecutionController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.*;

import java.io.IOException;
import java.util.stream.Collectors;

public class RunProgramPage {
    @FXML
    private ProgramState activeProgram;
    @FXML
    private TableView<HeapTableRow> heapTV;
    @FXML
    private TableView<SymbolTableRow> symbolTableTV;
    @FXML
    private ListView outLV, fileTableLV, exeStackLV;
    @FXML
    private ListView<Integer> statesIdsLV;
    @FXML
    private TextField activeStates;
    @FXML
    private Button runOnceButton;
    @FXML
    private Label selectedThreadIdLb;
    @FXML
    private TableView<LockTableRow> lockTV;

    private int selectedThreadId;
    private ExecutionController executionController;

    private final Stage stage;

    public RunProgramPage(ProgramState activeProgram) throws IOException, CustomException {
        this.activeProgram = activeProgram;
        this.selectedThreadId = activeProgram.getId();

        stage = new Stage();
        var loader = new FXMLLoader(getClass().getResource("run-program-view.fxml"));
        loader.setController(this);

        stage.setScene(new Scene(loader.load(), 1440, 800));
        stage.setTitle("Run Program");

        setupController();
        setupLayout();
    }

    private void setupController() throws CustomException {
        Repository<ProgramState> repo = new Repository<>("gui.txt") {{ add(activeProgram); }};
        executionController = new ExecutionController(repo, this::updateLayout);
    }

    private void updateLayout(IList<ProgramState> programStates) {
        var optionalState = programStates.getStream().filter(x -> x.getId() == selectedThreadId).findFirst();
        var state = optionalState.orElse(null);
        if (state == null) return;
        updateSymbolTableTableView(state);
        updateOutListView(state);
        updateHeapTableView(state);
        updateExecutionStackListView(state);
        updateFileTableListView(state);
        updateStateIdsListView(programStates);
        updateActiveStates(programStates);
        updateLockTableView(state);
        updateActiveThreadId();
    }

    private void updateLockTableView(ProgramState state) {
        var lockTable = state.getLockTable();

        lockTV.getItems().setAll(lockTable.getKeys()
                .stream()
                .map(x -> new LockTableRow(x, lockTable.get(x)))
                .collect(Collectors.toList()));
    }

    private void updateActiveThreadId() {
        selectedThreadIdLb.setText(String.format("Selected thread id: %d", selectedThreadId));
    }

    private void updateActiveStates(IList<ProgramState> programStates) {
        activeStates.setText(String.valueOf(programStates.size()));
    }

    private void updateStateIdsListView(IList<ProgramState> states) {
        statesIdsLV.getItems().setAll(states.getStream().map(ProgramState::getId).toList());
    }

    private void updateFileTableListView(ProgramState state) {
        var fileTable = state.getFileTable();
        var list = fileTable.getKeys().stream().toList();
        fileTableLV.getItems().setAll(list);
    }

    private void updateExecutionStackListView(ProgramState state) {
        var exeStack = state.getExecutionStack();
        var list = exeStack.getItems().getStream().toList();
        exeStackLV.getItems().setAll(list);
    }

    private void updateHeapTableView(ProgramState state) {
        var heap = state.getHeap();
        var list = heap.getAddresses().stream().map(x -> {
            try {
                return new HeapTableRow(x.toString(), heap.get(x).toString());
            } catch (CustomException e) {
                e.printStackTrace();
                return null;
            }
        }).toList();
        heapTV.getItems().setAll(list);
    }

    private void updateOutListView(ProgramState state) {
        outLV.setItems(FXCollections.observableList(state.getOutput().getStream().map(Object::toString).toList()));
    }

    private void updateSymbolTableTableView(ProgramState state) {
        var symbolTable = state.getSymbolTable();
        var list = symbolTable.getKeys().stream().map(x -> new SymbolTableRow(x, symbolTable.get(x).toString())).toList();
        symbolTableTV.getItems().setAll(list);
    }

    private void setupLayout() {
        setupHeapTableView();
        setupOutListView();
        setupFileTableListView();
        setupStatesIdsListView();
        setupSymbolTableTableView();
        setupExeStackListView();
        setupLockTableView();
        setupRunOnceButton();
    }

    private void setupLockTableView() {
        var keyCol = new TableColumn("Key");
        var valueCol = new TableColumn("Value");

        keyCol.setCellValueFactory(new PropertyValueFactory<>("key"));
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        lockTV.getColumns().setAll(keyCol, valueCol);
        lockTV.setMaxHeight(200);
    }

    private void setupRunOnceButton() {
        runOnceButton.setOnAction((event) -> {
            try {
                executionController.runOnce();
            } catch (CustomException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void setupExeStackListView() {
        exeStackLV.setMaxHeight(200);
    }

    private void setupSymbolTableTableView() {
        var idCol = new TableColumn("Id");
        var valueCol = new TableColumn("Value");

        idCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        symbolTableTV.getColumns().setAll(idCol, valueCol);
        symbolTableTV.setMaxHeight(200);
    }

    private void setupStatesIdsListView() {
        statesIdsLV.setMaxHeight(200);
        statesIdsLV.setOnMouseClicked(event -> {
            selectedThreadId = statesIdsLV.getSelectionModel().getSelectedItem();
            executionController.triggerUpdate();
        });
    }

    private void setupFileTableListView() {
        fileTableLV.setMaxHeight(200);
    }

    private void setupOutListView() {
        outLV.setMaxHeight(200);
    }

    private void setupHeapTableView() {
        var addressCol = new TableColumn("Address");
        var valueCol = new TableColumn("Value");

        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        heapTV.getColumns().setAll(addressCol, valueCol);
        heapTV.setMaxHeight(200);
    }

    public void showStage() {
        stage.showAndWait();
    }
}
