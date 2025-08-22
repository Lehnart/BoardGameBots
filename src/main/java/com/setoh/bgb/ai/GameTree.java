package com.setoh.bgb.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameTree <S> {

    public static class Node <S> {
        private final S state;

        public Node(S state) {
            this.state = state;
        }

        public S state() {
            return state;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj == null || this.getClass() != obj.getClass()) {
                return false;
            }
            Node<?> other = (Node<?>) obj;
            return state.equals(other.state);
        }

        @Override
        public int hashCode() {
            return state.hashCode();
        }
    }

    private Map<Node<S>, List<Node<S>>> adjacencyMap = new HashMap<>();

    public GameTree() {
    }

    public GameTree(Node<S> firstNode) {
        addNode(firstNode);
    }
    
    public void addNode(Node<S> node) {
        adjacencyMap.computeIfAbsent(node, n -> new ArrayList<>());
    }

    public void addEdge(Node<S> from, Node<S> to) {
        addNode(from);
        addNode(to);
        adjacencyMap.get(from).add(to);
    }

    public List<Node<S>> getChildren(Node<S> node) {
        if (adjacencyMap.containsKey(node)) {
            return adjacencyMap.get(node);
        }
        return List.of();
    }

    public boolean isIn(Node<S> node) {
        return adjacencyMap.containsKey(node);
    }
}