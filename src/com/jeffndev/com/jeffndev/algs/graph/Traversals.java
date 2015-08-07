package com.jeffndev.com.jeffndev.algs.graph;

/**
 * Created by jnewel200 on 8/6/2015.
 */
public class Traversals {
    public static void main(String [] args){
        TraverseGraph G = TraverseGraph.TestTreeGraph();
        TraverseNode s1 = G.getNode("A");
        traverseDF(s1,G);
    }
    public static void traverseDF(TraverseNode s1, TraverseGraph G){
        TraverseNode cur = s1;
        TraverseNode nxt = null;
        int visitedCount = 1;
        s1.setIsVisited(true);
        while (G.hasAdjNonVisiteds(cur) || cur.hasPrevious()){
            while (G.hasAdjNonVisiteds(cur)){
                nxt = G.nextAdjNonVisited(cur);
                nxt.setPrevious(cur);
                nxt.setIsVisited(true);
                visitedCount++;
                cur = nxt;
            }
            while(cur.hasPrevious()){
                //backtrack
                cur = cur.getPrevious();
                if(G.hasAdjNonVisiteds(cur)) break;
            }
        }
        System.out.println("Nodes visited in traversal: " + visitedCount);
    }
}
