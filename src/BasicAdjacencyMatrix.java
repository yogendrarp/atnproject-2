import java.util.Arrays;

public class BasicAdjacencyMatrix {
    Graph graph;
    final int DEGREE_LIMIT = 3;
    public BasicAdjacencyMatrix(Graph graph) {
        this.graph = graph;
    }

    public void computeBasicAdjMatrix() {
        int [][] adjMatrix = new int[graph.numberOfNodes][graph.numberOfNodes];
        for(int i=0; i< graph.numberOfNodes; i++) {
            for (int j=0; j<graph.numberOfNodes; j++) {
                if(i!=j && adjMatrix[i][j]==0 && Arrays.stream(adjMatrix[i]).sum() <= DEGREE_LIMIT) {
                    adjMatrix[i][j] = 1;
                    adjMatrix[j][i] = 1;
                }
            }
        }
        graph.adjMatrix = adjMatrix;
    }
}
