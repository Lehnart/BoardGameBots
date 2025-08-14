package com.setoh.bgb.tictactoe;

import com.setoh.bgb.tictactoe.Board.Symbol;

public record State(Board board, Symbol symbol) {

    public State copy(){
        return new State(board.copy(), symbol);
    }

}
