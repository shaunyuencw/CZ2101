package Structures;

public class PNode {
    private int parent;
    private int rank;

    public PNode(int parent, int rank){
        this.parent = parent;
        this.rank = rank;
    }

    public int getParent(){
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getRank(){
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
