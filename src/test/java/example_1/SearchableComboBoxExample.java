package example_1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchableComboBoxExample extends Application {

    private ObservableList<String> processos = FXCollections.observableArrayList(
            "197444", "197555", "197666", "198888", "199999");

    private ObservableList<String> originalProcessos;

    @Override
    public void start(Stage primaryStage) {
        originalProcessos = FXCollections.observableArrayList(processos);

        ComboBox<String> comboBox = new ComboBox<>(processos);
        comboBox.getStyleClass().add("combo-box");
        comboBox.setEditable(true);

        comboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                resetComboBox(comboBox);
            } else {
                ObservableList<String> filteredItems = FXCollections.observableArrayList();
                for (String processo : originalProcessos) {
                    if (processo.contains(newValue)) {
                        filteredItems.add(processo);
                    }
                }
                comboBox.setItems(filteredItems);
                comboBox.show();
            }
        });

        comboBox.setOnAction(event -> {
            String selectedProcess = comboBox.getSelectionModel().getSelectedItem();
            comboBox.getEditor().setText(selectedProcess);
        });

        javafx.scene.control.Button resetButton = new javafx.scene.control.Button("Reset");
        resetButton.setId("resetButton");
        resetButton.setOnAction(event -> resetComboBox(comboBox));

        VBox root = new VBox(comboBox, resetButton);
        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add(getClass().getResource("/web/style.css").toExternalForm());
        primaryStage.setTitle("Searchable ComboBox Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void resetComboBox(ComboBox<String> comboBox) {
        comboBox.setItems(originalProcessos);
        comboBox.getEditor().setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
