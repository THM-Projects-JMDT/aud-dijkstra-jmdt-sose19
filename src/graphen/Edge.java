package graphen;

public class Edge {
    private Node a;
    private Node b;
    private double distance;

    public Edge(Node a, Node b){
        this.a = a;
        this.b = b;
    }

    private void calculateDistance() {
        this.distance = Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }

    private Node otherNode(Node x){
        if(x.equals(a)&&x.equals(b))
            return null;
        return x.equals(a)?b:a;


    }
}
