import java.util.*;
import Graph.AdjListGraph;

// ! Vertex always start from 0
public class AdjListApp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.printf("How many vertices are there: \n");
        int numVertices = sc.nextInt();
        int source, destination, weight, input;

        AdjListGraph adjListGraph = new AdjListGraph(numVertices);

        boolean cont = true;

        do{
            System.out.println("(1) Add an edge");
            System.out.println("(2) Remove an edge");
            System.out.println("(3) View graph");
            System.out.println("(4) Run Dijkstra Algorithm");
            System.out.println("(5) End");

            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.printf("Enter connection to make (source, destination, weight): ");
                    source = sc.nextInt();
                    destination = sc.nextInt();
                    weight = sc.nextInt();
                    adjListGraph.addEdge(source, destination, weight, true);
                    break;
                case 2:
                    System.out.printf("Enter connection to remove (source, destination): ");
                    source = sc.nextInt();
                    destination = sc.nextInt();
                    adjListGraph.removeEdge(source, destination);
                    break;
                case 3:
                    adjListGraph.displayGraph();
                    break;
                case 4:
                    System.out.printf("Enter source vertex: ");
                    input = sc.nextInt();
                    adjListGraph.dijkstra(input, true, "array");
                    break;
                case 5:
                    System.out.println("Ending Program! See you again :)");
                    cont = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }


        } while(cont);

        sc.close();
    }
}
