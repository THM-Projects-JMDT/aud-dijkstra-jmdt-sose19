package visualization;

import dijkstra.Generator;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

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
    private Spinner<Integer> spinner;

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

    private ChangeListener<Integer> updateMaxNode = (obs, oldValue, newValue) ->  {
        Generator.setMaxNodeNum(newValue);
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        App.setButtonController(this);
        looping = new Looping();
        looping.running = false;
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 100, 10);
        spinnerValueFactory.valueProperty().addListener(updateMaxNode);
        Generator.setMaxNodeNum(spinnerValueFactory.getValue());
        this.spinner.setValueFactory(spinnerValueFactory);
    }
}