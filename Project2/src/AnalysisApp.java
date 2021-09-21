import Graph.*;
import java.util.*;

public class AnalysisApp {
    public static void main(String[] args) {
        int numVertices;
        int input;
        long sTime, eTime;
        Scanner sc = new Scanner(System.in);

        System.out.printf("Enter number vertices in graph: ");
        numVertices = sc.nextInt();

        AdjListGraph adjListGraph = new AdjListGraph(numVertices); // ! array
        AdjMatGraph adjMatGraph = new AdjMatGraph(numVertices); // ! array
        int[][] distToAdjMat = new int[numVertices][numVertices];
        // ? Shortest distance from 0 to 1 would be distToAdjMat[0][1]
        int[][] distToAdjList = new int[numVertices][numVertices];

        AdjListGraph adjListGraph2 = new AdjListGraph(numVertices); // ! minHeap
        AdjMatGraph adjMatGraph2 = new AdjMatGraph(numVertices); // ! minHeap
        int[][] distToAdjMat2 = new int[numVertices][numVertices];
        // ? Shortest distance from 0 to 1 would be distToAdjMat[0][1]
        int[][] distToAdjList2 = new int[numVertices][numVertices];

        // max number of edges is V * (V - 1)
        boolean cont = true;
        do{
            System.out.println("(1) Reset Graph");
            System.out.println("(2) Generate Edges");
            System.out.println("(3) Run Dijkstra on all possible 'source'");
            System.out.println("(4) Find shortest distance from X -> Y");
            System.out.println("(5) View both graphs");
            System.out.println("(99) Exit Program");
            System.out.printf("Enter what you would like to do: ");
            input = sc.nextInt();

            switch (input){
                case 1:
                    // ! Graph is resetted to new number of vertices
                    System.out.printf("Enter number vertices in graph: ");
                    numVertices = sc.nextInt();
                    adjListGraph = new AdjListGraph(numVertices); // ! array
                    adjMatGraph = new AdjMatGraph(numVertices); // ! array

                    adjListGraph2 = new AdjListGraph(numVertices); // ! minHeap
                    adjMatGraph2 = new AdjMatGraph(numVertices); // ! minHeap
                    // ? Shortest distance from X to Y would be distToAdjMat[X][Y]
                    distToAdjMat = new int[numVertices][numVertices];
                    distToAdjList = new int[numVertices][numVertices];

                    distToAdjMat2 = new int[numVertices][numVertices];
                    distToAdjList2 = new int[numVertices][numVertices];
                    break;

                case 2:
                    System.out.printf("Enter number of edges to generate: ");
                    int genNumEdges = sc.nextInt();
                    System.out.println("Generating edges, please hold...");

                    if (Graph.genRandEdges(genNumEdges, adjListGraph, adjMatGraph, adjListGraph2, adjMatGraph2)){
                        System.out.println("Graph connection generated successful!");
                    }
                    else{
                        System.out.println("Something went wrong, please try again.");
                    }
                    break;

                case 3:
                    System.out.println("Running dijkstra on all sources...");
                    System.out.println("Num of Vertices: " + numVertices + ", Num of Edges: " + adjListGraph.get_numEdges());

                    // ? AdjMat method
                    sTime = System.nanoTime();
                    distToAdjMat[0] = adjMatGraph.dijkstra(0, false, "array");
                    //for(int i = 0; i < numVertices; i++){
                    //    distToAdjMat[i] = adjMatGraph.dijkstra(i, false);
                    //}
                    eTime = System.nanoTime();
                    System.out.println("** Dijkstra (Array as Priority Queue) on AdjMat");
                    System.out.println("~ Algorithm processed completely in " + (double) (eTime - sTime)/1000000 + " microseconds ~");

                    // ? AdjList method
                    sTime = System.nanoTime();
                    distToAdjList[0] = adjListGraph.dijkstra(0, false, "array");
                    //for(int i = 0; i < numVertices; i++){
                    //    distToAdjList[i] = adjListGraph.dijkstra(i, false);
                    //}
                    eTime = System.nanoTime();
                    System.out.println("Dijkstra (Array as Priority Queue) on AdjList");
                    System.out.println("Algorithm processed completely in " + (double)  (eTime - sTime) /1000000 + " microseconds");

                    // ? AdjMat method
                    sTime = System.nanoTime();
                    
                    distToAdjMat2[0] = adjMatGraph2.dijkstra(0, false, "minHeap");
                    //for(int i = 0; i < numVertices; i++){
                    //    distToAdjMat[i] = adjMatGraph.dijkstra(i, false);
                    //}
                    eTime = System.nanoTime();
                    System.out.println("Dijkstra (minHeap as Priority Queue) on AdjMat");
                    System.out.println("Algorithm processed completely in " + (double)  (eTime - sTime) /1000000 + " microseconds");

                    // ? AdjList method
                    sTime = System.nanoTime();
                    distToAdjList2[0] = adjListGraph2.dijkstra(0, false, "minHeap");
                    //for(int i = 0; i < numVertices; i++){
                    //    distToAdjList[i] = adjListGraph.dijkstra(i, false);
                    //}
                    eTime = System.nanoTime();
                    System.out.println("** Dijkstra (minHeap as Priority Queue) on AdjList");
                    System.out.println("~ Algorithm processed completely in " + (double)  (eTime - sTime) /1000000 + " microseconds ~");
                    break;

                case 4:
                    System.out.printf("Enter start and end point to find shortest distance: ");
                    int x = sc.nextInt();
                    int y = sc.nextInt();

                    if (x >= numVertices || y >= numVertices){
                        System.out.println("Out of bounds");
                    }
                    else{
                        System.out.printf("Shortest distance [adjMat] from Vertex %d -> %d is %d\n", x, y, distToAdjMat[x][y]);
                        System.out.printf("Shortest distance [adjList] from Vertex %d -> %d is %d\n", x, y, distToAdjList[x][y]);
                    }
                    
                    break;
                
                case 5:
                    System.out.println("adjListGraph");
                    adjListGraph.displayGraph();
                    System.out.println("adjMatGraph");
                    adjMatGraph.displayGraph();
                    break;

                case 99:
                    System.out.println("Exitting program, see you again! :)");
                    cont = false;
                    break;
                default:
                System.out.println("Invalid Option");
            }

        } while (cont);

        sc.close();
    }
}