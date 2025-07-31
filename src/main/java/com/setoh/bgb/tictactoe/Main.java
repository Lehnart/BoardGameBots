package com.setoh.bgb.tictactoe;

import com.setoh.bgb.tictactoe.ai.AI;
import com.setoh.bgb.tictactoe.ai.BasicAI;
import com.setoh.bgb.tictactoe.ai.RandomAI;

public class Main {

    public static void main(String[] args) {
        AI basicAi = new BasicAI();
        AI randomAI = new RandomAI();
        Match match = new Match();
        match.play(basicAi, randomAI);
    }
}
