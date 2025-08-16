package com.setoh.bgb.tictactoe.ai;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;

import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;
import com.setoh.bgb.tictactoe.GameTree;

public class ExhaustiveEvaluatorTest {

    @Test
    public void testEvaluateGameBoard() {
        Board winBoard = new Board();
        winBoard.setSymbol(new Position(0, 0), Symbol.X);
        winBoard.setSymbol(new Position(0, 1), Symbol.X);
        winBoard.setSymbol(new Position(0, 2), Symbol.X);
        assertThat(ExhaustiveEvaluator.evaluateGameOverBoard(winBoard, Symbol.X)).isEqualTo(1.);

        Board loseBoard = new Board();
        loseBoard.setSymbol(new Position(0, 0), Symbol.X);
        loseBoard.setSymbol(new Position(0, 1), Symbol.X);
        loseBoard.setSymbol(new Position(0, 2), Symbol.X);
        assertThat(ExhaustiveEvaluator.evaluateGameOverBoard(loseBoard, Symbol.O)).isEqualTo(-1.);

        Board drawBoard = new Board();
        drawBoard.setSymbol(new Position(0, 0), Symbol.X);
        drawBoard.setSymbol(new Position(0, 1), Symbol.O);
        drawBoard.setSymbol(new Position(0, 2), Symbol.X);
        drawBoard.setSymbol(new Position(1, 0), Symbol.X);
        drawBoard.setSymbol(new Position(1, 1), Symbol.O);
        drawBoard.setSymbol(new Position(1, 2), Symbol.X);
        drawBoard.setSymbol(new Position(2, 0), Symbol.O);
        drawBoard.setSymbol(new Position(2, 1), Symbol.X);
        drawBoard.setSymbol(new Position(2, 2), Symbol.O);
        assertThat(ExhaustiveEvaluator.evaluateGameOverBoard(drawBoard, Symbol.O)).isEqualTo(0.);
    }

    @Test
    public void testFindNextBestBoard() {
        Board board = new Board();
        board.setSymbol(new Position(0, 0), Symbol.O); 
        board.setSymbol(new Position(1, 0), Symbol.O);
        board.setSymbol(new Position(1, 1), Symbol.X);
        board.setSymbol(new Position(1, 2), Symbol.X);

        ExhaustiveExplorator explorator = new ExhaustiveExplorator();
        GameTree tree = explorator.explore(board, Symbol.X);
        ExhaustiveEvaluator evaluator = new ExhaustiveEvaluator(tree);
        Board nextBestBoard = evaluator.findNextBestBoard(board, Symbol.X);
        Board expectedBoard = board.copy();
        expectedBoard.setSymbol(new Position(2, 0), Symbol.X);
        assertThat(nextBestBoard).isEqualTo(expectedBoard);
    }
}
