package dijkstra;

import java.util.Objects;

public class Node implements Comparable<Node>, Cloneable {
    private int x;
    private int y;
    private static int counter = 65;
    private char label;
    private double distance;
    private Node pred;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.label = (char)counter++;
        this.distance = Double.MAX_VALUE;
        this.pred = null;
    }

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
    public static void resetCounter(){
        counter=65;
    }

    public char getLabel() {
        return this.label;
    }

    public int compareTo(Node other) {
        double diff = this.distance - other.distance;
        if(diff > 0.001) return 1;
        if(diff < -0.001) return -1;
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

    @Override
    public int hashCode() {
        return Objects.hash( 2 * x, 3 * y);
    }

    @Override
    public String toString() {
        return this.label + ":" + (this.distance == Double.MAX_VALUE ? "∞" : String.valueOf(Math.round(this.distance)));
    }

    @Override
    public Node clone() {
        try {
            return (Node)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
