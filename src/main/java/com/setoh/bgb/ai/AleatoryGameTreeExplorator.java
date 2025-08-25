package com.setoh.bgb.ai;

import java.util.HashSet;
import java.util.Set;

public abstract class AleatoryGameTreeExplorator<S> {

    private final Set<S> statesToExplore = new HashSet<>();
    protected final Set<S> nextStatesToExplore = new HashSet<>();

    public AleatoryGameTree<S> explore(S initialState) {
        AleatoryGameTree<S> gameTree = initialize(initialState);
        while (!nextStatesToExplore.isEmpty()) {
            exploreNextStates(gameTree);
            nextStatesToExplore.removeIf(s -> gameTree.isIn(s) && !gameTree.getTransitions(s).isEmpty());
        }
        return gameTree;
    }

    AleatoryGameTree<S> initialize(S initialState) {
        AleatoryGameTree<S> gameTree = new AleatoryGameTree<>();
        nextStatesToExplore.add(initialState);
        return gameTree;
    }

    void exploreNextStates(AleatoryGameTree<S> gameTree) {
        statesToExplore.clear();
        statesToExplore.addAll(nextStatesToExplore);
        nextStatesToExplore.clear();
        for (S state : statesToExplore) {
            exploreState(gameTree, state);
        }
    }

    protected abstract void exploreState(AleatoryGameTree<S> gameTree, S state);
}
