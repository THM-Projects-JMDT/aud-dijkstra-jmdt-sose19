package dijkstra;

import java.util.*;

public class Path {
    private List<Edge> edges = new ArrayList<>();
    private List<Set<Node>> updatedNodes = new ArrayList<>();
    private List<Queue<Node>> queues = new ArrayList<>();

    public List<Edge> getPath() {
        return edges;
    }

    public List<Set<Node>> getUpdatedNodes() {
        return updatedNodes;
    }

    public List<Queue<Node>> getQueues() {
        return this.queues;
    }

    public void addNodeSet(Set<Node> nodeSet) {
        updatedNodes.add(nodeSet);
    }

    public void addEdge(Edge newEdge) {
        edges.add(newEdge);
    }

    public void addQueue(Queue<Node> queue) {
        queues.add(queue);
    }

    @Override
    public String toString() {
        return "Path{" +
                "edges=" + edges +
                '}' + updatedNodes;
    }
}