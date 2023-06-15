import java.io.FileWriter;
import java.io.PrintWriter;

public class Part2 {
    public static void main(String[] args) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("Part2Output.txt"))) {
            // Variables for density and size
            int minDensity = 1;
            int maxDensity = 9;
            int size = 5000;

            for (int density = minDensity; density <= maxDensity; density++) {
                // Creating the randomized graphs with the different densities
                RandomAdjMatrixGraph graph = new RandomAdjMatrixGraph(size, density);

                // Using Dijkstra's algorithm on every vertex of the graph multiple times and recordingt the time needed
                long DstartTime = System.currentTimeMillis();
                for (int startVertex = 0; startVertex < size; startVertex++) {
                // Measure the time taken by Dijkstra's algorithm
                    int[] shortestDistances = DijkstraAlgorithm.Dijkstra(graph, startVertex);
                }
                long DendTime = System.currentTimeMillis();
                long Dduration = DendTime - DstartTime;

                // Using Floyd's algorithm on the graphs and recording the time needed
                long FstartTime = System.currentTimeMillis();
                int[][] distances = FloydAlgorithm.Floyd(graph);
                long FendTime = System.currentTimeMillis();
                long Fduration = FendTime - FstartTime;

                // Printing out the densities and the times of each algorithm to the terminal
                System.out.println("Graph size: " + size + " Graph Density: " + density + " Dijkstra: " + Dduration + " milliseconds" + " Floyd: " + Fduration + " milliseconds");
                // Printing out the densities and the times needed to the text file Part2Output.txt
                pw.println("Graph size: " + size + " Graph Density: " + density + " Dijkstra: " + Dduration + " milliseconds" + " Floyd: " + Fduration + " milliseconds");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
