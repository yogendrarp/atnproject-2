import java.util.Arrays;

public class Annealing {
    double COOLDOWN = 200;
    final double MAXCOOLDOWN = 0.001;
    Graph graph;

    public Annealing(Graph graph) {
        this.graph = graph;
    }

    public void heuristicComputation() {
        int[][] originalMatrix = graph.adjMatrix.clone();
        int[][] optimisedMatrix = graph.adjMatrix.clone();
        int[][] probableOptimizedMatrix = graph.adjMatrix.clone();
        while (COOLDOWN > MAXCOOLDOWN) {
            for(int i=0; i< graph.numberOfNodes; i++) {
                for(int j=0; j<i; j++) {
                    if (probableOptimizedMatrix[i][j]==1) {
                        if(Arrays.stream(probableOptimizedMatrix[i]).sum() > 3
                            && Arrays.stream(probableOptimizedMatrix[j]).sum()>3) {

                        }
                    }
                }
            }
        }
    }
}
