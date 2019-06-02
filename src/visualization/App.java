package visualization;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @FXML
    private static ButtonController buttonController;

    public static ButtonController getButtonController() {
        return buttonController;
    }

    public static GraphController getGraphController() {
        return graphController;
    }

    @FXML
    private static GraphController graphController;

    public static void setButtonController(ButtonController buttonController) {
        App.buttonController = buttonController;
    }

    public static void setGraphController(GraphController graphController) {
        App.graphController = graphController;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/scene/dijkstra.fxml"));
        primaryStage.setTitle("Dijkstra");
        primaryStage.setScene(new Scene(root, 1920,1080));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}