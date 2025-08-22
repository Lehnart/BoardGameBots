package com.setoh.bgb.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class StateTest {
    
    @Test
    public void testStateCopy() {
        Board board = new Board();
        board.setSymbol(new Board.Position(0, 0), Board.Symbol.X);
        TicTacToeState originalState = new TicTacToeState(board, Board.Symbol.X);
        TicTacToeState copiedState = originalState.copy();

        assertThat(copiedState.board()).isEqualTo(originalState.board());
        assertThat(copiedState.symbol()).isEqualTo(originalState.symbol());
        assertThat(copiedState).isNotSameAs(originalState);
    }
}
