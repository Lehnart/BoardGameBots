package com.setoh.bgb.tictactoe.ai;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;

import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;

public class BasicAITest {

    @Test
    public void testPlay() {
        Board board = new Board();
        board.setSymbol(new Position(0, 0), Symbol.X);
        board.setSymbol(new Position(0, 1), Symbol.X);

        BasicAI basicAI = new BasicAI();
        Position move = basicAI.play(board, Symbol.X);

        assertThat(move).isNotNull().isEqualTo(new Position(0, 2));
    }
}
