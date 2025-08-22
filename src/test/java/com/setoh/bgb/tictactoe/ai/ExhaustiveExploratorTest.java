package com.setoh.bgb.tictactoe.ai;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import com.setoh.bgb.ai.GameTreeExplorator;
import com.setoh.bgb.ai.GameTree;
import com.setoh.bgb.ai.GameTree.Node;
import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.TicTacToeState;
import com.setoh.bgb.tictactoe.Board.Symbol;

public class ExhaustiveExploratorTest {
    @Test
    public void testExplore() {
        Board board = new Board();
        board.setSymbol(new Board.Position(0, 0), Symbol.X);
        board.setSymbol(new Board.Position(0, 1), Symbol.O);
        board.setSymbol(new Board.Position(0, 2), Symbol.X);

        board.setSymbol(new Board.Position(1, 0), Symbol.O);
        board.setSymbol(new Board.Position(1, 1), Symbol.O);
        board.setSymbol(new Board.Position(1, 2), Symbol.X);

        board.setSymbol(new Board.Position(2, 0), Symbol.O);
        
        ExhaustiveExplorator explorator = new ExhaustiveExplorator();
        GameTree<TicTacToeState> tree = explorator.explore(new TicTacToeState(board, Symbol.X));
        assertThat(tree).isNotNull();

        Board winnningBoard = board.copy();
        winnningBoard.setSymbol(new Board.Position(2, 2), Symbol.X);
        assertThat(tree.isIn(new Node<TicTacToeState>(new TicTacToeState(winnningBoard, Symbol.O)))).isTrue();

        Board impossibleBoard = winnningBoard.copy();
        impossibleBoard.setSymbol(new Board.Position(2, 1), Symbol.O);
        assertThat(tree.isIn(new Node<TicTacToeState>(new TicTacToeState(winnningBoard, Symbol.X)))).isFalse();
    }
}
