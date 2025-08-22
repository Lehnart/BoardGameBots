package com.setoh.bgb.ai;

import java.util.HashSet;
import java.util.Set;

public abstract class GameTreeExplorator<S> {

    private final Set<S> statesToExplore = new HashSet<>();
    protected final Set<S> nextStatesToExplore = new HashSet<>();

    public GameTree<S> explore(S initialState) {
        GameTree<S> gameTree = initialize(initialState);
        while (!nextStatesToExplore.isEmpty()) {
            exploreNextStates(gameTree);
        }
        return gameTree;
    }

    GameTree<S> initialize(S initialState) {
        GameTree<S> gameTree = new GameTree<>(new GameTree.Node<>(initialState));
        nextStatesToExplore.add(initialState);
        return gameTree;
    }

    void exploreNextStates(GameTree<S> gameTree) {
        statesToExplore.clear();
        statesToExplore.addAll(nextStatesToExplore);
        nextStatesToExplore.clear();
        for (S state : statesToExplore) {
            exploreState(gameTree, state);
        }
    }

    protected abstract void exploreState(GameTree<S> gameTree, S state);
}
