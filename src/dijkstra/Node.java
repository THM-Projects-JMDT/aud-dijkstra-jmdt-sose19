package dijkstra;

public class Node {
    private int x;
    private int y;
    private static int counter = 0;
    private int num;
    private double distance;
    private Node pred;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public double getDistance() {
        return distance;
    }

    public Node getPred() {
        return pred;
    }

    public int getNum() {
        return this.num;
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.num = counter++;
        this.distance = Double.MAX_VALUE;
        this.pred = null;
    }
}
