import java.util.ArrayList;

/**
 * Part 1 Code
 */
public class Main1 {
    public static void main(String[] args) {
        // Run experiment 10 times.
        for (int ex = 0; ex < 10; ex++) {
            // CONFIG:
            boolean SHOULD_MOVE_ALL = false;
            int TIMESPAN = 10000;

            // INITIALIZE NUM_NODES Nodes
            int NUM_NODES = 1000;
            Node[] nodes = new Node[NUM_NODES];
            for (int i = 0; i < NUM_NODES; i++) {
                nodes[i] = new Node();
            }

            // Collect Expected Hitting Time
            ArrayList<Integer> timeConnected = new ArrayList<Integer>();
            boolean connection_prev = false;
            boolean connection_curr = false;

            for (int t = 0; t < TIMESPAN; t++) {
                if (SHOULD_MOVE_ALL) {
                    for (Node n : nodes) {
                        n.RandomDirection(t);
                    }
                } else {
                    nodes[0].RandomDirection(t);
                }

                connection_curr = false;
                for (Node n : nodes) {
                    if (n != nodes[0]) {
                        if (n.canCommunicateWith(nodes[0])) {
                            connection_curr = true;
                        }
                    }
                }

                if (connection_curr) {
                    if (connection_prev) {
                        int prevI = timeConnected.size() - 1;
                        int prev = timeConnected.get(prevI);
                        timeConnected.set(prevI, prev + 1);
                    } else {
                        timeConnected.add(1);
                    }
                }

                connection_prev = connection_curr;
            }

            // Get actual values
            double avg = 0;
            if (timeConnected.size() > 0) {
                for (int ts : timeConnected) {
                    avg += ts;
                }
                avg = avg / timeConnected.size();
            }

            // Get theoretical values
            int N = Network.MAX_X * Network.MAX_X;
            int K = Network.R;
            int L = 10;
//            int L = (int) (Math.random() * (Network.MAX_TIME_SPAN - 1)) + 1; // Should be a prediction [min,max]
            double v = (int) (Network.MAX_V + Network.MIN_V) / 2; // Should be a prediction [min,max]
//            double v = (int) ((Math.random() * (Network.MAX_V - Network.MIN_V)) + Network.MIN_V); // Should be a prediction [min,max]
            int T_stop = Network.PAUSE_TIME;

            double expectedMeetingTime = (N / (2 * K * L)) * ((L / v) + T_stop);
            double expectedHittingTime = 0.0;

            if (SHOULD_MOVE_ALL) {
                // Expected Hitting Time

            }

            String type = SHOULD_MOVE_ALL ? "Meeting Time" : "Hitting Time";
            System.out.println("Average " + type + ":  " + avg);
            System.out.println("Expected " + type + ": " + (SHOULD_MOVE_ALL ? expectedHittingTime : expectedMeetingTime));
            System.out.println();
        }
    }
}
