package dijkstra;

import java.util.List;
import java.util.Set;

public interface IGraph {
    void addNode(Node n);
    Set<Node> getNodes();
    Set<Edge> getEdges();
    void link(Node a, Node b);
    Path findShortestPath(Node start, Node end);
    void fillAdjMatrix();
}
