package Graph;


public abstract class Graph{
    private int numVertices = 0;
    private int numEdges = 0;

    public Graph(){
    }
    
    public int get_numVertices(){
        return numVertices;
    }

    public int get_numEdges(){
        return numEdges;
    }

    public void edgeChange(char sign){
        if (sign == '+'){
            this.numEdges++;
        }
        else if (sign == '-'){
            this.numEdges--;
        }
    }

    public void set_numVertices(int numVertices){
        this.numVertices = numVertices;
    }

    public abstract void displayGraph();
    public abstract boolean addEdge(int source, int destination, int weight, boolean print);
    public abstract boolean removeEdge(int source, int destination);

    public static boolean genRandEdges(int genNumEdges, AdjListGraph adjListGraph, AdjMatGraph adjMatGraph, AdjListGraph adjListGraph2, AdjMatGraph adjMatGraph2){
        int numSuccesses = 0;
        int V = adjListGraph.get_numVertices();

        if (numSuccesses >= genNumEdges)   return false; // Invalid Call
        if (genNumEdges + adjListGraph.get_numEdges()> V * (V - 1)) return false; // Impossible to generate
        if (genNumEdges + adjMatGraph.get_numEdges()> V * (V - 1)) return false; // Impossible to generate
        

        while (numSuccesses < genNumEdges){
            int source, destination, weight;

            int range = V - 0;
            source = (int)(Math.random() * range);
            destination = (int)(Math.random() * range);
            // ? Lets put weight 1 - 50 -> (50 - 1 + 1) + 1
            weight = (int)(Math.random() * (50 - 1 + 1) + 1); // (Math.random() * range) + min; range = max-min + 1

            // TODO make source -> destination selection more efficient?
            if (adjMatGraph.checkConnection(source, destination) && source != destination){ // Only if connection doesnt exist, then attempt to add.
                if (adjListGraph.addEdge(source, destination, weight, false) && adjMatGraph.addEdge(source, destination, weight, false)
                && adjListGraph2.addEdge(source, destination, weight, false) && adjMatGraph2.addEdge(source, destination, weight, false)){
                    numSuccesses++;
                }
            }
        }

        return true;

    }

    public abstract int[] dijkstra(int sVertex, boolean print, String type);
    
    public void printSolution(int[] dist, int[] prevVertex, String type){
        System.out.println("~~~ " + type + " ~~~");
        System.out.println("Vertex \t\tDistance from Source\tPrevious Vertex\n");
        for (int i = 0; i < dist.length; i++){
            if (dist[i] != Integer.MAX_VALUE){
                System.out.printf("%d \t\t", i, dist[i], prevVertex[i]);
                System.out.printf("%d\t\t\t", dist[i]);
                if (prevVertex[i] != -1)
                    System.out.printf("%d\n", prevVertex[i]);
                else{
                    System.out.printf("null\n");
                }
            }
            else{
                System.out.printf("%d \t\tINF\t\t\tnull\n", i); // No path to this vertex
            }
        }
    }
}