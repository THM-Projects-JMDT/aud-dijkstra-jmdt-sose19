package graphen;

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

    public boolean contains(Node x){
        if (x.equals(a) || x.equals(b))
            return true;
        return false;
    }

    public Node otherNode(Node x){
        if(x.equals(a)&&x.equals(b))
            return null;
        return x.equals(a)?b:a;
    }

    public Node getlinkedNode(){
        return b;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "a=" + a +
                ", b=" + b +
                ", distance=" + distance +
                '}';
    }
}
