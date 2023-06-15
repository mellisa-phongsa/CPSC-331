public class FloydAlgorithm {
    public static int[][] Floyd(AdjMatrixGraph graph) {
        int size = graph.getSize();
        int[][] distances = new int[size][size];
        
        // Initialize distances matrix with the edge weights from the graph
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                distances[i][j] = graph.getEdgeWeight(i, j);
            }
        }
        
        // Update distances through intermediate vertices
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE &&
                            distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }
        
        return distances;
    }
}
