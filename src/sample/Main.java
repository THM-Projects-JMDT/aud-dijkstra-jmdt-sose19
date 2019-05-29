package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import dijkstra.*;

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

            Graph graph = new Graph();

            //Knoten erzeugen
            Node node1 = new Node(100,100);
            Node node2 = new Node(300,300);

            circles.add(new Circle(node1.getX(),node1.getY(),10));
            graph.addNode(node1);
            circles.add(new Circle(node2.getX(),node2.getY(),10));
            graph.addNode(node2);

            Edge e = graph.link(node1,node2);
            TextField tf = new TextField(e.getDistance() + "");
            lines.add(new Line(node1.getX(),node1.getY(),node2.getX(),node2.getY()));

            for (Circle circle :circles) {
                group.getChildren().add(circle);
            }

            for (Line l:lines) {
                l.setStrokeWidth(2);
                group.getChildren().add(l);
            }

            group.getChildren().add(tf);
            
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
