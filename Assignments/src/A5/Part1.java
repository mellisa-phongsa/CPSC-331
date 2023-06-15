import java.io.FileWriter;
import java.io.PrintWriter;

public class Part1 {
    public static void main(String[] args) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("Part1Output.txt"))) {
            // Variables for size and density
            int minSize = 500;
            int maxSize = 1000;
            int density = 5;

            for (int size = minSize; size <= maxSize; size += 1) {
                // Creating the randomized graph
                RandomAdjMatrixGraph graph = new RandomAdjMatrixGraph(size, density);

                // Using Dijkstra's algorithm on every vertex of the graph multiple times and recording the time needed
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

                // Printing out the size and the times of the algorithms on the terminal
                System.out.println("Graph size: " + size +  " Dijkstra: " + Dduration + " milliseconds" + " Floyd: " + Fduration + " milliseconds");
                // Printing the size and times to the output file Part1Output.txt
                pw.println("Graph size: " + size +  " Dijkstra: " + Dduration + " milliseconds" + " Floyd: " + Fduration + " milliseconds");
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}