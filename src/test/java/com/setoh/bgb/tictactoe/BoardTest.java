package com.setoh.bgb.tictactoe;

import org.junit.Test;

import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

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
    public void testConstructorWithBoard() {
        Board board = new Board();
        board.setSymbol(new Position(0, 0), Symbol.X);
        board.setSymbol(new Position(0, 1), Symbol.O);
        board.setSymbol(new Position(0, 2), Symbol.EMPTY);
        board.setSymbol(new Position(1, 0), Symbol.EMPTY);
        board.setSymbol(new Position(1, 1), Symbol.X);
        board.setSymbol(new Position(1, 2), Symbol.O);
        board.setSymbol(new Position(2, 0), Symbol.O);
        board.setSymbol(new Position(2, 1), Symbol.EMPTY);
        board.setSymbol(new Position(2, 2), Symbol.X);

        Board oboard = new Board(board);
        assertThat(oboard.getSymbol(new Position(0, 0))).isEqualTo(Symbol.X);
        assertThat(oboard.getSymbol(new Position(0, 1))).isEqualTo(Symbol.O);
        assertThat(oboard.getSymbol(new Position(0, 2))).isEqualTo(Symbol.EMPTY);
        assertThat(oboard.getSymbol(new Position(1, 0))).isEqualTo(Symbol.EMPTY);
        assertThat(oboard.getSymbol(new Position(1, 1))).isEqualTo(Symbol.X);
        assertThat(oboard.getSymbol(new Position(1, 2))).isEqualTo(Symbol.O);
        assertThat(oboard.getSymbol(new Position(2, 0))).isEqualTo(Symbol.O);
        assertThat(oboard.getSymbol(new Position(2, 1))).isEqualTo(Symbol.EMPTY);
        assertThat(oboard.getSymbol(new Position(2, 2))).isEqualTo(Symbol.X);
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

    @Test
    public void testGetLines() {
        Board board = new Board();
        board.setSymbol(new Position(0, 0), Symbol.X);
        board.setSymbol(new Position(0, 1), Symbol.O);
        board.setSymbol(new Position(0, 2), Symbol.EMPTY);
        board.setSymbol(new Position(1, 0), Symbol.EMPTY);
        board.setSymbol(new Position(1, 1), Symbol.X);
        board.setSymbol(new Position(1, 2), Symbol.O);
        board.setSymbol(new Position(2, 0), Symbol.O);
        board.setSymbol(new Position(2, 1), Symbol.EMPTY);
        board.setSymbol(new Position(2, 2), Symbol.X);

        List<Symbol[]> lines = board.getLines();
        lines.forEach(line -> {
            assertThat(line).hasSize(3);
        });
        assertThat(lines.get(0)).containsExactly(Symbol.X, Symbol.O, Symbol.EMPTY);
        assertThat(lines.get(1)).containsExactly(Symbol.EMPTY, Symbol.X, Symbol.O);
        assertThat(lines.get(2)).containsExactly(Symbol.O, Symbol.EMPTY, Symbol.X);     

        assertThat(lines.get(3)).containsExactly(Symbol.X, Symbol.EMPTY, Symbol.O);
        assertThat(lines.get(4)).containsExactly(Symbol.O, Symbol.X, Symbol.EMPTY);
        assertThat(lines.get(5)).containsExactly(Symbol.EMPTY, Symbol.O, Symbol.X);     
 
        assertThat(lines.get(6)).containsExactly(Symbol.X, Symbol.X, Symbol.X);
        assertThat(lines.get(7)).containsExactly(Symbol.EMPTY, Symbol.X, Symbol.O);     
 
    }

    @Test
    public void testEqualsAndHashcode() {
        Board board1 = new Board();
        Board board2 = new Board();
        Board board3 = new Board();
        board3.setSymbol(new Position(0, 0), Symbol.X);

        assertThat(board1).isEqualTo(board2);
        assertThat(board1.hashCode()).isEqualTo(board2.hashCode());

        assertThat(board1).isNotEqualTo(board3);
        assertThat(board2).isNotEqualTo(board3);

        assertThat(board1.hashCode()).isNotEqualTo(board3.hashCode());
        assertThat(board2.hashCode()).isNotEqualTo(board3.hashCode());  

        board2.setSymbol(new Position(0, 0), Symbol.X);
        assertThat(board3).isEqualTo(board2);
        assertThat(board3.hashCode()).isEqualTo(board2.hashCode());
    }
}
