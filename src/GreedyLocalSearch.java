import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GreedyLocalSearch {
    Graph graph;
    final int DEGREE_LIMIT = 3;
    public GreedyLocalSearch(Graph graph) {
        this.graph = graph;
    }

    public int[][] computeGreedyAdjMatrix() {
        int [][] adjMatrix = new int[graph.numberOfNodes][graph.numberOfNodes];
        for(int i=0; i< graph.numberOfNodes; i++) {
            int[] sortedIndex = getSortedIndexes(graph.distMatrix[i]);
            for (int pos: sortedIndex) {
                 if(i!=pos && adjMatrix[i][pos]==0 && Arrays.stream(adjMatrix[i]).sum() <= DEGREE_LIMIT) {
                     adjMatrix[i][pos] = 1;
                     adjMatrix[pos][i] = 1;
                 }
            }
        }
        return adjMatrix;
    }

    private int[] getSortedIndexes(double[] distMatrix) {
        List<Double> distList=  Arrays.stream(distMatrix).boxed().collect(Collectors.toList());
        AtomicInteger i= new AtomicInteger();
        int [] sortedIndexPos = new int[distMatrix.length];
        List<Double> sortedList= distList.stream().sorted().toList();
        sortedList.forEach(doubles -> {
            sortedIndexPos[i.getAndIncrement()] = distList.indexOf(doubles);
        });
        return sortedIndexPos;
    }
}
