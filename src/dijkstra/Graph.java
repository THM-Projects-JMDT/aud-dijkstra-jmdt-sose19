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
    public Edge link(Node a, Node b){
        Edge e = new Edge(a, b);
        edges.add(e);
        return e;
    }

    @Override
    public List<Node> findShortestPath(Node start, Node end, Path path) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        Node next;
        start.setDistance(0);
        q.offer(start);
        while(!q.isEmpty()) {
            next = q.poll();

            if(next.getPred() != null) {
                path.addEdge(new Edge(next, next.getPred()));
            }
            if(next.equals(end)) {
                List<Node> list = new ArrayList<>();
                list.add(end);
                return getAllPred(end, list);
            }
            Map<Node, Edge> neighbours = getNeighbours(next);
            for(Node n : neighbours.keySet()) {
                double newDistance = next.getDistance() + neighbours.get(n).getDistance();
                if(newDistance < n.getDistance()) {
                    n.setPred(next);
                    n.setDistance(newDistance);
                    q.offer(n);
                }
            }
        }
        //TODO: Path
        return new ArrayList<Node>();
    }

    private Map<Node, Edge> getNeighbours(Node n) {
        return edges.stream()
                .filter(e -> (e.contains(n) && !e.contains(n.getPred())))
                .collect(Collectors.toMap(e -> e.other(n), e -> e));
    }

    @Override
    public void fillAdjMatrix() {
        //TODO
    }
    private List<Node> getAllPred(Node n, List<Node> l)  {
        if(n.getPred() == null) {
            Collections.reverse(l);
            return l;
        }
        Node pred = n.getPred();
        l.add(pred);
        return getAllPred(pred, l);
    }
}