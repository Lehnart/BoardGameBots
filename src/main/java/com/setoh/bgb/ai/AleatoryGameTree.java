package com.setoh.bgb.ai;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class AleatoryGameTree <S> {

    public static interface Transition <S>{
        S getOrigin();
        S getDestination();
    }

    public static class Choice <S> implements Transition<S> {
        private final S origin;
        private final S destination;

        public Choice(S origin, S destination) {
            this.origin = origin;
            this.destination = destination;
        }

        @Override
        public S getOrigin() {
            return origin;
        }

        @Override
        public S getDestination() {
            return destination;
        }
    }

    public static class AleatoryTransition <S> implements Transition<S> {
        private final S origin;
        private final S destination;
        private final double probability;

        public AleatoryTransition(S origin, S destination, double probability) {
            this.origin = origin;
            this.destination = destination;
            this.probability = probability;
        }

        @Override
        public S getOrigin() {
            return origin;
        }

        @Override
        public S getDestination() {
            return destination;
        }

        public double getProbability() {
            return probability;
        }
    }

    private Map<S, Set<Transition<S>>> adjacencyMap = new HashMap<>();

    public AleatoryGameTree() {
    }

    public AleatoryGameTree(S firstNode) {
        addState(firstNode);
    }
    
    public void addState(S state) {
        adjacencyMap.computeIfAbsent(state, n -> new HashSet<>());
    }

    public void addTransition(S from, Transition<S> transition) {
        addState(from);
        addState(transition.getDestination());
        adjacencyMap.get(from).add(transition);
    }

    public Set<Transition<S>> getTransitions(S state) {
        if (adjacencyMap.containsKey(state)) {
            return adjacencyMap.get(state);
        }
        return Set.of();
    }

    public boolean isIn(S state) {
        return adjacencyMap.containsKey(state);
    }
}