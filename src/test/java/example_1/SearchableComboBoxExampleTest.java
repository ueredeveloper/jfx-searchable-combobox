package example_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SearchableComboBoxExampleTest extends ApplicationTest {

	public void start(Stage stage) throws Exception {
		// Your application start method
		new SearchableComboBoxExample().start(stage);
	}

	@Test
	public void testComboBoxReset() {
		ComboBox<String> comboBox = lookup(".combo-box").query();
		assertNotNull(comboBox);

		clickOn(comboBox).write("197"); // Type "197" to filter items

		Node item = lookup(".list-cell").nth(0).query();
		assertNotNull(item);
		clickOn(item);
		
		assertEquals("197444", comboBox.getEditor().getText()); // Verify the selected item
		//javafx.scene.control.Button resetButton = lookup(".button").query();
		//assertNotNull(resetButton);
		//clickOn(resetButton);

		//assertEquals("", comboBox.getEditor().getText()); // Verify the reset action}

	}
}