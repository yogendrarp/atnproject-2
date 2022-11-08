import java.util.Arrays;

public class Annealing {
    double COOLDOWN = 100;
    final double MAXCOOLDOWN = 0.001;
    Graph graph;

    public Annealing(Graph graph) {
        this.graph = graph;
    }

    public void heuristicComputation() {
        int[][] optimisedMatrix = graph.adjMatrix.clone();
        int[][] probableOptimizedMatrix = graph.adjMatrix.clone();
        while (COOLDOWN > MAXCOOLDOWN) {
            for(int i=0; i< graph.numberOfNodes; i++) {
                for(int j=0; j<i; j++) {
                    if (probableOptimizedMatrix[i][j]==1) {
                        if(Arrays.stream(probableOptimizedMatrix[i]).sum() > 3
                            && Arrays.stream(probableOptimizedMatrix[j]).sum()>3) {
                            probableOptimizedMatrix[i][j] = 0;
                            probableOptimizedMatrix[j][i] = 0;
                            Graph _tempGraph = graph.getShallowCopy(probableOptimizedMatrix);
                            _tempGraph.plotEdgesUsingAdjMatrix();
                            double _computedCost = _tempGraph.computeCost();
                            Graph _optimisedGraph = graph.getShallowCopy(optimisedMatrix);
                            _optimisedGraph.adjMatrix = optimisedMatrix;
                            double _optimisedCost = _optimisedGraph.computeCost();
                            //Since this heuristic, we consider equal as well
                            boolean isWorthTheChange = Boolean.FALSE;
                            double costReduction = _computedCost - _optimisedCost;
                            if(0 <= costReduction) {
                                isWorthTheChange = Boolean.TRUE;
                            } else {
                                double odds = Math.exp(costReduction/COOLDOWN*-1);
                                if(odds>=0.9) {
                                    isWorthTheChange = Boolean.TRUE;
                                }
                            }
                            if(isWorthTheChange) {
                                optimisedMatrix = probableOptimizedMatrix.clone();
                            } else {
                                probableOptimizedMatrix = optimisedMatrix.clone();
                            }
                        }
                    }
                }
            }
            //Reduce the temperature
            COOLDOWN/=10;
        }
        graph.adjMatrix = optimisedMatrix;
    }
}
