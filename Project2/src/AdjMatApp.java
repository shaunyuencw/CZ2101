import java.util.Scanner;

import Graph.AdjMatGraph;

public class AdjMatApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("How many vertices are there: \n");
        int numVertices = sc.nextInt();
        int source, destination, weight, input;

        AdjMatGraph adjMatGraph = new AdjMatGraph(numVertices);

        boolean cont = true;

        do{
            System.out.println("(1) Add an edge");
            System.out.println("(2) Remove an edge");
            System.out.println("(3) View Graph");
            System.out.println("(4) Run Dijkstra Algorithm");
            System.out.println("(5) End");

            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.printf("Enter connection to make (source, destination, weight): ");
                    source = sc.nextInt();
                    destination = sc.nextInt();
                    weight = sc.nextInt();
                    adjMatGraph.addEdge(source, destination, weight, true);
                    break;
                case 2:
                    System.out.printf("Enter connection to remove (source, destination): ");
                    source = sc.nextInt();
                    destination = sc.nextInt();
                    adjMatGraph.removeEdge(source, destination);
                    break;
                case 3:
                    adjMatGraph.displayGraph();
                    break;
                case 4:
                    System.out.printf("Enter source vertex: ");
                    input = sc.nextInt();
                    if (input < 0 || input > adjMatGraph.get_numVertices()){
                        System.out.println("Invalid vertex");
                    }
                    else{
                        adjMatGraph.dijkstra(input, true, "minHeap");
                    }
                    
                    break;
                case 5:
                    System.out.println("Ending program! See you again soon :)");
                    cont = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }


        } while(cont);
        sc.close();
    }
}
