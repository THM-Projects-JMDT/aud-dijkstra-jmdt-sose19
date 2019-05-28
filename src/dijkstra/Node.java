package dijkstra;

public class Node {
    private int x;
    private int y;
    private static int counter = 0;
    private int num;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getNum() {
        return this.num;
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.num = counter++;
    }
}
