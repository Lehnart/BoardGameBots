package com.setoh.bgb.tictactoe;

import java.util.ArrayList;
import java.util.List;

import com.setoh.bgb.tictactoe.ai.AI;

public class Confrontation {

    public static record Result(int firstAIIndex, int secondAIIndex, int firstAIWins, int secondAIWins, int draws) {}

    private List<AI> aiList;
    private int numberOfGames;

    public Confrontation(List<AI> aiList, int numberOfGames) {
        this.aiList = aiList;
        this.numberOfGames = numberOfGames;
    }

    public List<Result> run() {
        List<Result> results = new ArrayList<>();
        for (int i = 0; i < aiList.size(); i++) {
            for (int j = i; j < aiList.size(); j++) {
                Result result = confront(i, j);
                results.add(result);
            }
        }
        return results;
    }

    public Result confront(int firstAIIndex, int secondAIIndex) {
        int firstAIWins = 0;
        int secondAIWins = 0;
        int draws = 0;

        AI firstAI = aiList.get(firstAIIndex);
        AI secondAI = aiList.get(secondAIIndex);
        Match match = new Match();
        for (int i = 0; i < numberOfGames; i++) {
            Match.Winner winner = match.play(firstAI, secondAI);
            switch (winner) {
                case FIRST_AI -> firstAIWins++;
                case SECOND_AI -> secondAIWins++;
                default -> draws++;
            }
        }
        return new Result(firstAIIndex, secondAIIndex, firstAIWins, secondAIWins, draws);
    }
}
