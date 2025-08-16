package com.setoh.bgb.tictactoe.ai;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;

import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;

public class ExhaustiveAITest {

    @Test
    public void testPlay() {
        Board board = new Board();
        board.setSymbol(new Position(0, 0), Symbol.X);
        board.setSymbol(new Position(0, 1), Symbol.X);
        board.setSymbol(new Position(1, 2), Symbol.O);
        board.setSymbol(new Position(2, 2), Symbol.O);

        ExhaustiveAI exhaustiveAI = new ExhaustiveAI();
        Position move = exhaustiveAI.play(board, Symbol.X);

        assertThat(move).isNotNull().isEqualTo(new Position(0, 2));
    }
}
