package com.setoh.bgb.tictactoe.ai;

import java.util.List;

import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Symbol;

public class BasicEvaluator implements Evaluator {

    record SymbolCount(int emptyCount, int currentPlayerCount, int opponentCount){}

    public SymbolCount countSymbols(Symbol[] line, Symbol currentPlayer) {
        int emptyCount = 0;
        int currentPlayerCount = 0;
        int opponentCount = 0;
        for (Symbol symbol : line) {
            if (symbol == Symbol.EMPTY) {
                emptyCount++;
            } else if (symbol == currentPlayer) {
                currentPlayerCount++;
            } else {
                opponentCount++;
            }
        }
        return new SymbolCount(emptyCount, currentPlayerCount, opponentCount);
    }

    @Override
    public double evaluate(Board board, Symbol currentPlayer) {
        List<Symbol[]> lines = board.getLines();
        for (Symbol[] line : lines) {
            SymbolCount lineCount = countSymbols(line, currentPlayer);
            if (lineCount.currentPlayerCount == 3) {
                return 1.;
            } else if (lineCount.opponentCount == 3) {
                return 0.;
            }
        }
        return 0.5;
    }
}
