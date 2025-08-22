package com.setoh.bgb.ai;

import java.util.HashSet;
import java.util.Set;

public abstract class GameTreeExplorator<State> {

    private final Set<State> statesToExplore = new HashSet<>();
    protected final Set<State> nextStatesToExplore = new HashSet<>();

    public GameTree<State> explore(State initialState) {
        GameTree<State> gameTree = initialize(initialState);
        while (!nextStatesToExplore.isEmpty()) {
            exploreNextStates(gameTree);
        }
        return gameTree;
    }

    GameTree<State> initialize(State initialState) {
        GameTree<State> gameTree = new GameTree<State>(new GameTree.Node<State>(initialState));
        nextStatesToExplore.add(initialState);
        return gameTree;
    }

    void exploreNextStates(GameTree<State> gameTree) {
        statesToExplore.clear();
        statesToExplore.addAll(nextStatesToExplore);
        nextStatesToExplore.clear();
        for (State state : statesToExplore) {
            exploreState(gameTree, state);
        }
    }

    protected abstract void exploreState(GameTree<State> gameTree, State state);
}
