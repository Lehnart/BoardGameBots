package com.setoh.bgb.tictactoe;

import com.setoh.bgb.tictactoe.Board.Symbol;

public record TicTacToeState(Board board, Symbol symbol) {

    public TicTacToeState copy(){
        return new TicTacToeState(board.copy(), symbol);
    }

}
