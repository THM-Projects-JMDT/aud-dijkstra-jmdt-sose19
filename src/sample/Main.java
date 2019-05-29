package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import dijkstra.*;

import java.io.IOException;
import java.util.*;

public class Main extends Application {

    private Stage primaryStage;
    private List<Circle> circles = new ArrayList<>();
    private Map<Edge, Line> lines = new HashMap<>();
    private List<Label> labeleCircles = new ArrayList<>();
    private List<Label> labelLines = new ArrayList<>();
    private Graph graph = new Graph();
    private Iterator<Edge> iterator;

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
            Scene scene = new Scene(group, 1080,720);

/*
            scene.setOnMouseClicked(event -> {
                int x = (int) Math.round(event.getX());
                int y = (int) Math.round(event.getY());
                Node n = new Node(x,y);
                createCircle(n);
                group.getChildren().add(circles.get(circles.size() - 1));
            });


*/
            //Knoten erzeugen
            Node node1 = new Node(100,100);
            Node node2 = new Node(350,100);
            Node node3 = new Node(400, 120);
            Node node4 = new Node(205,200);
            Node node5 = new Node(440, 400);
            Node node6 = new Node(300,700);
            Node node7 = new Node(700,290);
            Node node8 = new Node(600,500);
            Node node9 = new Node(860,390);

            createCircle(node1);
            createCircle(node2);
            createCircle(node3);
            createCircle(node4);
            createCircle(node5);
            createCircle(node6);
            createCircle(node7);
            createCircle(node8);
            createCircle(node9);

            createLine(node1, node4);
            createLine(node2, node4);
            createLine(node2, node3);
            createLine(node4, node6);
            createLine(node6, node7);
            createLine(node7, node9);
            createLine(node6, node9);
            createLine(node6, node8);

            for (Circle circle :circles) {
                group.getChildren().add(circle);
            }

            for (Line l:lines.values()) {
                l.setStrokeWidth(2);
                group.getChildren().add(l);
            }
            for (Label label: labeleCircles) {
                group.getChildren().add(label);
            }
            for (Label label: labelLines) {
                group.getChildren().add(label);
            }

            Path path = new Path();
            List<Edge> opt = graph.findShortestPath(node7, node2, path);
            iterator = path.getPath().iterator();
            Button buttonGo = new Button("Weiter machen");
            Button random = new Button("Neuer Graph");
            group.getChildren().add(buttonGo);
            group.getChildren().add(random);
            random.setTranslateY(30);
            buttonGo.setOnAction(event -> {
                if (iterator.hasNext()) {
                    markLine(iterator.next(), Color.RED);
                } else {
                    markOptimalPath(opt);
                }
            });
            primaryStage.setScene(scene);
            primaryStage.show();

            Controller controller = loader.getController();
            controller.setMain(this);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void createCircle(Node node) {
        Circle circle1 = new Circle(node.getX(),node.getY(),5);
        Label labelCircle = new Label(String.valueOf(node.getLabel()));
        circles.add(circle1);
        graph.addNode(node);
        labeleCircles.add(labelCircle);
        attachLabelToCircle(circle1,labelCircle);
    }

    private void markLine(Edge e, Color c) {
        lines.get(e).setStroke(c);
        lines.get(e).setStrokeWidth(5);
    }

    private void createLine(Node node1, Node node2) {
        Edge e = graph.link(node1, node2);
        Line line = new Line(node1.getX(),node1.getY(),node2.getX(),node2.getY());
        Label labelLine = new Label(Math.round(e.getDistance()) + "");
        lines.put(e, line);
        labelLines.add(labelLine);
        attachLabelToLine(line,labelLine);
    }

    private void attachLabelToCircle(Circle circle, Label label){
        label.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        label.layoutXProperty().bind((circle.centerXProperty().add(10)));
        label.layoutYProperty().bind((circle.centerYProperty().add(-10)));
    }

    private void attachLabelToLine(Line line, Label label) {
        label.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        label.layoutXProperty().bind((line.endXProperty().add(line.startXProperty())).divide(2));
        label.layoutYProperty().bind((line.endYProperty().add(line.startYProperty())).divide(2));
    }

    private void markOptimalPath(List<Edge> edges) {
        for(Edge e : edges) {
            markLine(e, Color.GREEN);
        }
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

    }
}
