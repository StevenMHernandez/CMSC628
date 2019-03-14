import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Part 1 Code
 */
public class Main1 {
    public static void main(String[] args) throws IOException {
        Files.write(Paths.get("matlab/data/part1.0.txt"), "".getBytes());
        Files.write(Paths.get("matlab/data/part1.1.txt"), "".getBytes());


        for (int shouldMove = 0; shouldMove <= 1; shouldMove++) {
            for (int max_x = 500; max_x <= 1000; max_x += 50) {
                for (int pause_time = 25; pause_time <= 100; pause_time += 25) {
                    Network.SHOULD_MOVE_ALL = shouldMove == 1;
                    Network.MAX_X = max_x;
                    Network.PAUSE_TIME = pause_time;

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

                        if (Network.SHOULD_MOVE_ALL) {
                            expected.add(expectedMeetingTime);
                        } else {
                            expected.add(expectedHittingTime);
                        }

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
                    System.out.println("T_stop              = " + Network.PAUSE_TIME);
                    System.out.println("---------------------");
                    System.out.println("Average Simulation  = " + avg_sim / simulation.size());
                    System.out.println("Average Theoretical = " + avg_exp / expected.size());
                    System.out.println("=====================");

                    String s = String.format("%d,%d,%f,%f\n", Network.MAX_X, Network.PAUSE_TIME, avg_sim / simulation.size(), avg_exp / expected.size());
                    Files.write(Paths.get("matlab/data/part1." + shouldMove + ".txt"), s.getBytes(), StandardOpenOption.APPEND);
                }
            }
        }
    }
}
