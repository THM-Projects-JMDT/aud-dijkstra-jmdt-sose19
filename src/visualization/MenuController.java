package visualization;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MenuController {
    @FXML
    private MenuItem btnMenClose;

    @FXML
    private MenuItem btnMenRnd;

    @FXML
    private MenuItem btnMenClear;

    @FXML
    void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void random(ActionEvent event) {
        App.getButtonController().random(event);
    }

    @FXML
    void clear(ActionEvent event) {
        App.getButtonController().clear(event);
    }
}
