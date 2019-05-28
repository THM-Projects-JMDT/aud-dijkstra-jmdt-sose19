package graphen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Graph implements IGraph {
    private ArrayList<Node> nodes;

    public Graph() {
        //wieso hatten wir hash? Arraylist ist doch in der regel einfacher?...
        nodes = new ArrayList<>();
    }

    @Override
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    @Override
    public void addNode(Node n) {
        nodes.add(n);
    }

    public Node addNode(int x, int y) {
        Node ausgabe= new Node(x,y);
        nodes.add( ausgabe);
        return ausgabe;
    }

    @Override
    public void link(Node a, Node b) {
      a.addEdge(b);
      b.addEdge(a);
    }

    @Override
    public Path findShortestPath(Node start, Node end) {
        Path p = new Path();
        ArrayList<Node> durchlaufen = new ArrayList<>();
        durchlaufen.add(start);
        findShortestPath(durchlaufen,start,end,p);
        return p;
    }

    // Hatte schon mal angefangen aber nicht fertig geworden :D

    private Edge findShortestPath(ArrayList<Node> durchlaufen, Node akt, Node end, Path p) {
        for(Edge e:akt.getEdges()){
            if(e.getlinkedNode()!=durchlaufen){
                    if(findShortestPath(start,x,end,p)!=null)
                        p.addEdge(e);
                    if(x.equals(end)){
                        return e;
                    }

                }

        }
        return null;
    }


    @Override
    public void fillAdjMatrix() {
        //TODO
    }
}
