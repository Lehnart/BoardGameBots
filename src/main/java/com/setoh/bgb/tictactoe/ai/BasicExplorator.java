package com.setoh.bgb.tictactoe.ai;

import java.util.HashMap;
import java.util.Map;

import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Symbol;

public class BasicExplorator implements Explorator<Board.Position> {

    @Override
    public Map<Board.Position, Double> explore(Board board, Symbol currentPlayer, Evaluator evaluator) {
        Map<Board.Position, Double> scores = new HashMap<Board.Position, Double>();
        for(Board.Position position : board.getEmptyPositions()) {
            Double score = evaluator.evaluate(board, currentPlayer);
            scores.put(position, score);
        }
        return scores;
    }

}
