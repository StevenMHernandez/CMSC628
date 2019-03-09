import java.util.ArrayList;

/**
 * Part 2 Code
 */
public class Main2 {
    public static void main(String[] args) {
        // CONFIG
        int L_max = 1000;
        int NUM_NODES = 10;

        // Run experiment 10 times.
        for (int ex = 0; ex < 10; ex++) {
            ArrayList<Integer> nodes_with_message = new ArrayList<Integer>();
            nodes_with_message.add(0);
            int destination_i = 1;

            // INITIALIZE NUM_NODES Nodes
            Node[] nodes = new Node[NUM_NODES];
            for (int i = 0; i < NUM_NODES; i++) {
                nodes[i] = new Node();
            }

            int success = -1;
            int t = 0;

            while (success == -1) {
                t++;
                for (Node n : nodes) {
                    n.RandomDirection(t);
                }

                for (int node_i = 0; node_i < nodes_with_message.size(); node_i++) {
                    Node n = nodes[node_i];
                    for (int neighbor_i = 0; neighbor_i < nodes.length; neighbor_i++) {
                        Node neighbor = nodes[neighbor_i];
                        if (n != neighbor && !nodes_with_message.contains(neighbor_i)) { // ignore certain values
                            if (n.canCommunicateWith(neighbor)) {
                                if (neighbor_i == destination_i) {
                                    success = t;
                                } else if (nodes_with_message.size() < L_max) {
                                    nodes_with_message.add(neighbor_i); // It might be argued that this neighbor should be able to share with its own neighbors in this time instance, but we will not simulate this argument.
                                }
                            }
                        }
                    }
                }
            }

            System.out.println(success + " (s) required. Message shared with " + nodes_with_message.size() + " nodes");
        }

//        double expected = (Math.log(NUM_NODES - 1) / (NUM_NODES - 1)) * EM_mm
//        System.out.println("Expected: " + expected);

        System.out.println();
    }
}
