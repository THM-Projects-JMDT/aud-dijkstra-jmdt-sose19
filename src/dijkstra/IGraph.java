package dijkstra;

import java.util.List;
import java.util.Set;

public interface IGraph {
    void addNode(Node n);
    Set<Node> getNodes();
    Set<Edge> getEdges();
    Edge link(Node a, Node b);
    List<Edge> findShortestPath(Node start, Node end, Path path);
}
