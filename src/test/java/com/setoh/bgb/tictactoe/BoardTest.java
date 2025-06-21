package com.setoh.bgb.tictactoe;

import org.junit.Test;

import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {
    @Test
    public void testConstructor() {
        Board board = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertThat(board.getSymbol(new Position(i, j))).isEqualTo(Symbol.EMPTY);
            }
        }
    }

    @Test
    public void testPosition() {
        assertThatThrownBy(() -> new Position(-1, 0))
            .isInstanceOf(IndexOutOfBoundsException.class)
            .hasMessage("Row and column must be between 0 and 2.");
        assertThatThrownBy(() -> new Position(0, -1))
            .isInstanceOf(IndexOutOfBoundsException.class)
            .hasMessage("Row and column must be between 0 and 2.");
        assertThatThrownBy(() -> new Position(3, 0))
            .isInstanceOf(IndexOutOfBoundsException.class)
            .hasMessage("Row and column must be between 0 and 2.");
        assertThatThrownBy(() -> new Position(0, 3))
            .isInstanceOf(IndexOutOfBoundsException.class)
            .hasMessage("Row and column must be between 0 and 2.");
    }

    @Test 
    public void testSetSymbol() {
        Board board = new Board();
        Position pos = new Position(1, 1);
        board.setSymbol(pos, Symbol.X);
        assertThat(board.getSymbol(pos)).isEqualTo(Symbol.X);

        assertThatThrownBy(() -> board.setSymbol(pos, Symbol.O))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Cell is already occupied.");
    }

    @Test
    public void testIsGameFull() {
        Board board = new Board();
        assertThat(board.isGameFull()).isFalse();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.setSymbol(new Position(i, j), Symbol.X);
            }
        }
        assertThat(board.isGameFull()).isTrue();
    }
}
