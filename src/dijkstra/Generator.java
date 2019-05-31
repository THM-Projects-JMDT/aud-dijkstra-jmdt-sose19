package dijkstra;

import java.util.Random;

public class Generator {

    private Random r = new Random();
    public Node node1;
    public Node node2;

    public Graph generateGraph(){
        Graph graph = new Graph();
        int randomfor = r.nextInt(12)+5;
        int randomfor2 = r.nextInt(randomfor*3)+10;
        Node[] nodes = new Node[randomfor];
        for(int i = 0;i<randomfor;i++){
            nodes[i]=graph.addNode(r.nextInt(1080),r.nextInt(720));
        }
        node1=nodes[randomfor-2];
        node2=nodes[randomfor-3];
        for(int i = 0;i<randomfor2;i++) {
            Node x= nodes[r.nextInt(randomfor-1)];
            Node y= nodes[r.nextInt(randomfor-1)];
            if(x.equals(y)) {
                i--;
                continue;
            }
            Edge e = new Edge(x, y);
            for(Edge g: graph.getEdges()){
                if(e.equals(g)) {
                    continue;
                }
            }
            graph.link(x,y);

        }

        return graph;
    }


}
