package example_2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class FxUtil extends Application {

	private ObservableList<MyUser> users = FXCollections.observableArrayList(new MyUser("Carlos day", 20),
			new MyUser("Jorge day", 34));

	@Override
	public void start(Stage primaryStage) {

		ComboBox<MyUser> comboBox = new ComboBox<>(users);
		// || itemToCompare.getAge().toString().equals(typedText)
		FxUtilComboBoxSearchable.autoCompleteComboBoxPlus(comboBox,
				(typedText, itemToCompare) -> itemToCompare.getName().toLowerCase().contains(typedText.toLowerCase()));

		comboBox.setConverter(new StringConverter<MyUser>() {

			@Override
			public String toString(MyUser object) {
				return object != null ? object.getName() : "";
			}

			@Override
			public MyUser fromString(String string) {
				return comboBox.getItems().stream().filter(object -> object.getName().equals(string)).findFirst()
						.orElse(null);
			}

		});

		Button btnPrintSelectedItem = new Button("User selected");

		btnPrintSelectedItem.setOnAction(e -> {
			MyUser user = comboBox.getSelectionModel().getSelectedItem();

			System.out.println("name " + user.getName() + " age " + user.getAge());
		});

		FxUtilComboBoxSearchable.getComboBoxValue(comboBox);

		VBox root = new VBox(comboBox, btnPrintSelectedItem);
		Scene scene = new Scene(root, 300, 250);
		// scene.getStylesheets().add(getClass().getResource("/web/style.css").toExternalForm());
		primaryStage.setTitle("FX Util");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
