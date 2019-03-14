import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * Part 2 Code
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        Files.write(Paths.get("matlab/data/part2.0.txt"), "".getBytes());

        for (int L = 10; L < Network.NUM_NODES; L += Network.NUM_NODES / 8) {
            Network.L_MAX = L;

            ArrayList<Integer> s = new ArrayList<Integer>();

            // Run experiment 10 times.
            for (int ex = 0; ex < 100; ex++) {
                ArrayList<Integer> nodes_with_message = new ArrayList<Integer>();
                nodes_with_message.add(0);
                int destination_i = 1;

                // INITIALIZE NUM_NODES Nodes
                Node[] nodes = new Node[Network.NUM_NODES];
                for (int i = 0; i < nodes.length; i++) {
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
                                    } else if (nodes_with_message.size() < Network.L_MAX) {
                                        nodes_with_message.add(neighbor_i); // It might be argued that this neighbor should be able to share with its own neighbors in this time instance, but we will not simulate this argument.
                                    }
                                }
                            }
                        }
                    }
                }

                s.add(success);
            }

            double avg = 0;
            for (Integer x : s) {
                avg += x;
            }

            double upper = TheoreticalCalculations.expectedMessageDeliveryTimeUpperBound();
            double average = avg / s.size();
            double lower = TheoreticalCalculations.expectedMessageDeliveryTimeLowerBound();

            System.out.println("=========");
            System.out.println("Upper: " + upper);
            System.out.println("Average: " + average);
            System.out.println("Lower: " + lower);
            System.out.println("=========");


            // `L,Upper,Average,Lower`
            String st = String.format("%d,%f,%f,%f\n", Network.L_MAX, upper, average, lower);
            Files.write(Paths.get("matlab/data/part2.0.txt"), st.getBytes(), StandardOpenOption.APPEND);
        }
    }
}
