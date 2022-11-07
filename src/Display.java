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
    }
}
