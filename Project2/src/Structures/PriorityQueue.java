package Structures;
import java.util.ArrayList;

public class PriorityQueue {
    private ArrayList<CNode> pq;

    public PriorityQueue(){
        pq = new ArrayList<CNode>();
    }

    public void add(CNode toAdd){
        //System.out.println("To add, " + toAdd.getVertex() + " (" + toAdd.getWeight() + ")");
        int i;
        if (pq.size() == 0){
            pq.add(toAdd);
            //System.out.println("Added, " + toAdd.getVertex() + " (" + toAdd.getWeight() + ")");
        }
        else{
            for (i = 0; i < pq.size(); i++){
                CNode temp = pq.get(i);
                if (toAdd.getWeight() > temp.getWeight()){
                    break;
                }
            }
            pq.add(i, toAdd);
            //System.out.println("Added, " + toAdd.getVertex() + " (" + toAdd.getWeight() + ")");
        }
    }

    public int get_numNodes(){
        return pq.size();
    }

    public boolean remove(int vertex){
        for (int i = 0; i < pq.size(); i++){
            if (pq.get(i).getVertex() == vertex){
                //System.out.println("Removed " + pq.get(i).getVertex() + " (" + pq.get(i).getWeight() + ")");
                pq.remove(i);
                return true;
            }
        }

        return false;
    }

    public CNode dequeue(){
        if (pq.size() <= 0){
            return new CNode(-1, 0); // Queue is empty
        }

        CNode lastNode = pq.get(pq.size() - 1); // Get smallest "weight" which is last index
        pq.remove(pq.size() - 1); // remove smallest "weight" which is last index

        //System.out.println("Returning " + lastNode.getVertex() + " (" + lastNode.getWeight() + ")");
        return lastNode;
    }

    public boolean isEmpty(){
        return pq.isEmpty();
    }

    public void displayPQueue(){
        for (int i = 0; i < pq.size(); i++){
            CNode temp = pq.get(i);

            System.out.printf("%d (%d), ", temp.getVertex(), temp.getWeight());
        }

        System.out.println();
    }
}
