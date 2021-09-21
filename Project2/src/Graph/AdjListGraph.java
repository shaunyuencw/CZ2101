package Graph;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import Structures.*;

// ! Always assume arguments start from "0"
public class AdjListGraph extends Graph{
    private ArrayList<LinkedList<CNode>> adjLists;

    public AdjListGraph(int numVertices){
        adjLists = new ArrayList<>();
        set_numVertices(numVertices);
        for (int i = 0; i < numVertices; i++){
            adjLists.add(new LinkedList<CNode>());
        }
    }
    
    public boolean addEdge(int source, int destination, int weight, boolean print){
        // ! Out of bound.
        if (source >= get_numVertices() || destination >= get_numVertices()){
            if (print)  System.out.println("adjList Out of bounds");
            return false;
        }
        else if (source == destination){
            if (print)  System.out.println("adjList No need to link to self ._.");
            return false;
        }

        int temp = findCNode(source, destination);
        CNode toAdd = new CNode(destination, weight);

        if (temp != -1){
            if (print)  System.out.println("adjList Connection already exist.");
            return false;
        }
        else{
            adjLists.get(source).add(toAdd);
            if (print)  System.out.printf("adjList Added a link between %d -> %d\n", source, destination);
            edgeChange('+');
            return true;
        }
    }

    public boolean removeEdge(int source, int destination){
        // ! Out of bound.
        if (source >= get_numVertices() || destination >= get_numVertices()){
            System.out.println("adjList Out of bounds");
            return false;
        }
        else if (source == destination){
            System.out.println("adjList Cannot remove link from itself ._.");
            return false;
        }
        int temp = findCNode(source, destination);
        if (temp  != -1){
            adjLists.get(source).remove(temp);
            System.out.printf("adjList Removed a link between %d -> %d\n", source, destination);
            edgeChange('-');
            return true;
        }
        else{
            System.out.println("adjList Connection does not exist.");
            return false;
        }
    }
    
    public void displayGraph(){
        for (int i = 0; i < adjLists.size(); i++){
            LinkedList<CNode> cVertex = adjLists.get(i);
            System.out.print("Vertex " + i + ":");
            for (int j = 0; j < cVertex.size(); j++){
                System.out.print(" -> " + cVertex.get(j).getVertex() + " (" + cVertex.get(j).getWeight() + ")");
            }
            System.out.println();
        }
    }

    // Returns index if found
    public int findCNode(int source, int toFind){
        for (int i = 0; i < adjLists.get(source).size(); i++){
            if (adjLists.get(source).get(i).getVertex() == toFind){
                return i;
            }
        }

        return -1;
    }

    public int get_adjListGraphSize(){
        return adjLists.size();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int[] dijkstra(int sVertex, boolean print, String type){
        MinimizingHeap mh = new MinimizingHeap();
        PriorityQueue pq = new PriorityQueue();

        int dist[] = new int[get_numVertices()];
        Boolean[] visited = new Boolean[get_numVertices()];
        int[] prevVertex = new int[get_numVertices()];
        int V = get_numVertices();

        // Set all distance to "INFINITY" and visited to false
        for (int i = 0; i < V; i++){
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false; // Will be set to true when finalized
            prevVertex[i] = -1; // No prev Vertex
        }

        dist[sVertex] = 0; // Distance from source to source is 0
        prevVertex[sVertex] = -1;
        
        for (int i = 0; i < V; i++){
            if (type.equals("array")){
                pq.add(new CNode(i, dist[i]));
            }
            else if (type.equals("minHeap")){
                mh.add(new CNode(i , dist[i]));
            }
        }

        //mh.displayMinHeap();

        if (type.equals("minHeap")){
            while(!mh.isEmpty()){
                CNode cVertex = mh.extractMin();
                visited[cVertex.getVertex()] = true;

                LinkedList <CNode> cVertexLL = adjLists.get(cVertex.getVertex());
                for (Iterator<CNode> i = cVertexLL.iterator(); i.hasNext();){
                    CNode v = (CNode) i.next();
                    if (!visited[v.getVertex()] && dist[v.getVertex()] > dist[cVertex.getVertex()] + v.getWeight()){
                        dist[v.getVertex()] = dist[cVertex.getVertex()] + v.getWeight();
                        prevVertex[v.getVertex()] = cVertex.getVertex();
                        if (!mh.decreaseKey(v.getVertex(), dist[v.getVertex()])){
                            System.out.println("Failed to decreaseKey");
                        }
                    }
                }
            }
        }
        else if (type.equals("array")){
            while(!pq.isEmpty()){
                CNode cVertex = pq.dequeue();
                //System.out.println("Dequeued " + cVertex.getVertex() + " (" + cVertex.getWeight() + ")");
                visited[cVertex.getVertex()] = true;

                LinkedList <CNode> cVertexLL = adjLists.get(cVertex.getVertex());
                for (Iterator<CNode> i = cVertexLL.iterator(); i.hasNext();){
                    CNode v = (CNode) i.next();
                    if (!visited[v.getVertex()] && dist[v.getVertex()] > dist[cVertex.getVertex()] + v.getWeight()){
                        if (!pq.remove(v.getVertex())){
                            System.out.println("Failed to remove");
                        }
                        dist[v.getVertex()] = dist[cVertex.getVertex()] + v.getWeight();;
                        prevVertex[v.getVertex()] = cVertex.getVertex();
                        pq.add(new CNode(v.getVertex(), dist[v.getVertex()]));
                    }
                }
            }
        }
        
        if (print)  printSolution(dist, prevVertex, "adjList");
        return dist;
    }
}