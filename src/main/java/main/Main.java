package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.DocumentoTipo;
import utils.DocumentoTipoStringConverter;

public class Main extends Application {

    private ObservableList<DocumentoTipo> tiposDocumentos = FXCollections.observableArrayList(
            new DocumentoTipo(1, "Requerimento"),
            new DocumentoTipo(2, "Ofício"),
            new DocumentoTipo(3, "Despacho")
    );

    private ObservableList<DocumentoTipo> originalTiposDocumentos;

    @Override
    public void start(Stage primaryStage) {
        originalTiposDocumentos = FXCollections.observableArrayList(tiposDocumentos);

        ComboBox<DocumentoTipo> comboBox = createCombobox();
        comboBox.setId("comboBox");

        // Button to reset ComboBox to its original state
        Button resetButton = new Button("Reset");
        resetButton.getStyleClass().add("button"); // Add the CSS class to the button
        resetButton.setId("resetButton");
       
        VBox root = new VBox(comboBox, resetButton);
        Scene scene = new Scene(root, 400, 350);

        scene.getStylesheets().add(getClass().getResource("/web/style.css").toExternalForm());
        
        resetButton.setOnAction(event -> {
        	
        	/*root.getChildren().remove(0);
        	root.getChildren().add(0, createCombobox());*/
        	
        	/*
        	comboBox.setItems(originalTiposDocumentos);
            comboBox.getSelectionModel().clearSelection();
            comboBox.getEditor().accessibleTextProperty().set("");*/
        	
        	 root.getChildren().clear();
             root.getChildren().addAll(createCombobox(), resetButton);
       
        });
        
        primaryStage.setTitle("Searchable ComboBox Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public ComboBox<DocumentoTipo> createCombobox () {
    	ComboBox<DocumentoTipo> comboBox = new ComboBox<>(tiposDocumentos);
        comboBox.setEditable(true);
        comboBox.setConverter(new DocumentoTipoStringConverter());

        comboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                comboBox.setItems(originalTiposDocumentos);
                comboBox.hide();
            } else {
                ObservableList<DocumentoTipo> filteredItems = FXCollections.observableArrayList();
                for (DocumentoTipo tipo : originalTiposDocumentos) {
                    if (tipo.getDt_descricao().toLowerCase().contains(newValue.toLowerCase())) {
                        filteredItems.add(tipo);
                    }
                }
                comboBox.setItems(filteredItems);
                comboBox.show();
            }
        });

        comboBox.setOnAction(event -> {
            DocumentoTipo selectedTipo = comboBox.getSelectionModel().getSelectedItem();
            if (selectedTipo != null) {
                System.out.println("Selected ID: " + selectedTipo.getDt_id());
                System.out.println("Selected Description: " + selectedTipo.getDt_descricao());
            }
        });
        
        return comboBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
