package com.example.todolist;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HelloController {
    @FXML
    private ListView<String> taskListView;
    @FXML
    private TextField taskInput;

    private final ObservableList<String> tasks = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        taskListView.setItems(tasks);
        taskListView.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    CheckBox checkBox = new CheckBox(item);
                    checkBox.setSelected(item.startsWith("✓ "));

                    // Formatação visual para tarefas concluídas
                    if (item.startsWith("✓ ")) {
                        checkBox.setTextFill(Color.GRAY);
                        checkBox.setStyle("-fx-strikethrough: true;");
                    }

                    checkBox.setOnAction(e -> {
                        String taskText = checkBox.getText();
                        int index = getIndex();
                        if (checkBox.isSelected()) {
                            tasks.set(index, "✓ " + taskText);
                        } else {
                            tasks.set(index, taskText.replace("✓ ", ""));
                        }
                    });

                    setGraphic(checkBox);
                    setText(null);
                }
            }
        });
    }

    @FXML
    protected void onAddButtonClick() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            taskInput.clear();
        }
    }

    @FXML
    protected void onRemoveButtonClick() {
        int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tasks.remove(selectedIndex);
        }
    }
}