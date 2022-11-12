import java.util.ArrayList;

public class Display {
    static void printCoordinates(ArrayList<String> networkPlane) {

        for (int i=0; i<networkPlane.size(); i++) {
            System.out.print(String.format("Node %d : (%s)",i,networkPlane.get(i)));
            if((i+1)%3==0) {
                System.out.println();
            } else {
                System.out.print("\t");
            }
        }
        System.out.println();
    }

    static void printMatrix(int[][] matrix){
        for(int i=0;i<matrix.length;i++){
            System.out.print("[");
            for(int j=0;j<matrix.length;j++){
                System.out.printf("%d",matrix[i][j]);
                if(j != matrix.length-1) {
                    System.out.print(",");
                }
            }
            System.out.print("],");
        }
        System.out.println();
    }
}
