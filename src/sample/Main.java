package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        mainWindow();
    }

    public void mainWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
            Parent root = loader.load();

            Group group = new Group();
            Scene scene = new Scene(group, 600,600);
            List<Circle> circles = new ArrayList<>();
            List<Line> lines = new ArrayList<>();

            circles.add(new Circle(100,100,10));
            circles.add(new Circle(300,150,10));
            circles.add(new Circle(50,200,10));
            circles.add(new Circle(400,500,10));

            lines.add(new Line(100,100,300,150));
            lines.add(new Line(100,100,50,200));
            lines.add(new Line(300,150,400,500));
            lines.add(new Line(50,200,400,500));
            lines.add(new Line(300,150,50,200));

            for (Circle circle :circles) {
                group.getChildren().add(circle);
            }

            for (Line l:lines) {
                l.setStrokeWidth(2);
                group.getChildren().add(l);
            }

            primaryStage.setScene(scene);
            primaryStage.show();

            Controller controller = loader.getController();
            controller.setMain(this);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
