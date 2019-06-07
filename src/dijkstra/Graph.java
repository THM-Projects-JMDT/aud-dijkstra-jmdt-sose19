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

    public Node addNode(int x, int y) {
        Node n = new Node(x,y);
        nodes.add(n);
        return n;
    }

    @Override
    public Edge link(Node a, Node b){
        Edge e = new Edge(a, b);
        edges.add(e);
        return e;
    }

    @Override
    public List<Edge> findShortestPath(Node start, Node end, Path path) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        Set<Node> startSet = new HashSet<>();
        Node next;
        start.setDistance(0);
        startSet.add(start.clone());
        path.addNodeSet(startSet);
        q.offer(start);
        path.addQueue(new PriorityQueue<>(q));
        while(!q.isEmpty()) {
            next = q.poll();

            if(next.getPred() != null) {
                path.addEdge(new Edge(next.getPred().clone(), next.clone()));
                path.addQueue(new PriorityQueue<>(q));
            }
            if(next.equals(end)) {
                List<Edge> list = new ArrayList<>();
                return getAllPred(end, list);
            }
            Map<Node, Edge> neighbours = getNeighbours(next);
            Set<Node> neighboursSet = new HashSet<>();
            for(Node n : neighbours.keySet()) {
                double newDistance = next.getDistance() + neighbours.get(n).getDistance();
                if(newDistance < n.getDistance()) {
                    double prevDist = n.getDistance();
                    n.setPred(next);
                    n.setDistance(newDistance);
                    if(prevDist == Double.MAX_VALUE) {
                        q.offer(n);
                    }
                }
                neighboursSet.add(n.clone());
            }
            q.offer(q.poll());
            path.addQueue(new PriorityQueue<>(q));
            path.addNodeSet(neighboursSet);
        }
        reset();
        return new ArrayList<Edge>();
    }

    private Map<Node, Edge> getNeighbours(Node n) {
        return edges.stream()
                .filter(e -> (e.contains(n) && !e.contains(n.getPred())))
                .collect(Collectors.toMap(e -> e.other(n), e -> e));
    }

    private void reset() {
        nodes.forEach(n -> {
            n.setDistance(Double.MAX_VALUE);
            n.setPred(null);
        });
    }

    private List<Edge> getAllPred(Node n, List<Edge> l)  {
        if(n.getPred() == null) {
            Collections.reverse(l);
            reset();
            return l;
        }
       Edge e = new Edge(n.getPred(), n);
       l.add(e);
       return getAllPred(n.getPred(), l);
    }
}