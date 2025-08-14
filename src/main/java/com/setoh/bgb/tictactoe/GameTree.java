package com.setoh.bgb.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameTree {

    public static record Node(Board board){}

    private Map<Node, List<Node>> adjacencyMap = new HashMap<>();

    public void addNode(Node node) {
        adjacencyMap.computeIfAbsent(node, n -> new ArrayList<>());
    }

    public void addEdge(Node from, Node to) {
        addNode(from);
        addNode(to);
        adjacencyMap.get(from).add(to);
    }

    public List<Node> getChildren(Node node) {
        if (adjacencyMap.containsKey(node)) {
            return adjacencyMap.get(node);
        }
        return List.of();
    }

    public boolean isIn(Node node) {
        return adjacencyMap.containsKey(node);
    }
}