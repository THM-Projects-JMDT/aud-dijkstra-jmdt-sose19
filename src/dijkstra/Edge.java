package dijkstra;

import java.util.Objects;

public class Edge {
    private Node a;
    private Node b;
    private double distance;

    public double getDistance() {
        return distance;
    }

    public Edge(Node a, Node b){
        this.a = a;
        this.b = b;
        calculateDistance();
    }

    private void calculateDistance() {
        this.distance = Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }

    public Node getA() {
        return a;
    }

    public Node getB() {
        return b;
    }

    public boolean contains(Node n) {
        if(a.equals(n)) return true;
        if(b.equals(n)) return true;
        return false;
    }

    public Node other(Node n) {
        if(a.equals(n)) return b;
        if(b.equals(n)) return a;
        return null;
    }
    public String toString() {
        return "{" + this.a + "," + this.b + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        if((this.a.equals(edge.a) && this.b.equals(edge.b)) || (this.a.equals(edge.b) && this.b.equals(edge.a)))
            return true;
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(a, b) + Objects.hash(b, a);
    }
}
