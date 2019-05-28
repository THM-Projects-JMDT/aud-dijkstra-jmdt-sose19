package dijkstra;

import graphen.Edge;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<graphen.Edge> edges = new ArrayList<>();

    public List<graphen.Edge> getPath() {
        return edges;
    }

    public void addEdge(Edge newEdge) {
        edges.add(newEdge);
    }

    @Override
    public String toString() {
        return "Path{" +
                "edges=" + edges +
                '}';
    }
}