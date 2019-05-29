package dijkstra;

import java.util.Random;

public class Generator {
    private static Graph generateGraph(){
        Graph graph = new Graph();
        Random r = new Random();
        int randomfor = r.nextInt(20)+5;
        int randomfor2 = r.nextInt(19)+5;
        Node[] nodes = new Node[randomfor];
        for(int i = 0;i<randomfor;i++){
            nodes[i]=graph.addNode(r.nextInt(600),r.nextInt(600));

        }
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
                    i--;
                    continue;
                }
            }
            graph.link(x,y);

        }

        return graph;
    }

}
