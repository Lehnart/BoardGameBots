package com.setoh.bgb.tictactoe.ai;

import java.util.Map;

import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;

public class BasicAI implements AI {

    private BasicExplorator explorator = new BasicExplorator();
    private Evaluator evaluator = new BasicEvaluator();

    public Position play(Board board, Symbol playerSymbol) {
        Map<Position, Double> results = explorator.explore(board, playerSymbol, evaluator);
        return results.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

}
