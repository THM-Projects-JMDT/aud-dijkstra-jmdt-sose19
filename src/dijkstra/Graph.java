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
    public Node findShortestPath(Node start, Node end, Path path) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        Node next;
        start.setDistance(0);
        q.offer(start);
        while(!q.isEmpty()) {
            next = q.poll();
            if(next.equals(end)) {
                return end;
            }
            Map<Node, Edge> neighbours = getNeighbours(next);
            for(Node n : neighbours.keySet()) {
                n.setDistance(next.getDistance() + neighbours.get(n).getDistance());
                n.setPred(next);
                q.offer(n);
            }
        }
        //TODO
        return null;
    }

    private Map<Node, Edge> getNeighbours(Node n) {
        return edges.stream()
                .filter(e -> e.contains(n))
                .collect(Collectors.toMap(e -> e.other(n), e -> e));
    }

    @Override
    public void fillAdjMatrix() {
        //TODO
    }
}