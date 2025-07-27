package com.setoh.bgb.tictactoe.ai;

import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Symbol;

public interface Evaluator {

    double evaluate(Board board, Symbol currentPlayer);

}
