package dijkstra;

import java.util.*;

public class Graph implements IGraph {
    private Set<Node> nodes;
    private Set<Edge> edges;

    public Graph() {
        nodes = new HashSet<>();
        edges = new HashSet<>();
    }

    @Override
    public Set<Node> getNodes() {
        return nodes;
    }

    @Override
    public Set<Edge> getEdges() {
        return edges;
    }

    @Override
    public void addNode(Node n) {
        nodes.add(n);
    }

    @Override
    public void link(Node a, Node b) {
        edges.add(new Edge(a, b));
    }

    @Override
    public Path findShortestPath(Node start, Node end) {
        Node next;
        PriorityQueue<Node> q = new PriorityQueue<>();

        //TODO
        return null;
    }

    @Override
    public void fillAdjMatrix() {
        //TODO
    }
}
