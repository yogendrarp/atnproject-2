import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Application {
    final static int MAX_COORDINATE = 200;
    final static DecimalFormat df = new DecimalFormat("#.##");
    public static void main(String[] args) {
        System.out.println("Enter number of Nodes");
        Scanner scanner =  new Scanner(System.in);
        int numberOfNodes = scanner.nextInt();
        ArrayList<String> networkMap =  getPointsOnPlane(numberOfNodes);
        Display.printCoordinates(networkMap);
        Graph greedyGraph = new Graph(numberOfNodes, networkMap);
        Instant start = Instant.now();
        greedyGraph.calculateDistanceMatrix();
        GreedyLocalSearch greedyLocalSearch = new GreedyLocalSearch(greedyGraph);
        greedyGraph.adjMatrix = greedyLocalSearch.computeGreedyAdjMatrix();
        greedyGraph.plotEdgesUsingAdjMatrix();
        Constraints.balanceDiameter(greedyGraph.edges, greedyGraph.adjMatrix, numberOfNodes);
        double costByLocalGreedy = greedyGraph.computeCost();
        Instant end = Instant.now();
        System.out.println("____________________________________________________________________________________________");
        System.out.printf("Time to compute %d%n", ChronoUnit.MILLIS.between(start, end));
        System.out.println("Adjacency Matrix by using local greedy method is");
        Display.printMatrix(greedyGraph.adjMatrix);
        System.out.printf("Cost by local greedy heuristic method %s%n", df.format(costByLocalGreedy));
        System.out.println("____________________________________________________________________________________________");

        start = Instant.now();
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
        end = Instant.now();
        System.out.println("____________________________________________________________________________________________");
        System.out.println("Adjacency Matrix by using annealing heuristic method without optimization");
        System.out.printf("Time to compute %d%n", ChronoUnit.MILLIS.between(start, end));
        Display.printMatrix(basicAnnealingGraph.adjMatrix);
        System.out.printf("Cost by Annealing heuristic method with unoptimised initial adjacency matrix %s%n", df.format(costOfBasicAnnealing));
        System.out.println("____________________________________________________________________________________________");

        // We start with an existing graph, which works as an optimization
        start = Instant.now();
        Graph annealingGraph = greedyGraph.getShallowCopy(greedyGraph.adjMatrix);
        Annealing annealing = new Annealing(annealingGraph);
        annealing.heuristicComputation();
        double costByAnnealing = annealingGraph.computeCost();
        end = Instant.now();
        System.out.println("____________________________________________________________________________________________");
        System.out.println("Adjacency Matrix by using annealing heuristic method is");
        System.out.printf("Time to compute %d%n", ChronoUnit.MILLIS.between(start, end));
        Display.printMatrix(annealingGraph.adjMatrix);
        System.out.printf("Cost by Annealing heuristic method %s%n", df.format(costByAnnealing));
        System.out.println("____________________________________________________________________________________________");
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
