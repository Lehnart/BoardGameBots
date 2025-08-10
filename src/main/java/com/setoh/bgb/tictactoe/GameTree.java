package com.setoh.bgb.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameTree {

    public static interface GameTreeNode {
    }

    private Map<GameTreeNode, List<GameTreeNode>> adjacencyMap = new HashMap<>();

    public void addNode(GameTreeNode node) {
        adjacencyMap.computeIfAbsent(node, n -> new ArrayList<>());
    }

    public void addEdge(GameTreeNode from, GameTreeNode to) {
        addNode(from);
        addNode(to);
        adjacencyMap.get(from).add(to);
    }

    public List<GameTreeNode> getChildren(GameTreeNode node) {
        if (adjacencyMap.containsKey(node)) {
            return adjacencyMap.get(node);
        }
        return List.of();
    }

    public boolean isIn(GameTreeNode node) {
        return adjacencyMap.containsKey(node);
    }
}