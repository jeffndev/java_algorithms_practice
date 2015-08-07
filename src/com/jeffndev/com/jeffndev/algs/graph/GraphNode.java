package com.jeffndev.com.jeffndev.algs.graph;

/**
 * Created by jnewel200 on 8/6/2015.
 */
public interface GraphNode {
    boolean hasAdjacents();
    GraphNode getNextAdjacent();
}
