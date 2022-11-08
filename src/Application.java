import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Application {
    final static int MAX_COORDINATE = 200;
    public static void main(String[] args) {
        System.out.println("Enter number of Nodes");
        Scanner scanner =  new Scanner(System.in);
        int numberOfNodes = scanner.nextInt();
        ArrayList<String> networkMap =  getPointsOnPlane(numberOfNodes);
        Display.printCoordinates(networkMap);
        Graph greedyGraph = new Graph(numberOfNodes, networkMap);
        greedyGraph.calculateDistanceMatrix();
        GreedyLocalSearch greedyLocalSearch = new GreedyLocalSearch(greedyGraph);
        greedyGraph.adjMatrix = greedyLocalSearch.computeGreedyAdjMatrix();
        greedyGraph.plotEdgesUsingAdjMatrix();
        Constraints constraints = new Constraints(numberOfNodes);
        constraints.balanceDiameter(greedyGraph.edges, greedyGraph.adjMatrix);
        double costByLocalGreedy = greedyGraph.computeCost();
        System.out.printf("Cost by local greedy heuristic method %f%n", costByLocalGreedy);

        // We start with an existing graph, which works as an optimization
        Graph annealingGraph = greedyGraph.getShallowCopy(greedyGraph.adjMatrix);
        Annealing annealing = new Annealing(annealingGraph);
        annealing.heuristicComputation();
        double costByAnnealing = annealingGraph.computeCost();
        System.out.printf("Cost by Annealing heuristic method %f%n", costByAnnealing);
    }

    static ArrayList<String> getPointsOnPlane(int numberOfNodes) {
        Random random = new Random();
        ArrayList<String> networkMap =  new ArrayList<>();
        HashSet<String> existingPoints = new HashSet<>();
        while(existingPoints.size() < numberOfNodes) {
            int xCoordinate = random.nextInt(MAX_COORDINATE);
            int yCoordinate = random.nextInt(MAX_COORDINATE);
            String coordinates = String.format("%d,%d",xCoordinate, yCoordinate);
            if(!existingPoints.contains(coordinates)){
                existingPoints.add(coordinates);
                networkMap.add(coordinates);
            }
        }
        return networkMap;
    }
}
