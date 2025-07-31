package com.setoh.bgb.tictactoe.ai;

import java.util.List;
import java.util.Random;

import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;

public class RandomAI implements AI {

    private Random random = new Random(); 

    public Position play(Board board, Symbol playerSymbol) {
        List<Position> emptyPositions = board.getEmptyPositions();
        int randomPositionIndex = random.nextInt(emptyPositions.size());
        return emptyPositions.get(randomPositionIndex);
    }

}
