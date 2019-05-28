package graphen;

import java.util.ArrayList;
import java.util.Objects;

public class Node {
    private int x;
    private int y;
    // dürfte schlauer sein die jeweiligen kanten hier zu speichern
    private ArrayList<Edge> edges;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        edges = new ArrayList<>();
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Node n){
        edges.add(new Edge(this,n));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
