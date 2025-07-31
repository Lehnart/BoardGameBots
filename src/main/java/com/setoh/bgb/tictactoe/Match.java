package com.setoh.bgb.tictactoe;

import java.util.Random;

import com.setoh.bgb.tictactoe.Board.Symbol;
import com.setoh.bgb.tictactoe.ai.AI;

public class Match {

    public static enum Winner{
        FIRST_AI, SECOND_AI, DRAW
    }

    private Random random = new Random();

    public Winner play(AI firstAI, AI secondAI) {
        boolean doesFirstAIStarts = random.nextBoolean();
        Logic logic = doesFirstAIStarts ? new Logic(firstAI, secondAI) : new Logic(secondAI, firstAI);
        logic.run();
        Symbol winner = logic.getWinner();
        switch (winner) {

            case X -> {
                if (doesFirstAIStarts) {
                    return Winner.FIRST_AI;
                } else {
                    return Winner.SECOND_AI;
                }
            }

            case O -> {
                if (doesFirstAIStarts) {
                    return Winner.SECOND_AI;
                } else {
                    return Winner.FIRST_AI;
                }
            }
            
            default -> {
                return Winner.DRAW;
            }
        }
    }
}