package com.setoh.bgb.tictactoe.ai;

import java.util.Map;

import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Symbol;

public interface Explorator<T> {
    Map<T, Double> explore(Board board, Symbol currentPlayer, Evaluator evaluator);
}
