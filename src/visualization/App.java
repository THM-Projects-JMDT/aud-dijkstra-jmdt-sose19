package visualization;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {
    @FXML
    private static ButtonController buttonController;

    @FXML
    private static GraphController graphController;

    public static ButtonController getButtonController() {
        return buttonController;
    }

    public static GraphController getGraphController() { return graphController; }

    public static void setButtonController(ButtonController buttonController) { App.buttonController = buttonController; }

    public static void setGraphController(GraphController graphController) {
        App.graphController = graphController;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/scene/dijkstra.fxml"));
        primaryStage.setTitle("Dijkstra");
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, primaryScreenBounds.getWidth(),primaryScreenBounds.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}