package com.setoh.bgb.cantstop.ai;

import java.util.List;
import java.util.Random;

import com.setoh.bgb.cantstop.Board;

public class RandomAI extends AI {

    private Random random = new Random();
    private final double continuingProbability;

    public RandomAI(double probability) {
        continuingProbability = probability;
    }

    @Override
    public List<Integer> chooseCombination(List<List<Integer>> columnsToProgress) {
        if (columnsToProgress.isEmpty()) {
            return List.of();
        }
        return columnsToProgress.get(random.nextInt(columnsToProgress.size()));
    }

    @Override
    public boolean choseToContinueOrNot(Board state) {
        return random.nextDouble() < continuingProbability;
    }
}

