package com.setoh.bgb;

import com.setoh.bgb.tictactoe.Logic;
import com.setoh.bgb.tictactoe.ai.BasicAI;
import com.setoh.bgb.tictactoe.ai.RandomAI;

public class Main {
    public static void main(String[] args) {
        RandomAI randomAI = new RandomAI();
        BasicAI basicAI = new BasicAI();
        for(int i = 0; i < 100; i++) {
            Logic logic = new Logic(basicAI, randomAI);
            logic.run();
        }
    }    
}
