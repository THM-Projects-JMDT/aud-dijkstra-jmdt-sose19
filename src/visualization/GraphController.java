package visualization;

import dijkstra.Edge;
import dijkstra.Generator;
import dijkstra.Node;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ResourceBundle;

public class GraphController implements Initializable {
    private DrawGraph drawGraph;
    private GraphState graphState;
    private boolean switcher;
    private boolean first;

    @FXML
    private Pane centerGroup;

    @FXML
    private Label error;

    @FXML
    void setNode(MouseEvent event) {
        if(graphState.connected()) {
            if(!(graphState.isFrom(graphState.getStart()) || graphState.isFrom(graphState.getEnd()))) {
                drawGraph.resetStart();
            }
            graphState.setFrom(null);
            graphState.changeConected(false);
            return;
        }
        if(graphState.finished()) return;
        int x = (int) Math.round(event.getX());
        int y = (int) Math.round(event.getY());
        Node n = new Node(x,y);
        drawGraph.createCircle(n);
        drawGraph.addCircle(n);
    }

    public void init() {
        drawGraph = new DrawGraph(centerGroup, error);
        graphState = drawGraph.getGraphState();
        first = true;
        switcher = true;
    }

    public void clear() {
        Node.resetCounter();
        App.getButtonController().disableButtons();
        centerGroup.getChildren().clear();
        centerGroup.getChildren().removeAll();
        init();
        App.getButtonController().stopLoop();
    }

    public void random() {
        clear();
        App.getButtonController().enableButtons();
        Generator g = new Generator();
        graphState.setGraph(g.generateGraph());
        for (Node n : graphState.getGraph().getNodes()) {
            drawGraph.createCircle(n);
        }
        for (Edge e : graphState.getGraph().getEdges()) {
            drawGraph.createLine(e.getA(), e.getB());
        }
        drawGraph.newGraph(g.getStart(), g.getEnd());
    }

    public void initializePlay() {
        graphState.findShortestPath();
        graphState.iteratorInit();
        graphState.changeEndselected(true);
        graphState.changeFinished(true);
        App.getButtonController().enableButtons();
    }

    public void next() {
        if (graphState.finished()) {
            drawGraph.resetNoNeighbours();
            if (graphState.getEdgeIt().hasNext() && switcher && !first) {
                drawGraph.markLine(graphState.getEdgeIt().next(), Color.ORANGERED);
            } else if (graphState.getNodeIt().hasNext()) {
                drawGraph.updateLabels(graphState.getNodeIt().next());
                first = false;
            } else {
                drawGraph.markOptimalPath();
                switcher = false;
                first = true;
            }
            switcher = !switcher;
        }
    }

    private void changeSceneSize() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        centerGroup.setPrefHeight(primaryScreenBounds.getHeight() - 20);
        centerGroup.setPrefWidth(primaryScreenBounds.getWidth() - 638);
        Generator.setMaxHeight((int)centerGroup.getPrefHeight() - 50);
        Generator.setMaxWidth((int)centerGroup.getPrefWidth() - 40);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        App.setGraphController(this);
        changeSceneSize();
        clear();
    }
}
