package dijkstra;

import java.util.*;
import java.util.stream.Collectors;

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
        PriorityQueue<Node> q = new PriorityQueue<>();
        Node next;
        q.offer(start);
        while(true) {
            next = q.poll();
            Set<Node> neighbours = next.getNeighbours();
        }
        //TODO
        return null;
    }

    private Map<Node, Edge> getNeighbors(Node n) {
        return edges.stream()
                .filter(e -> e.contains(n))
                .collect(Collectors.toMap(e -> e.other(n), e -> e));
    }

    @Override
    public void fillAdjMatrix() {
        //TODO
    }
}