import Structures.*;

public class App {
    public static void main(String[] args) throws Exception {
        PriorityQueue pq = new PriorityQueue();
        int dist[] = new int[5];
        for (int i = 0; i < 5; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < 5; i++){
            pq.add(new CNode(i, dist[i]));
        }

        //mh.displayMinHeap();
        //System.out.println();
        System.out.println("There are " + pq.get_numNodes() + " nodes in the queue");

        while(pq.get_numNodes() > 0){
            CNode temp = pq.dequeue(); // O(1)
            if (temp.getVertex() != -1){
                System.out.printf("%d (%d) \n", temp.getVertex(), temp.getWeight());
            }
            else{
                System.out.println("minHeap is empty");
            }
        }

        System.out.println("There are " + pq.get_numNodes() + " nodes in the queue");

        /*System.out.println("Dequeue");
        CNode temp = mh.getRoot();
        System.out.println("There are " + mh.get_numNodes() + " nodes in the queue");
        System.out.println(temp.getVertex() + " : " + temp.getWeight());*/

    }
}
