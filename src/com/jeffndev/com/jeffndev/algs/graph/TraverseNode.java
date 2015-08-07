package com.jeffndev.com.jeffndev.algs.graph;

/**
 * Created by jnewel200 on 8/6/2015.
 */
public class TraverseNode{
    private TraverseNode _previous;
    private boolean _visited;
    private String _key;

    public TraverseNode(String key){
        _key = key;
        setIsVisited(false);
    }
    public String getKey(){ return _key;}

    public boolean hasPrevious(){
        return _previous != null;
    }
    public void setPrevious(TraverseNode nd){
        _previous = nd;
    }
    public TraverseNode getPrevious(){ return _previous;}

    public boolean isVisited() {return _visited;}
    public void setIsVisited(boolean isV){
        _visited = isV;
    }
}
