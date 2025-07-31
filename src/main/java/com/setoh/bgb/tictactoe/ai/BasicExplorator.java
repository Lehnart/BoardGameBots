package com.setoh.bgb.tictactoe.ai;

import java.util.HashMap;
import java.util.Map;

import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Symbol;

public class BasicExplorator implements Explorator<Board.Position> {

    @Override
    public Map<Board.Position, Double> explore(Board board, Symbol currentPlayer, Evaluator evaluator) {
        Map<Board.Position, Double> scores = new HashMap<>();
        for(Board.Position position : board.getEmptyPositions()) {
            Board newBoard = new Board(board);
            newBoard.setSymbol(position, currentPlayer);
            Double score = evaluator.evaluate(newBoard, currentPlayer);
            scores.put(position, score);
        }
        return scores;
    }

}
