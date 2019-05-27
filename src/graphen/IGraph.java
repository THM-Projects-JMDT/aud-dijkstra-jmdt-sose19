package graphen;

import java.util.ArrayList;
import java.util.Set;

public interface IGraph {
    void addNode(Node n);
    ArrayList<Node> getNodes();
    ArrayList<Edge> getEdges();
    void link(Node a, Node b);
    Path findShortestPath(Node start, Node end);
    void fillAdjMatrix();
}
