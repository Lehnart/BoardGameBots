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
        while(!isGameOver()){
            play();
        }
    }

    private void play() {
        AI currentAI;
        if(currentSymbolToPlay == Symbol.X){
            currentAI = xAI;
            currentSymbolToPlay = Symbol.O;
        } else {
            currentAI = oAI;
            currentSymbolToPlay = Symbol.X;
        }
        Position position = currentAI.play(board);
        board.setSymbol(position, currentSymbolToPlay);
    }

    public boolean isGameOver() {
        if(board.isGameFull()){
            return true;
        }
        if(getWinner()!=Symbol.EMPTY){
            return true;
        }
            return false;
    }

    public Symbol getWinner() {
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
