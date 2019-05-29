package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
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
            Node node3 = new Node(405, 300);

            circles.add(new Circle(node1.getX(),node1.getY(),5));
            graph.addNode(node1);
            circles.add(new Circle(node2.getX(),node2.getY(),5));
            graph.addNode(node2);
            circles.add(new Circle(node3.getX(),node3.getY(),5));
            graph.addNode(node3);

            Edge e = graph.link(node1,node2);
            String s = Math.round(e.getDistance()) + "";

            Line line = new Line(node1.getX(),node1.getY(),node2.getX(),node2.getY());
            Label label = new Label(s);

            drawLine(line, label);

            lines.add(line);

            for (Circle circle :circles) {
                group.getChildren().add(circle);
            }

            for (Line l:lines) {
                l.setStrokeWidth(2);
                group.getChildren().add(l);
            }

            group.getChildren().add(label);

            primaryStage.setScene(scene);
            primaryStage.show();

            Controller controller = loader.getController();
            controller.setMain(this);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void drawLine(Line line, Label label) {
        label.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        label.layoutXProperty().bind((line.endXProperty().add(line.startXProperty())).divide(2));
        label.layoutYProperty().bind((line.endYProperty().add(line.startYProperty())).divide(2));
    }

    public static void main(String[] args) {
        launch(args);

        Graph g = new Graph();
        Node a = new Node(0,1);
        Node b = new Node(2,2);
        Node c = new Node(3,3);
        Node d = new Node(2, 3);
        Node e = new Node(4,4);
        Node f = new Node(5,1);
        Node h = new Node(6,1);
        g.addNode(a);
        g.addNode(b);
        g.addNode(c);
        g.link(a,b);
        g.link(b,c);
        g.link(a,d);
        g.link(c,d);
        g.link(c, e);
        g.link(f,h);
        g.link(c,f);
        Path path = new Path();
        g.findShortestPath( d, e, path).forEach(System.out::println);
        path.getPath().forEach(System.out::println);

    }
}
