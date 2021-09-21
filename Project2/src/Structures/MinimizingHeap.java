package Structures;

import java.util.ArrayList;

public class MinimizingHeap {
    private ArrayList<CNode> minHeap;
    
    public MinimizingHeap(){
        minHeap = new ArrayList<CNode>();
    }

    public void swap(int left, int right){
        CNode temp = minHeap.get(left);
        minHeap.set(left, minHeap.get(right));
        minHeap.set(right, temp);
    }

    public void heapify(int root){
        if (minHeap.size() < 3) return; // Nothing to heapify

        int smallest = root;
        int l = 2 * root + 1;
        int r = 2 * root + 2;

        if (l < minHeap.size()){
            if (minHeap.get(l).getWeight() < minHeap.get(smallest).getWeight()){
                smallest = l;
            }
        }

        if (r < minHeap.size()){
            if (minHeap.get(r).getWeight() < minHeap.get(smallest).getWeight()){
                smallest = r;
            }
        }

        if (smallest != root){
            swap(root, smallest);
            heapify(smallest);
        }
    }

    public void add(CNode toAdd){
        if (minHeap.size() == 0){
            minHeap.add(toAdd);
        }

        else if (minHeap.size() <= 2){
            if (toAdd.getWeight() < minHeap.get(0).getWeight()){
                minHeap.add(0, toAdd); // * If smaller than current root, make it the root
            }
            else{
                minHeap.add(toAdd); // * Else, just add it as child of root
            }
        }

        else{ // minimum size of 3
            int addedIndex = minHeap.size();
            minHeap.add(toAdd);
            int parent;
            do{
                parent = addedIndex / 2;
                if (minHeap.get(addedIndex).getWeight() < minHeap.get(parent).getWeight()){
                    swap(addedIndex, parent);
                    addedIndex = parent;
                }
                else{
                    break; // added node is in the correct positon
                }
            } while(parent > 0);
        }
    }

    public int get_numNodes(){
        return minHeap.size();
    }

    public void displayMinHeap(){
        for (int i = 0; i < minHeap.size(); i++){
            CNode temp = minHeap.get(i);

            System.out.printf("%d (%d), ", temp.getVertex(), temp.getWeight());
        }
    }

    public CNode extractMin(){
        if (minHeap.size() <= 0){
            return new CNode(-1, 0);
        }
        else{
            CNode minNode = minHeap.get(0);
            swap(0, minHeap.size() - 1);
            minHeap.remove(minHeap.size() - 1);
            if (minHeap.size() >= 3){
                heapify(0);
            }
            else if(minHeap.size() == 2){
                if (minHeap.get(1).getWeight() < minHeap.get(0).getWeight()){
                    swap(0, 1);
                }
            }
            return minNode;
        }
    }

    public boolean decreaseKey(int vertex, int newWeight){
        // We first find the index of the CNode that we need to modify
        //System.out.println("Looking for vertex " + vertex);
        for (int i = 0; i < minHeap.size(); i++){
            if (minHeap.get(i).getVertex() == vertex){
                //System.out.println("Found vertex " + vertex);
                //System.out.println("Old weight was " + minHeap.get(i).getWeight());
                minHeap.get(i).setWeight(newWeight);
                //System.out.println("Set weight to " + minHeap.get(i).getWeight());

                // ? We will check how far up we need to move it up the heap
                // ? Note that you can only decrease key in this manner
                int parent;
                do{
                    parent = i / 2;
                    if (minHeap.get(i).getWeight() < minHeap.get(parent).getWeight()){
                        swap(i, parent);
                        //System.out.println("Swapped " + minHeap.get(i).getVertex() + " with " + minHeap.get(parent).getVertex());
                        i = parent;
                    }
                    else{
                        break; // added node is in the correct positon
                    }
                } while(parent > 0);

                return true;
            }
        }
        return false;
    }

    public boolean isEmpty(){
        return minHeap.isEmpty();
    }
}
