package dijkstra;

import java.util.Random;

public class Generator {
    private static int maxNodeNum;
    private Random r = new Random();
    private Node start;
    private Node end;

    public Node getStart() { return start; }

    public Node getEnd() { return end; }

    public static void setMaxNodeNum(int maxNum) { Generator.maxNodeNum = maxNum - 4; }

    public Graph generateGraph() {
        Graph graph = new Graph();
        int randomfor = r.nextInt(maxNodeNum) + 5;
        int randomfor2 = r.nextInt(randomfor * 3) + 5;
        Node[] nodes = new Node[randomfor];
        for (int i = 0; i < randomfor; i++) {
            nodes[i] = graph.addNode(r.nextInt(1080), r.nextInt(720));
        }
        start = nodes[randomfor - 2];
        end = nodes[randomfor - 3];
        for (int i = 0; i < randomfor2; i++) {
            Node x = nodes[r.nextInt(randomfor - 1)];
            Node y = nodes[r.nextInt(randomfor - 1)];
            if (x.equals(y)) {
                i--;
                continue;
            }
            Edge e = new Edge(x, y);
            for (Edge g : graph.getEdges()) {
                if (e.equals(g)) {
                    continue;
                }
            }
            graph.link(x, y);

        }

        return graph;
    }


}
