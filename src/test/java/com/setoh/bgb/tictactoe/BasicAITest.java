package com.setoh.bgb.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;

import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;
import com.setoh.bgb.tictactoe.ai.BasicAI;

public class BasicAITest {

    @Test
    public void testPlay() {
        Board board = new Board();
        board.setSymbol(new Position(0, 0), Symbol.X);
        board.setSymbol(new Position(0, 1), Symbol.X);

        BasicAI basicAI = new BasicAI();
        Position move = basicAI.play(board, Symbol.X);

        assertThat(move).isNotNull();
        assertThat(move).isEqualTo(new Position(0, 2));
    }
}
