package visualization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ButtonController implements Initializable {
    private Looping looping;

    @FXML
    private Button btnGo;

    @FXML
    private Button btnLoop;

    @FXML
    private Button btnRandom;

    @FXML
    private Button btnClear;

    @FXML
    void clear(ActionEvent event) {
        App.getGraphController().clear();
    }

    @FXML
    void go(ActionEvent event) {
        App.getGraphController().next();
    }

    @FXML
    void loop(ActionEvent event) {
        if(looping.running) {
            stopLoop();
        } else {
            startLoop();
        }
    }

    @FXML
    void random(ActionEvent event) {
        App.getGraphController().random();
    }

    public void stopLoop() {
        btnLoop.setText("AUTO-START");
        looping.running = false;
    }

    public void startLoop() {
        looping = new Looping();
        looping.start();
        btnLoop.setText("STOPP");
    }

    public void enableButtons() {
        btnGo.setDisable(false);
        btnLoop.setDisable(false);
    }

    public void disableButtons() {
        btnGo.setDisable(true);
        btnLoop.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        App.setButtonController(this);
        looping = new Looping();
        looping.running = false;
    }
}