package com.jeffndev.com.jeffndev.algs.graph;

import java.util.*;

/**
 * Created by jnewel200 on 8/6/2015.
 */
public class TraverseGraph {
    private Map<String, TraverseNode> _nodes = new HashMap<>();
    private Map<String, Set<String>> _connections = new HashMap<>();

    public TraverseNode getNode(String key){
        return _nodes.get(key);
    }

    public boolean hasAdjNonVisiteds(TraverseNode nd){
        if(!_connections.containsKey(nd.getKey())) return false;

        if(_connections.get(nd.getKey()).isEmpty()) return false;

        for(String key : _connections.get(nd.getKey())){
            TraverseNode adjNode = _nodes.get(key);
            if(!adjNode.isVisited()) return true;
        }
        return false;
    }
    public TraverseNode nextAdjNonVisited(TraverseNode nd){
        if(!_connections.containsKey(nd.getKey())) return null;

        if(_connections.get(nd.getKey()).isEmpty()) return null;

        for(String key : _connections.get(nd.getKey())) {
            TraverseNode adjNode = _nodes.get(key);
            if (!adjNode.isVisited()) return adjNode;
        }
        return null;
    }
    public TraverseGraph(Set<String> nodeNames){
        for(String s: nodeNames)
            _nodes.put(s, new TraverseNode(s));
    }
    public TraverseGraph(Set<String> nodeNames, Map<String, Set<String>> connections){
        for(String s: nodeNames)
            _nodes.put(s, new TraverseNode(s));
        for(String key : connections.keySet()) {
            if (_nodes.containsKey(key)) {
                Set<String> connects = new TreeSet<>();
                for (String k : connections.get(key)) {
                    if (_nodes.containsKey(k))
                        connects.add(k);
                }
                _connections.put(key, connects);
            }
        }
    }


    public static TraverseGraph TestTreeGraph(){
        Set<String> nodeKeys = new HashSet<String>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L"));
        Map<String, Set<String>> connects = new HashMap<>();
        connects.put("A", new HashSet<String>(Arrays.asList("B","C","D")));
        connects.put("B", new HashSet<String>(Arrays.asList("A","J","K")));
        connects.put("C", new HashSet<String>(Arrays.asList("A","L")));
        connects.put("D", new HashSet<String>(Arrays.asList("A","E","G")));
        connects.put("E", new HashSet<String>(Arrays.asList("D","F")));
        connects.put("F", new HashSet<String>(Arrays.asList("E")));
        connects.put("G", new HashSet<String>(Arrays.asList("D")));
        connects.put("H", new HashSet<String>(Arrays.asList("L")));
        connects.put("I", new HashSet<String>(Arrays.asList("L")));
        connects.put("J", new HashSet<String>(Arrays.asList("B")));
        connects.put("K", new HashSet<String>(Arrays.asList("B")));
        connects.put("L", new HashSet<String>(Arrays.asList("C","H","I")));

        TraverseGraph G = new TraverseGraph(nodeKeys, connects);
        return G;
    }
    public static void main(String [] args){
        TraverseGraph G = TestTreeGraph();
    }
}
