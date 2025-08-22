package com.setoh.bgb.tictactoe.ai;

import com.setoh.bgb.ai.GameTree;
import com.setoh.bgb.ai.GameTreeExplorator;
import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;
import com.setoh.bgb.tictactoe.Logic;
import com.setoh.bgb.tictactoe.TicTacToeState;

public class ExhaustiveExplorator extends GameTreeExplorator<TicTacToeState> {

    @Override
    protected void exploreState(GameTree<TicTacToeState> gameTree, TicTacToeState state) {
        Board currentBoard = state.board();
        if (!Logic.isGameOver(currentBoard)) {
            addNextPositionsToExplore(gameTree, state);
        }
    }

    void addNextPositionsToExplore(GameTree<TicTacToeState> gameTree, TicTacToeState state) {
        Board board = state.board();
        Symbol symbol = state.symbol();
        for (Position p : board.getEmptyPositions()) {
            Board nextBoard = board.copy();
            nextBoard.setSymbol(p, state.symbol());
            TicTacToeState nextState = new TicTacToeState(nextBoard, symbol == Symbol.X ? Symbol.O : Symbol.X);
            gameTree.addNode(new GameTree.Node<>(nextState));
            gameTree.addEdge(new GameTree.Node<>(state), new GameTree.Node<>(nextState));
            nextStatesToExplore.add(nextState);
        }
    }
}
