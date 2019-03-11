import java.util.ArrayList;

/**
 * Part 1 Code
 */
public class Main1 {
    public static void main(String[] args) {
        ArrayList<Integer> simulation = new ArrayList<Integer>();
        ArrayList<Double> expected = new ArrayList<Double>();

        // Run experiment 10 times.
        for (int ex = 0; ex < 100; ex++) {
            // INITIALIZE NUM_NODES Nodes
            Node source = new Node();
            Node destination = new Node();

            // Collect Simulated Expected Hitting Time
            int t = 0;
            while (true) {
                source.RandomDirection(t);
                if (Network.SHOULD_MOVE_ALL) {
                    destination.RandomDirection(t);
                }

                if (destination.canCommunicateWith(source)) {
                    break;
                }

                t++;
            }

            double expectedHittingTime = TheoreticalCalculations.expectedHittingTime();
            double expectedMeetingTime = TheoreticalCalculations.expectedMeetingTime();

            String type;
            String value;
            if (Network.SHOULD_MOVE_ALL) {
                type = "Meeting Time";
                value = Double.toString(expectedMeetingTime);
                expected.add(expectedMeetingTime);
            } else {
                type = "Hitting Time";
                value = Double.toString(expectedHittingTime);
                expected.add(expectedHittingTime);
            }
            System.out.println("Average " + type + ":  " + t);
            System.out.println("Expected " + type + ": " + value);
            System.out.println();

            simulation.add(t);
        }

        double avg_sim = 0;
        double avg_exp = 0;
        for (int i = 0; i < simulation.size(); i++) {
            avg_sim += simulation.get(i);
            avg_exp += expected.get(i);
        }

        System.out.println("=====================");
        System.out.println("N                   = " + Network.MAX_X);
        System.out.println("T_stop              = " + Network.MAX_TIME_SPAN);
        System.out.println("---------------------");
        System.out.println("Average Simulation  = " + avg_sim / simulation.size());
        System.out.println("Average Theoretical = " + avg_exp / expected.size());
        System.out.println("=====================");
    }
}
