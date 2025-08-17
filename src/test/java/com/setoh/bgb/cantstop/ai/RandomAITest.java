package com.setoh.bgb.cantstop.ai;

import org.junit.Test;

import com.setoh.bgb.cantstop.Board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class RandomAITest {

    @Test
    public void testShouldContinue() {
        RandomAI alwaysStoppingAi = new RandomAI(1.001);
        assertThat(alwaysStoppingAi.choseToContinueOrNot(new Board())).isTrue();
        RandomAI alwaysContinuingAi = new RandomAI(0.);
        assertThat(alwaysContinuingAi.choseToContinueOrNot(new Board())).isFalse();
    }

    @Test
    public void testPlays() {
        RandomAI aiPlayer = new RandomAI(0.5);
        List<Integer> columns = aiPlayer.chooseCombination(List.of(List.of(5)));
        assertThat(columns).hasSize(1).containsExactly(5);
    }

    @Test
    public void testPlaysWithEmptyValidCombinations() {
        RandomAI aiPlayer = new RandomAI(0.5);
        List<Integer> columns = aiPlayer.chooseCombination(List.of(List.of()));
        assertThat(columns).isEmpty();
    }
}
