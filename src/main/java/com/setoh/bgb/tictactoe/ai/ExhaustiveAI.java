package com.setoh.bgb.tictactoe.ai;

import com.setoh.bgb.ai.GameTree;
import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.TicTacToeState;
import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;

public class ExhaustiveAI implements AI {

    private ExhaustiveExplorator explorator = new ExhaustiveExplorator();
    private GameTree<TicTacToeState> tree;

    public ExhaustiveAI() {
        this.tree = explorator.explore(new TicTacToeState(new Board(), Symbol.X));
    }

    public Position play(Board board, Symbol playerSymbol) {
        Position nextPosition = null;
        ExhaustiveEvaluator evaluator = new ExhaustiveEvaluator(tree);
        Board nextBestBoard = evaluator.findNextBestBoard(board, playerSymbol);
        for (Position position : board.getEmptyPositions()) {
            Board copiedBoard = board.copy();
            copiedBoard.setSymbol(position, playerSymbol);
            if (copiedBoard.equals(nextBestBoard)) {
                nextPosition = position;
            }
        }
        return nextPosition;
    }

}
