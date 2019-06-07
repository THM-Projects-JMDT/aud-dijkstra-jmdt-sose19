package visualization;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class ExplanationController implements Initializable {
    @FXML
    private TextFlow txtF;

    public void textInit() {
        txtF.getChildren().add(new Text(
                String.format("Mit einem Klick auf die Oberfläche wird ein neuer Punkt erstellt. Wenn man eine Linie ziehen will, wählt man den Startpunkt aus, indem man auf diesen klickt. Den Endpunkt wählt man daraufhin auch mit einem Klick aus. Wenn man einen Punkt doppelt anklickt, wird er zum Startpunkt, also grünlich. Wenn man im Anschluss noch einen Punkt doppelt anklickt, wird dieser zum Endpunkt, also violett. Nun kann man mit 'AUTO-Start' den Algorithmus durchlaufen lassen oder mit 'Weiter' diesen Schritt für Schritt durchgehen. Alternativ kann man sich auch mit 'Zufallsgenerator', einen zufälligen Graphen erstellen lassen. ")
        ));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textInit();
    }
}
