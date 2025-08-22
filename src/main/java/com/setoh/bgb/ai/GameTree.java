package com.setoh.bgb.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameTree <State> {

    public static class Node <State> {
        private final State state;

        public Node(State state) {
            this.state = state;
        }

        public State state() {
            return state;
        }
        
        @Override
        public boolean equals(Object obj) {
            Node<?> other = (Node<?>) obj;
            return state == null ? other.state == null : state.equals(other.state);
        }

        @Override
        public int hashCode() {
            return state == null ? 0 : state.hashCode();
        }
    }

    private Map<Node<State>, List<Node<State>>> adjacencyMap = new HashMap<>();

    public GameTree() {
    }

    public GameTree(Node<State> firstNode) {
        addNode(firstNode);
    }
    
    public void addNode(Node<State> node) {
        adjacencyMap.computeIfAbsent(node, n -> new ArrayList<>());
    }

    public void addEdge(Node<State> from, Node<State> to) {
        addNode(from);
        addNode(to);
        adjacencyMap.get(from).add(to);
    }

    public List<Node<State>> getChildren(Node<State> node) {
        if (adjacencyMap.containsKey(node)) {
            return adjacencyMap.get(node);
        }
        return List.of();
    }

    public boolean isIn(Node<State> node) {
        return adjacencyMap.containsKey(node);
    }
}