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

    public void setGraph(Graph graph) {
        currentGraph = graph;
    }

    public Path getPath() {
        return path;
    }

    public boolean finished() { return finished; }

    public boolean connected() {
        return connect;
    }

    public void changeConected(boolean to) {
        connect = to;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    public boolean isFrom(Node node) {
        return this.form == node;
    }

    public void setFrom(Node from) {
        this.form = from;
    }

    public Node getFrom() {
        return this.form;
    }

    public Node getTo() {
        return this.to;
    }

    public boolean isStart(Node node) {
        return this.start == node;
    }

    public boolean isEnd(Node node) {
        return this.end == node;
    }

    public Node getEnd() {
        return this.end;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public void setNodeSelected(boolean b) {
        nodeSelected = b;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public void setOpt(List<Edge> opt) {
        this.opt = opt;
    }

    public Graph getGraph() {
        return this.currentGraph;
    }

    public void iteratorInit() {
        edgeIt =  path.getPath().iterator();
        nodeIt = path.getUpdatedNodes().iterator();
    }

    public void changeFinished(boolean to) {
        finished = to;
    }

    public boolean isStartSelected() {
        return this.start != null;
    }

    public boolean isEndSelected() { return endSelected; }

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

    public void changeEndselected(boolean b) {
        this.endSelected = b;
    }

    public void findShortestPath() {
        opt = currentGraph.findShortestPath(start, end, path);
    }
}
