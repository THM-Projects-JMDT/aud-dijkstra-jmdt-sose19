package visualization;

import dijkstra.*;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static javafx.scene.paint.Color.rgb;

public class DrawGraph {

    private GraphState graphState;
    private Map<Node, Circle> circles;
    private Map<Circle, Label> labels;
    private Map<Edge, Line> lines;
    private Map<Line,Label> labelLines = new HashMap<>();
    private Pane centerGroup;
    private Label error;

    public DrawGraph(Pane centerGroup, Label error) {
        circles = new HashMap<>();
        labels = new HashMap<>();
        lines = new HashMap<>();
        labelLines = new HashMap<>();
        this.graphState = new GraphState();
        this.centerGroup = centerGroup;
        this.error = error;
    }

    public GraphState getGraphState() {
        return graphState;
    }

    public void createCircle(Node node) {
        Circle circle = new Circle(node.getX(), node.getY(),10);
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
            if(graphState.finished()) return;
            if(!graphState.connected()) {
                setFrom(node);
            } else {
                graphState.setTo(node);
                if(graphState.isFrom(graphState.getTo())) {
                    if(!graphState.isStartSelected()) {
                        setStart(node);
                    } else if(!graphState.isEndSelected()){
                        setEnd(node);
                    }
                    graphState.changeConected(false);
                    event.consume();
                    return;
                }
                setTo(node);
            }
            event.consume();
        });
        circles.put(node, circle);
        graphState.getGraph().addNode(node);
        labels.put(circle, labelCircle);
        attachLabelToCircle(circle,labelCircle);
    }

    public void addCircle(Node n) {
        centerGroup.getChildren().add(circles.get(n));
        centerGroup.getChildren().add(labels.get(circles.get(n)));
    }

    private void setFrom(Node n) {
        graphState.setFrom(n);
        Circle c = circles.get(n);
        if(!(graphState.isStart(n) || graphState.isEnd(n))) {
            c.setFill(Color.LIGHTGREEN);
        }
        graphState.changeConected(true);
    }

    private void setTo(Node n) {
        Circle c = circles.get(graphState.getFrom());
        if(!(graphState.isStart(graphState.getFrom()) || graphState.isFrom(graphState.getEnd()))) {
            c.setFill(Color.BLACK);
        }
        setLineFromTo();
    }

    private void setLineFromTo() {
        createLine(graphState.getFrom(), graphState.getTo());
        Edge e = new Edge(graphState.getFrom(), graphState.getTo());
        Line l = lines.get(e);
        centerGroup.getChildren().add(l);
        l.toBack();
        centerGroup.getChildren().add(labelLines.get(l));
        graphState.setFrom(null);
        graphState.setTo(null);
        graphState.changeConected(false);
    }

    private void setStart(Node n) {
        graphState.setStart(n);
        circles.get(n).setFill(Color.YELLOWGREEN);
        graphState.setNodeSelected(true);
    }

    public void resetStart() {
        circles.get(graphState.getFrom()).setFill(Color.BLACK);
    }

    private void setEnd(Node n) {
        graphState.setEnd(n);
        circles.get(n).setFill(Color.BLUEVIOLET);
        App.getGraphController().initializePlay();
    }

    public void newGraph(Node n1, Node n2){
        for (Circle circle :circles.values()) {
            centerGroup.getChildren().add(circle);
        }

        for (Line l:lines.values()) {
            l.setStrokeWidth(2);
            centerGroup.getChildren().add(l);
            l.toBack();
        }
        for (Label label: labels.values()) {
            centerGroup.getChildren().add(label);
        }
        for (Label label: labelLines.values()) {
            centerGroup.getChildren().add(label);
        }

        graphState.setStart(n1);
        graphState.setEnd(n2);
        graphState.findShortestPath();
        graphState.iteratorInit();

        circles.get(n1).setFill(Color.YELLOWGREEN);
        circles.get(n2).setFill(Color.BLUEVIOLET);
        App.getButtonController().enableButtons();
        graphState.changeFinished(true);
    }

    public void markLine(Edge e, Color c) {
        if(lines.get(e) != null) {
            lines.get(e).setStroke(c);
            lines.get(e).setStrokeWidth(5);
        }
    }

    public void updateLabels(Set<Node> nodes) {
        if (nodes.isEmpty()) {
            noNeighbours();
            return;
        }
        nodes.forEach( n -> {
            Label l = labels.get(circles.get(n));
            l.setText(n.toString());
            l.setBackground(new Background(new BackgroundFill(rgb(233,79,100), CornerRadii.EMPTY, Insets.EMPTY)));
        });
    }

    public void createLine(Node node1, Node node2) {
        Edge e = graphState.getGraph().link(node1, node2);
        Line line = new Line(node1.getX(),node1.getY(),node2.getX(),node2.getY());
        line.setStrokeWidth(2);
        line.setOnMouseClicked(Event::consume);
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

    public void markOptimalPath() {
        App.getButtonController().stopLoop();
        App.getButtonController().disableButtons();
        for(Edge e : graphState.getOpt()) {
            markLine(e, Color.BLUE);
        }
    }

    public void noNeighbours() {
        error.setText("keine Nachbarn!");
    }

    public void resetNoNeighbours() {
        error.setText("");
    }
}
