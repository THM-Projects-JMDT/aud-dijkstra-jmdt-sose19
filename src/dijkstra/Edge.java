package dijkstra;

public class Edge {
    private Node a;
    private Node b;
    private double distance;

    public Edge(Node a, Node b){
        this.a = a;
        this.b = b;
        calculateDistance();
    }

    private void calculateDistance() {
        this.distance = Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }
}
