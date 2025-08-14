package com.setoh.bgb.tictactoe;

import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;
import com.setoh.bgb.tictactoe.ai.AI;

public class Logic {

    private Board board;
    private AI xAI;
    private AI oAI;
    private Symbol currentSymbolToPlay;
    
    public Logic(AI xAI, AI oAI) {
        this.board = new Board();
        this.xAI = xAI;
        this.oAI = oAI;
        this.currentSymbolToPlay = Symbol.X;
    }

    public void run(){
        while(!isGameOver(this.board)){
            play();
        }
    }

    private void play() {
        AI currentAI = currentSymbolToPlay == Symbol.X ? xAI : oAI;
        Position position = currentAI.play(board, currentSymbolToPlay);
        board.setSymbol(position, currentSymbolToPlay);
        currentSymbolToPlay = currentSymbolToPlay == Symbol.X ? Symbol.O : Symbol.X;
    }

    public boolean isGameOver() {
        return Logic.isGameOver(board);
    }
    
    public Symbol getWinner() {
        return Logic.getWinner(board);
    }

    public static boolean isGameOver(Board board) {
        return board.isGameFull() || getWinner(board) != Symbol.EMPTY;
    }

    public static Symbol getWinner(Board board) {
        for (int i = 0; i < 3; i++) {
            if (board.getSymbol(new Position(i, 0)) == board.getSymbol(new Position(i, 1)) &&
                board.getSymbol(new Position(i, 1)) == board.getSymbol(new Position(i, 2)) &&
                board.getSymbol(new Position(i, 0)) != Symbol.EMPTY) {
                return board.getSymbol(new Position(i, 0));
            }
            if (board.getSymbol(new Position(0, i)) == board.getSymbol(new Position(1, i)) &&
                board.getSymbol(new Position(1, i)) == board.getSymbol(new Position(2, i)) &&
                board.getSymbol(new Position(0, i)) != Symbol.EMPTY) {
                return board.getSymbol(new Position(0, i));
            }
        }
        if (board.getSymbol(new Position(0, 0)) == board.getSymbol(new Position(1, 1)) &&
            board.getSymbol(new Position(1, 1)) == board.getSymbol(new Position(2, 2)) &&
            board.getSymbol(new Position(0, 0)) != Symbol.EMPTY) {
            return board.getSymbol(new Position(0, 0));
        }
        if (board.getSymbol(new Position(0, 2)) == board.getSymbol(new Position(1, 1)) &&
            board.getSymbol(new Position(1, 1)) == board.getSymbol(new Position(2, 0)) &&
            board.getSymbol(new Position(0, 2)) != Symbol.EMPTY) {
            return board.getSymbol(new Position(0, 2));
        }
        return Symbol.EMPTY;
    }

    public Board getBoard(){
        return board;
    }
}
