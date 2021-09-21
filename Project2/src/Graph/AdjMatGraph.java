package Graph;

import Structures.*;

public class AdjMatGraph extends Graph{
    private int adjMatrix[][];

    public AdjMatGraph(int numVertices){
        set_numVertices(numVertices);
        adjMatrix = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++){
            for (int j = 0; j < numVertices; j++){
                adjMatrix[i][j] = 0;
            }
        }
    }

    public boolean checkConnection(int source, int destination){
        return (adjMatrix[source][destination] == 0);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean addEdge(int source, int destination, int weight, boolean print){
        if (source >= get_numVertices() || destination >= get_numVertices()){
            if (print)  System.out.println("adjMat Out of bounds");
            return false;
        }
        else if (source == destination){
            if (print)  System.out.println("adjMat No need to link to self ._.");
            return false;
        }

        if (adjMatrix[source][destination] != 0){
            if (print)  System.out.println("adjMat Connection already exist");
            return false;
        }
        else{
            adjMatrix[source][destination] = weight;
            if (print)  System.out.printf("adjMat Added a link between %d -> %d\n", source, destination);
            edgeChange('+');
            return true;
        }
    }

    public boolean removeEdge(int source, int destination){
        if (source >= get_numVertices() || destination >= get_numVertices()){
            System.out.println("adjMat Out of bounds");
            return false;
        }
        else if (source == destination){
            System.out.println("adjMat No need to link to self ._.");
            return false;
        }
        
        if (adjMatrix[source][destination] == 0){
            System.out.println("adjMat Connection does not exist");
            return false;
        }
        else{
            adjMatrix[source][destination] = 0;
            System.out.printf("adjMat Removed a link between %d -> %d\n", source, destination);
            edgeChange('-');
            return true;
        }
    }

    public void displayGraph(){
        for (int i = 0; i < get_numVertices(); i++){
            System.out.print("Vertex " + i + ": ");
            for (int j = 0; j < get_numVertices(); j++){
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[] dijkstra(int sVertex, boolean print, String type){
        MinimizingHeap mh = new MinimizingHeap();
        PriorityQueue pq = new PriorityQueue();
        int dist[] = new int[get_numVertices()];
        Boolean[] visited = new Boolean[get_numVertices()];
        int[] prevVertex = new int[get_numVertices()];
        int V = get_numVertices();

        // Set all distance to "INFINITY" and visited to false
        for (int i = 0; i < get_numVertices(); i++){
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false; // Will be set to true when finalized
            prevVertex[i] = -1;
        }

        dist[sVertex] = 0; // Distance from source to source is 0
        prevVertex[sVertex] = -1;

        for (int i = 0; i < V; i++){
            if (type.equals("array")){
                pq.add(new CNode(i, dist[i])); // Put in all vertices
            }
            else if (type.equals("minHeap")){
                mh.add(new CNode(i, dist[i]));
            }
        }

        //pq.displayPQueue();
        if (type.equals("array")){
            while(!pq.isEmpty()){
                CNode cVertex = pq.dequeue();
                //System.out.println("Dequeued " + cVertex.getVertex() + " (" + cVertex.getWeight() + ")");
                visited[cVertex.getVertex()] = true;
    
                for (int v = 0; v < V; v++){
                    if(adjMatrix[cVertex.getVertex()][v] != 0){
                        if (!visited[v] && dist[v] > dist[cVertex.getVertex()] + adjMatrix[cVertex.getVertex()][v]){
                            if (!pq.remove(v)){
                                System.out.println("Failed to remove!");
                            }
                            dist[v] = dist[cVertex.getVertex()] + adjMatrix[cVertex.getVertex()][v];
                            prevVertex[v] = cVertex.getVertex();
                            pq.add(new CNode(v, dist[v]));
                        }
                    }
                }
            }
        }
        else if (type.equals("minHeap")){
            while(!mh.isEmpty()){
                CNode cVertex = mh.extractMin();
                visited[cVertex.getVertex()] = true;

                for (int v = 0; v < V; v++){
                    if(adjMatrix[cVertex.getVertex()][v] != 0){
                        if (!visited[v] && dist[v] > dist[cVertex.getVertex()] + adjMatrix[cVertex.getVertex()][v]){
                            dist[v] = dist[cVertex.getVertex()] + adjMatrix[cVertex.getVertex()][v];
                            prevVertex[v] = cVertex.getVertex();
                            if (!mh.decreaseKey(v, dist[v])){
                                System.out.println("Failed to decreaseKey");
                            }
                        }
                    }
                }
            }
        }
        
        
        if (print)  printSolution(dist, prevVertex, "adjMat");
        return dist;
    }  
}