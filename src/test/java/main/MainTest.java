package main;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Node;
import javafx.stage.Stage;

public class MainTest extends ApplicationTest {

    public void start(Stage stage) throws Exception {
        // Your application start method
        new Main().start(stage);
    }

    @Test
    public void testComboBoxReset() {
        clickOn(".button");
        Node resetButton = lookup("#resetButton").query(); // Query by ID instead of style class
        assertNotNull(resetButton);
        System.out.println(resetButton.getStyleClass()); // Print out the button's styles
        clickOn(resetButton);
        // Perform assertions or verifications after clicking the reset button
    }
}
