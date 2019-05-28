package dijkstra;

public class Node implements Comparable<Node> {
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

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public void setPred(Node pred) {
        this.pred = pred;
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

    public int compareTo(Node other) {
        double diff = this.distance - other.distance;
        if(Math.round(diff) > 0) return 1;
        if(Math.round(diff) < 0) return -1;
        return 0;
    }

    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }
        if(!(other instanceof Node)) {
            return false;
        }
        Node otherNode = (Node) other;
        if(this.x == otherNode.x && this.y == otherNode.y) {
            return true;
        }
        return false;
    }
    public String toString() {
        return "[" + this.num + "]";
    }
}
