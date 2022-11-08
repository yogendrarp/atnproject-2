import java.text.DecimalFormat;
import java.util.ArrayList;

public class Graph {
    double [][]distMatrix;
    int numberOfNodes;
    ArrayList<String> networkMap;
    int [][] adjMatrix;
    ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
    final static DecimalFormat df = new DecimalFormat("#.##");

    public Graph(int numberOfNodes, ArrayList<String> networkMap){
        this.numberOfNodes = numberOfNodes;
        distMatrix = new double[this.numberOfNodes][this.numberOfNodes];
        this.networkMap = networkMap;
    }

    public void calculateDistanceMatrix() {
        //Using distance formula we can calculate the distance between
        //any two points on the graph
        for(int i=0; i<networkMap.size(); i++){
            int x1 = Integer.parseInt(networkMap.get(i).split(",")[0]);
            int y1 = Integer.parseInt(networkMap.get(i).split(",")[1]);
            for(int j=0; j<networkMap.size(); j++) {
                if(i==j){
                    continue;
                }
                int x2 = Integer.parseInt(networkMap.get(j).split(",")[0]);
                int y2 = Integer.parseInt(networkMap.get(j).split(",")[1]);
                distMatrix[i][j] = getDistanceBetweenPoints(x1, y1, x2, y2);
            }
        }
    }

    public double computeCost()  {
        double cost = 0;
        for(int i=0; i<numberOfNodes; i++) {
            for(int j=0; j<numberOfNodes; j++) {
                if(adjMatrix[i][j]==1) {
                    cost += distMatrix[i][j];
                }
            }
        }
        return (cost/2);
    }

    public void plotEdgesUsingAdjMatrix() {
        for(int i=0;i<numberOfNodes;i++){
            edges.add(new ArrayList<>());
        }
        for(int i=0; i<numberOfNodes; i++) {
            for(int j=0; j<numberOfNodes; j++) {
                if(adjMatrix[i][j] == 1) {
                    edges.get(i).add(j);
                    edges.get(j).add(i);
                }
            }
        }
    }

    public Graph getShallowCopy(int [][] adjMatrix){
        Graph shallowGraph =  new Graph(this.numberOfNodes, this.networkMap);
        shallowGraph.distMatrix = this.distMatrix;
        shallowGraph.adjMatrix = adjMatrix;
        return shallowGraph;
    }

    private double getDistanceBetweenPoints(int x1, int y1, int x2, int y2) {
        return Double.parseDouble
                (df.format(Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2))));
    }
}
