package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import dijkstra.*;

import java.io.IOException;
import java.util.*;

import static javafx.scene.paint.Color.rgb;

public class Main extends Application {

    private Stage primaryStage;
    private Map<Edge, Line> lines = new HashMap<>();
    private Map<Node, Circle> circles = new HashMap<>();
    private Map<Circle, Label> labelCircles = new HashMap<>();
    private Map<Line,Label> labelLines = new HashMap<>();
    private Graph graph;
    private Iterator<Edge> edgeIterator;
    private Iterator<Set<Node>> setIterator;
    private Path path;
    private List<Edge> opt;
    private Pane centerGroup;
    private Group topGroup;
    private Group rightGroup;
    private Button buttonGo, standard, random, clear, loop;
    private boolean switcher;
    private boolean först;
    private boolean looping=true;
    private Looping looping2= new Looping(this);
    private Label note = new Label("");
    private Node from;
    private Node to;
    private Node start;
    private Node end;
    private boolean startSelected;
    private boolean endSelected;
    private boolean connect;
    private boolean finished;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        mainWindow();
    }

    public void mainWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
            Parent root = loader.load();
            BorderPane borderPane = new BorderPane();
            topGroup = new Group();
            rightGroup = new Group();
            centerGroup = new Pane();
            createCaption();
            borderPane.setTop(topGroup);
            borderPane.setRight(rightGroup);
            borderPane.setCenter(centerGroup);
            Scene scene = new Scene(borderPane, 1920,1080);
            buttons();
            graph = new Graph();
            centerGroup.setOnMouseClicked(event -> {
                if(connect) {
                    if(!(from == start || from == end)) {
                        circles.get(from).setFill(Color.BLACK);
                    }
                    from = null;
                    connect = false;
                    return;
                }
                if(finished) return;
                int x = (int) Math.round(event.getX());
                int y = (int) Math.round(event.getY());
                Node n = new Node(x,y);
                createCircle(n);
                centerGroup.getChildren().add(circles.get(n));
                centerGroup.getChildren().add(labelCircles.get(circles.get(n)));
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

    private void startbutton(){
        loop = new Button("Start");
        topGroup.getChildren().add(loop);
        loop.setTranslateY(90);
        looping2.running=false;

        loop.setOnAction(event -> {
            if(looping) {
                loop.setText("Stop");
                looping2= new Looping(this);
                looping2.start();
            } else {
                loop.setText("Start");
                looping2.running=false;
            }

            looping = !looping;
        });
    }


    private void buttons(){
        note.setTranslateX(500);
        centerGroup.getChildren().add(note);
        switcher = true;
        först = true;
        buttonGo = new Button("Weiter machen");
        topGroup.getChildren().add(buttonGo);

        random = new Button("Random Graph");
        topGroup.getChildren().add(random);
        random.setTranslateY(30);

        standard = new Button("Standard Graph");
        topGroup.getChildren().add(standard);
        standard.setTranslateY(60);

        clear = new Button("Löschen");
        topGroup.getChildren().add(clear);
        clear.setTranslateY(120);

        startbutton();
        disableButtons();

        buttonGo.setOnAction(event -> {
            if(finished) {
                gogo();
            }
        });


        random.setOnAction(event -> {
            clear();
            Generator g = new Generator();
            graph = new Graph();
            graph = g.generateGraph();
            for(Node n: graph.getNodes()){
                createCircle(n);
            }
            for (Edge e: graph.getEdges()){
                createLine(e.getA(),e.getB());
            }
            newGraph(g.node1,g.node2);
        });

        standard.setOnAction(event -> {
            clear();
            standard();
        });

        clear.setOnAction(event -> {
            clear();
        });
    }

    private void disableButtons() {
        buttonGo.setDisable(true);
        loop.setDisable(true);
    }

    private void enableButtons() {
        buttonGo.setDisable(false);
        loop.setDisable(false);
    }
    private void clear(){
        connect = false;
        start = null;
        end = null;
        startSelected = false;
        endSelected = false;
        finished = false;
        switcher = true;
        först = true;
        Node.resetCounter();
        circles = new HashMap<>();
        lines = new HashMap<>();
        labelCircles = new HashMap<>();
        labelLines = new HashMap<>();
        centerGroup.getChildren().clear();
        centerGroup.getChildren().removeAll();
  //      buttons();
        note.setText("");
        disableButtons();
    }

    private void standard(){
        graph = new Graph();
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

        newGraph(node2,node7);
    }

    private void createCaption(){
        Label labelRed = new Label("hi");
        labelRed.setBackground(new Background(new BackgroundFill(rgb(236,172,180), CornerRadii.EMPTY, Insets.EMPTY)));
        labelRed.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(1))));
        rightGroup.getChildren().add(labelRed);
        Label labelBlue = new Label("hi");
        labelBlue.setBackground(new Background(new BackgroundFill(rgb(143,198,240), CornerRadii.EMPTY, Insets.EMPTY)));
        labelBlue.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(1))));
        rightGroup.getChildren().add(labelBlue);
        labelBlue.setTranslateY(30);
    }

    public void gogo(){
        note.setText("");
        if (edgeIterator.hasNext() && switcher && !först) {
            markLine(edgeIterator.next(), Color.ORANGERED);
        } else if (setIterator.hasNext() ) {
            updateLabels(setIterator.next());
            först = false;
        } else {
            markOptimalPath(opt);
        }
        switcher = !switcher;
    }

    private void newGraph(Node n1, Node n2){
        for (Circle circle :circles.values()) {
            centerGroup.getChildren().add(circle);
        }

        for (Line l:lines.values()) {
            l.setStrokeWidth(2);
            centerGroup.getChildren().add(l);
            l.toBack();
        }
        for (Label label: labelCircles.values()) {
            centerGroup.getChildren().add(label);
        }
        for (Label label: labelLines.values()) {
            centerGroup.getChildren().add(label);
        }
        path=new Path();
        opt = graph.findShortestPath(n1, n2, path);
        edgeIterator = path.getPath().iterator();
        setIterator = path.getUpdatedNodes().iterator();
        startbutton();

        circles.get(n1).setFill(Color.YELLOWGREEN);
        circles.get(n2).setFill(Color.BLUEVIOLET);
        enableButtons();
        finished = true;
    }


    private void createCircle(Node node) {
        Circle circle = new Circle(node.getX(),node.getY(),10);
        Label labelCircle = new Label(String.valueOf(node.toString()));
        circle.setOnMouseEntered(event -> {
            Paint c = circle.getFill();
            if(c.equals(Color.LIGHTGREEN) || c.equals(Color.BLUEVIOLET) || c.equals(Color.YELLOWGREEN)) {
                return;
            }
            circle.setFill(Color.GREY);
        });
        circle.setOnMouseExited(event -> {
            Paint c = circle.getFill();
            if(c.equals(Color.LIGHTGREEN) || c.equals(Color.BLUEVIOLET) || c.equals(Color.YELLOWGREEN)) {
                return;
            }
            circle.setFill(Color.BLACK);
        });
        circle.setOnMouseClicked(event -> {
            if(finished) return;
            if(!connect) {
                setFrom(node);
            } else {
                to = node;
                if(to == from) {
                    if(!startSelected) {
                        setStart(node);
                    } else if(!endSelected){
                        setEnd(node);
                    }
                    connect = false;
                    event.consume();
                    return;
                }
                setTo(node);
            }
            event.consume();
        });
        circles.put(node, circle);
        graph.addNode(node);
        labelCircles.put(circle, labelCircle);
        attachLabelToCircle(circle,labelCircle);
    }

    private void setFrom(Node n) {
        from = n;
        Circle c = circles.get(n);
        if(!(n == start || n == end)) {
            c.setFill(Color.LIGHTGREEN);
        }
        connect = true;
    }

    private void setTo(Node n) {
        to = n;
        Circle c = circles.get(from);
        if(!(from == start || from == end)) {
            c.setFill(Color.BLACK);
        }
        setLineFromTo();
    }

    private void setLineFromTo() {
        createLine(from, to);
        Edge e = new Edge(from, to);
        Line l = lines.get(e);
        centerGroup.getChildren().add(l);
        l.toBack();
        centerGroup.getChildren().add(labelLines.get(l));
        from = null;
        to = null;
        connect = false;
    }

    private void setStart(Node n) {
        start = n;
        circles.get(n).setFill(Color.YELLOWGREEN);
        startSelected = true;
    }

    private void setEnd(Node n) {
        end = n;
        circles.get(n).setFill(Color.BLUEVIOLET);
        initializePlay();
    }

    private void initializePlay() {
        path = new Path();
        opt = graph.findShortestPath(start, end, path);
        edgeIterator = path.getPath().iterator();
        setIterator = path.getUpdatedNodes().iterator();
        endSelected = true;
        finished = true;
        enableButtons();
    }

    private void markLine(Edge e, Color c) {
        lines.get(e).setStroke(c);
        lines.get(e).setStrokeWidth(5);
    }

    private void updateLabels(Set<Node> nodes) {
        if (nodes.isEmpty()) {
            note.setText("keine Nachbarn");
            return;
        }
        nodes.forEach( n -> {
            Label l = labelCircles.get(circles.get(n));
            l.setText(n.toString());
            l.setBackground(new Background(new BackgroundFill(rgb(233,79,100), CornerRadii.EMPTY, Insets.EMPTY)));
        });
    }

    private void createLine(Node node1, Node node2) {
        Edge e = graph.link(node1, node2);
        Line line = new Line(node1.getX(),node1.getY(),node2.getX(),node2.getY());
        line.setStrokeWidth(2);
        line.setOnMouseClicked(event -> {
            event.consume();
        });
        Label labelLine = new Label(Math.round(e.getDistance()) + "");
        lines.put(e, line);
        labelLines.put(line, labelLine);
        attachLabelToLine(line,labelLine);
    }

    private void attachLabelToCircle(Circle circle, Label label){
        label.setBackground(new Background(new BackgroundFill(rgb(236,172,180), CornerRadii.EMPTY, Insets.EMPTY)));
        label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(1))));
        label.layoutXProperty().bind((circle.centerXProperty().add(10)));
        label.layoutYProperty().bind((circle.centerYProperty().add(-10)));
    }

    private void attachLabelToLine(Line line, Label label) {
        label.setBackground(new Background(new BackgroundFill(rgb(143,198,240), CornerRadii.EMPTY, Insets.EMPTY)));
        label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(1))));
        label.layoutXProperty().bind((line.endXProperty().add(line.startXProperty())).divide(2));
        label.layoutYProperty().bind((line.endYProperty().add(line.startYProperty())).divide(2));
    }

    private void markOptimalPath(List<Edge> edges) {
        for(Edge e : edges) {
            markLine(e, Color.BLUE);
        }
        switcher = false;
        först = true;
        disableButtons();
    }
    public static void main(String[] args) {
        launch(args);

        /*
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
        System.out.println(path.toString());

         */
    }
}
