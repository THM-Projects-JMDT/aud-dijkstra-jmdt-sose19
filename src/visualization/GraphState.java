package visualization;

import dijkstra.Edge;
import dijkstra.Graph;
import dijkstra.Node;
import dijkstra.Path;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GraphState {
    private Graph currentGraph;
    private Path path;
    private Iterator<Edge> edgeIt;
    private Iterator<Set<Node>> nodeIt;
    private Node form;
    private Node to;
    private Node start;
    private Node end;
    private List<Edge> opt;
    private boolean finished;
    private boolean connect;
    private boolean nodeSelected;
    private boolean endSelected;

    public GraphState() {
        path = new Path();
        finished = true;
        connect = false;
        start = null;
        end = null;
        finished = false;
        currentGraph = new Graph();
    }

    //Getter

    public Node getFrom() {
        return this.form;
    }

    public Node getTo() {
        return this.to;
    }

    public Node getEnd() {
        return this.end;
    }

    public Graph getGraph() {
        return this.currentGraph;
    }

    public Iterator<Edge> getEdgeIt() {
        return edgeIt;
    }

    public Iterator<Set<Node>> getNodeIt() {
        return nodeIt;
    }

    public List<Edge> getOpt() {
        return opt;
    }

    public Node getStart() {
        return this.start;
    }

    //Setter

    public void setStart(Node start) {
        this.start = start;
    }

    public void setNodeSelected(boolean b) {
        nodeSelected = b;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public void setFrom(Node from) { this.form = from; }

    public void setGraph(Graph graph) {
        currentGraph = graph;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    //Boolean Getter

    public boolean finished() { return finished; }

    public boolean connected() {
        return connect;
    }

    //Change Methods

    public void changeFinished(boolean to) {
        finished = to;
    }

    public void changeConected(boolean to) {
        connect = to;
    }

    public void changeEndselected(boolean b) {
        this.endSelected = b;
    }

    //Test Methods

    public boolean isFrom(Node node) {
        return this.form == node;
    }

    public boolean isStart(Node node) {
        return this.start == node;
    }

    public boolean isEnd(Node node) {
        return this.end == node;
    }

    public boolean isStartSelected() {
        return this.start != null;
    }

    public boolean isEndSelected() { return endSelected; }

    //other

    public void iteratorInit() {
        edgeIt =  path.getPath().iterator();
        nodeIt = path.getUpdatedNodes().iterator();
    }

    public void findShortestPath() {
        opt = currentGraph.findShortestPath(start, end, path);
    }
}
