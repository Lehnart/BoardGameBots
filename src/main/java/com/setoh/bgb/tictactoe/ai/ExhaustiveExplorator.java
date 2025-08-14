package com.setoh.bgb.tictactoe.ai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;
import com.setoh.bgb.tictactoe.GameTree;
import com.setoh.bgb.tictactoe.Logic;
import com.setoh.bgb.tictactoe.State;

public class ExhaustiveExplorator {

    private final Set<State> statesToExplore = new HashSet<>();
    private final Set<State> nextStatesToExplore = new HashSet<>();

    public GameTree explore(Board board, Symbol symbol) {
        GameTree gameTree = initialize(board, symbol);
        while (!nextStatesToExplore.isEmpty()) {
            exploreNextStates(gameTree);
        }
        return gameTree;
    }

    GameTree initialize(Board board, Symbol symbol) {
        State initialState = new State(board, symbol);
        GameTree gameTree = new GameTree(new GameTree.Node(initialState));
        nextStatesToExplore.add(initialState);
        return gameTree;
    }

    void exploreNextStates(GameTree gameTree) {
        statesToExplore.clear();
        statesToExplore.addAll(nextStatesToExplore);
        nextStatesToExplore.clear();
        System.out.println("Exploring " + statesToExplore.size() + " states");
        for (State state : statesToExplore) {
            exploreState(gameTree, state);
        }
    }

    void exploreState(GameTree gameTree, State state) {
        Board currentBoard = state.board();
        if (!Logic.isGameOver(currentBoard)) {
            addNextPositionsToExplore(gameTree, state);
        }
    }

    void addNextPositionsToExplore(GameTree gameTree, State state) {
        Board board = state.board();
        Symbol symbol = state.symbol();
        for (Position p : board.getEmptyPositions()) {
            Board nextBoard = board.copy();
            nextBoard.setSymbol(p, state.symbol());
            State nextState = new State(nextBoard, symbol == Symbol.X ? Symbol.O : Symbol.X);
            gameTree.addNode(new GameTree.Node(nextState));
            gameTree.addEdge(new GameTree.Node(state), new GameTree.Node(nextState));
            nextStatesToExplore.add(nextState);
        }
    }
}
