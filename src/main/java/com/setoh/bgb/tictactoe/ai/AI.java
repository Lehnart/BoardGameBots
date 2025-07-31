package com.setoh.bgb.tictactoe.ai;

import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;

public interface AI {

    Position play(Board board, Symbol playerSymbol);

}
