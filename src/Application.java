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
        Constraints.balanceDiameter(greedyGraph.edges, greedyGraph.adjMatrix, numberOfNodes);
        double costByLocalGreedy = greedyGraph.computeCost();
        //System.out.println("Adjacency Matrix by using heuristic method is");
        //Display.printMatrix(greedyGraph.adjMatrix);
        System.out.printf("Cost by local greedy heuristic method %f%n", costByLocalGreedy);

        Graph basicGraph = new Graph(numberOfNodes, networkMap);
        basicGraph.distMatrix =  greedyGraph.distMatrix;
        BasicAdjacencyMatrix basicAdjacencyMatrix = new BasicAdjacencyMatrix(basicGraph);
        basicAdjacencyMatrix.computeBasicAdjMatrix();
        basicGraph.plotEdgesUsingAdjMatrix();
        Constraints.balanceDiameter(basicGraph.edges, basicGraph.adjMatrix, basicGraph.numberOfNodes);


        Graph basicAnnealingGraph = basicGraph.getShallowCopy(basicGraph.adjMatrix);
        Annealing basicAnnealing = new Annealing(basicAnnealingGraph);
        basicAnnealing.heuristicComputation();
        double costOfBasicAnnealing = basicAnnealingGraph.computeCost();
        System.out.printf("Cost by Annealing heuristic method %f%n", costOfBasicAnnealing);

        // We start with an existing graph, which works as an optimization
        Graph annealingGraph = greedyGraph.getShallowCopy(greedyGraph.adjMatrix);
        Annealing annealing = new Annealing(annealingGraph);
        annealing.heuristicComputation();
        double costByAnnealing = annealingGraph.computeCost();
        //System.out.println("Adjacency Matrix by using heuristic method is");
       // Display.printMatrix(annealingGraph.adjMatrix);
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
