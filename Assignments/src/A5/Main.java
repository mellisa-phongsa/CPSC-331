import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("Output.txt"))) {
            int minSize = 5;
            int maxSize = 10;
            //pw.print("Part I - Generate random graphs of sizes between 500-1000 with density 5");
            for (int size = minSize; size <= maxSize; size += 1) {
                int density = 5;
                RandomAdjMatrixGraph matrix = new RandomAdjMatrixGraph(size, density);

                //Dijkstra's APSP algorithm time
                //long dStartTime = System.nanoTime();
                //Dijkstra(matrix);
                //long dEndTime = System.nanoTime();
                //long dijkstraTime = dEndTime - dStartTime;

                //Floyd's ASAP algorithm time
                //long fStartTime = System.nanoTime();
                //Floyd(matrix);
                //long fEndTime = System.nanoTime();
                ///long floydTime = fEndTime - fStartTime;

                //pw.print("Dijkstra Time: " + dijkstraTime);
                //pw.print("Floyd Time: " + floydTime);

                matrix.printGraph();
            }

            int minDensity = 1;
            int maxDensity = 9;
            //pw.print("Part II - Generate random graphs with densities between 1-9 with size 5000");
            for (int density = minDensity; density <= maxDensity; density++) {
                int size = 5;
                RandomAdjMatrixGraph matrix = new RandomAdjMatrixGraph(size, density);

                //Dijkstra's APSP algorithm time
                //long dStartTime = System.nanoTime();
                //Dijkstra(matrix);
                //long dEndTime = System.nanoTime();
                //long dijkstraTime = dEndTime - dStartTime;

                //Floyd's ASAP algorithm time
                //long fStartTime = System.nanoTime();
                //Floyd(matrix);
                //long fEndTime = System.nanoTime();
                ///long floydTime = fEndTime - fStartTime;

                //pw.print("Dijkstra Time: " + dijkstraTime);
                //pw.print("Floyd Time: " + floydTime);

                matrix.printGraph();
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
            
    }
}
