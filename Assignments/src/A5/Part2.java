import java.io.FileWriter;
import java.io.PrintWriter;

public class Part2 {
    public static void main(String[] args) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("Part2Output.txt"))) {
            int minDensity = 1;
            int maxDensity = 9;
            int size = 5000;

            for (int density = minDensity; density <= maxDensity; density++) {
                RandomAdjMatrixGraph graph = new RandomAdjMatrixGraph(size, density);

                long DstartTime = System.currentTimeMillis();
                for (int startVertex = 0; startVertex < size; startVertex++) {
                // Measure the time taken by Dijkstra's algorithm
                    int[] shortestDistances = DijkstraAlgorithm.Dijkstra(graph, startVertex);
                }
                long DendTime = System.currentTimeMillis();
                long Dduration = DendTime - DstartTime;

                long FstartTime = System.currentTimeMillis();
                int[][] distances = FloydAlgorithm.Floyd(graph);
                long FendTime = System.currentTimeMillis();
                long Fduration = FendTime - FstartTime;


                System.out.println("Graph size: " + size + " Graph Density: " + density + " Dijkstra: " + Dduration + " milliseconds" + " Floyd: " + Fduration + " milliseconds");
                pw.println("Graph size: " + size + " Graph Density: " + density + " Dijkstra: " + Dduration + " milliseconds" + " Floyd: " + Fduration + " milliseconds");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
