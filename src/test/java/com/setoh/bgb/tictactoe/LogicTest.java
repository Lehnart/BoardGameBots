package com.setoh.bgb.tictactoe;

import org.junit.Test;

import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;
import com.setoh.bgb.tictactoe.ai.RandomAI;

import static org.assertj.core.api.Assertions.assertThat;

public class LogicTest {
    @Test
    public void testConstructorAndRun() {
        Logic logic = new Logic(new RandomAI(), new RandomAI());
        assertThat(logic.isGameOver()).isFalse();
        assertThat(logic.getWinner()).isEqualTo(Symbol.EMPTY);
        logic.run();
        assertThat(logic.isGameOver()).isTrue();
        Symbol winner = logic.getWinner();
        assertThat(winner).isIn(Symbol.X, Symbol.O, Symbol.EMPTY);
    }

    @Test
    public void testBoardFull() {
        Logic logic = new Logic(new RandomAI(), new RandomAI());
        Board board = logic.getBoard();
        board.setSymbol(new Position(0, 0), Symbol.O);
        board.setSymbol(new Position(1, 0), Symbol.O);
        board.setSymbol(new Position(2, 0), Symbol.X);
        board.setSymbol(new Position(0, 1), Symbol.X);
        board.setSymbol(new Position(1, 1), Symbol.X);
        board.setSymbol(new Position(2, 1), Symbol.O);
        board.setSymbol(new Position(0, 2), Symbol.O);
        board.setSymbol(new Position(1, 2), Symbol.X);
        board.setSymbol(new Position(2, 2), Symbol.O);
        assertThat(logic.isGameOver()).isTrue();
    }
    
    @Test
    public void testGetWinner() {
        Logic logic = new Logic(new RandomAI(), new RandomAI());
        Board board = logic.getBoard();
        board.setSymbol(new Position(0, 0), Symbol.O);
        board.setSymbol(new Position(1, 0), Symbol.O);
        board.setSymbol(new Position(2, 0), Symbol.O);
        assertThat(logic.isGameOver()).isTrue();
        assertThat(logic.getWinner()).isEqualTo(Symbol.O);

        Logic logic2 = new Logic(new RandomAI(), new RandomAI());
        Board board2 = logic2.getBoard();
        board2.setSymbol(new Position(0, 0), Symbol.X);
        board2.setSymbol(new Position(0, 1), Symbol.X);
        board2.setSymbol(new Position(0, 2), Symbol.X);
        assertThat(logic.isGameOver()).isTrue();
        assertThat(logic2.getWinner()).isEqualTo(Symbol.X);

        Logic logic3 = new Logic(new RandomAI(), new RandomAI());
        Board board3 = logic3.getBoard();
        board3.setSymbol(new Position(0, 0), Symbol.X);
        board3.setSymbol(new Position(1, 1), Symbol.X);
        board3.setSymbol(new Position(2, 2), Symbol.X);
        assertThat(logic.isGameOver()).isTrue();
        assertThat(logic3.getWinner()).isEqualTo(Symbol.X);

        Logic logic4 = new Logic(new RandomAI(), new RandomAI());
        Board board4 = logic4.getBoard();
        board4.setSymbol(new Position(0, 2), Symbol.X);
        board4.setSymbol(new Position(1, 1), Symbol.X);
        board4.setSymbol(new Position(2, 0), Symbol.X);
        assertThat(logic.isGameOver()).isTrue();
        assertThat(logic4.getWinner()).isEqualTo(Symbol.X);
    }
    
}
