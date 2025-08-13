package com.setoh.bgb.tictactoe.ai;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.data.Offset;
import org.junit.Test;

import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;
import com.setoh.bgb.tictactoe.ai.BasicEvaluator;

public class BasicEvaluatorTest {
    @Test
    public void testEvaluate() {
        Board board = new Board();
        BasicEvaluator evaluator = new BasicEvaluator();
        assertThat(evaluator.evaluate(board, Symbol.X)).isCloseTo(0.5, Offset.offset(0.001));

        board = new Board();
        board.setSymbol(new Position(0, 0), Symbol.X);
        board.setSymbol(new Position(0, 1), Symbol.X);
        board.setSymbol(new Position(0, 2), Symbol.X);
        assertThat(evaluator.evaluate(board, Symbol.X)).isCloseTo(1., Offset.offset(0.001));


        board = new Board();
        board.setSymbol(new Position(0, 0), Symbol.O);
        board.setSymbol(new Position(0, 1), Symbol.O);
        board.setSymbol(new Position(0, 2), Symbol.O);
        assertThat(evaluator.evaluate(board, Symbol.X)).isCloseTo(0., Offset.offset(0.001));

    }
}
