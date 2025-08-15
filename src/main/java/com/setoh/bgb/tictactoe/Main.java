package com.setoh.bgb.tictactoe;

import java.util.List;

import com.setoh.bgb.tictactoe.Confrontation.Result;
import com.setoh.bgb.tictactoe.ai.AI;
import com.setoh.bgb.tictactoe.ai.BasicAI;
import com.setoh.bgb.tictactoe.ai.ExhaustiveAI;
import com.setoh.bgb.tictactoe.ai.RandomAI;

public class Main {

    public static void main(String[] args) {
        AI basicAi = new BasicAI();
        AI randomAI = new RandomAI();
        AI exhaustiveAI = new ExhaustiveAI();
        Confrontation confrontation = new Confrontation(List.of(randomAI, basicAi, exhaustiveAI), 100);
        List<Result> resulst = confrontation.run();
        for (Result result : resulst) {
            System.out.printf("AI %d vs AI %d: %d wins, %d wins, %d draws%n",
                    result.firstAIIndex(),
                    result.secondAIIndex(), 
                    result.firstAIWins(),
                    result.secondAIWins(),
                    result.draws());
        }   
    }
}
