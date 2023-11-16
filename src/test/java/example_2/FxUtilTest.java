package example_2;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class FxUtilTest extends ApplicationTest {

	public void start(Stage stage) throws Exception {
		// Your application start method
		new FxUtil().start(stage);
	}

	@Test
	public void testComboBoxReset() {
		ComboBox<String> comboBox = lookup(".combo-box").query();
		assertNotNull(comboBox);

		clickOn(comboBox).write("da"); // Type "da" to filter items -> Carlos day, Jorge day

		Node item = lookup(".list-cell").nth(0).query();
		assertNotNull(item);
		clickOn(item);
		
		assertEquals("Carlos day", comboBox.getEditor().getText()); // Verify the selected item*/
		javafx.scene.control.Button btnPrintSelectedItem = lookup(".button").query();
		assertNotNull(btnPrintSelectedItem);
		clickOn(btnPrintSelectedItem);

		//assertEquals("", comboBox.getEditor().getText()); // Verify the reset action}

	}
}