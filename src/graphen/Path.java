package graphen;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Edge> edges = new ArrayList<>();

    public List<Edge> getPath() {
        return edges;
    }

    public void addEdge(Edge newEdge) {
        edges.add(newEdge);
    }
}