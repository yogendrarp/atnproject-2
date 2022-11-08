import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Constraints {
    final static int DIAMETER_HARD_LIMIT = 4;

    static void balanceDiameter(ArrayList<ArrayList<Integer>> edges, int[][] adjMatrix, int numberOfNodes) {
        for(int i=0; i<numberOfNodes; i++) {
            for(int j=0; j<numberOfNodes; j++) {
                int distance = computeDistanceUsingBFS(i, j, edges, numberOfNodes);
                if((i!=j && distance == 0) || (distance>DIAMETER_HARD_LIMIT)) {
                    adjMatrix[i][j] = 1;
                    adjMatrix[j][i] = 1;
                    edges.get(i).add(j);
                    edges.get(j).add(i);
                }
            }
        }
    }

    static int computeDistanceUsingBFS(int startNode, int endNode, ArrayList<ArrayList<Integer>> edges, int numberOfNodes) {
        Queue<Integer> nodeQueue = new LinkedList<>();
        int [] distance = new int[numberOfNodes];
        boolean [] visited = new boolean[numberOfNodes];
        nodeQueue.add(startNode);
        distance[startNode] = 0;
        visited[startNode] = Boolean.TRUE;

        while (!nodeQueue.isEmpty()) {
            int node = nodeQueue.poll();
            for(int i=0; i<edges.get(node).size(); i++) {
                int pos = edges.get(node).get(i);
                if(!visited[pos]) {
                    distance[pos] = distance[node] + 1 ;
                    nodeQueue.add(pos);
                    visited[pos] = Boolean.TRUE;
                }
            }
        }
        return distance[endNode];
    }
}
