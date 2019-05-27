package dijkstra;

public class Edge {
    private Node a;
    private Node b;
    private double distance;

    public Edge(Node a, Node b){
        this.a = a;
        this.b = b;
    }

    private void calculateDistance() {
        this.distance = Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }
}
