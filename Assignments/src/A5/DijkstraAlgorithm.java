public class DijkstraAlgorithm {
    public static int[] Dijkstra(AdjMatrixGraph graph, int source) {
        int size = graph.getSize();
        int[] shortestDistances = new int[size];
        boolean[] visited = new boolean[size];
        
        // Initialize shortest distances with the edge weights from the source vertex
        for (int v = 0; v < size; v++) {
            shortestDistances[v] = graph.getEdgeWeight(source, v);
            visited[v] = false;
        }
        
        // Set the distance of the source vertex to 0
        shortestDistances[source] = 0;
        
        for (int count = 0; count < size - 1; count++) {
            // Find the vertex with the minimum distance from the set of unvisited vertices
            int minDistanceVertex = -1;
            int minDistance = Integer.MAX_VALUE;
            for (int v = 0; v < size; v++) {
                if (!visited[v] && shortestDistances[v] < minDistance) {
                    minDistance = shortestDistances[v];
                    minDistanceVertex = v;
                }
            }
            
            // Mark the selected vertex as visited
            visited[minDistanceVertex] = true;
            
            // Update the distances of the neighboring vertices
            for (int v = 0; v < size; v++) {
                int edgeWeight = (int) graph.getEdgeWeight(minDistanceVertex, v);
                if (!visited[v] && edgeWeight > 0 && shortestDistances[minDistanceVertex] != Integer.MAX_VALUE &&
                        shortestDistances[minDistanceVertex] + edgeWeight < shortestDistances[v]) {
                    shortestDistances[v] = shortestDistances[minDistanceVertex] + edgeWeight;
                }
            }
        }
        
        return shortestDistances;
    }
}