package com.setoh.bgb.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.setoh.bgb.tictactoe.ai.AI;
import com.setoh.bgb.tictactoe.ai.RandomAI;

public class ConfrontationTest {
    @Test
    public void testConfrontation() {
        List<AI> aiList = new ArrayList<>();
        aiList.add(new RandomAI());
        aiList.add(new RandomAI());
        aiList.add(new RandomAI());

        Confrontation confrontation = new Confrontation(aiList, 100);
        List<Confrontation.Result> results = confrontation.run();
        assertThat(results).isNotEmpty().hasSize(6);
        Confrontation.Result result1 = results.get(1);
        Confrontation.Result result2 = results.get(2);
        Confrontation.Result result3 = results.get(4);

        assertThat(result1.firstAIIndex()).isZero();
        assertThat(result1.secondAIIndex()).isEqualTo(1);
        assertThat(result1.firstAIWins() + result1.secondAIWins() + result1.draws()).isEqualTo(100);
        assertThat(result1.firstAIWins()).isGreaterThan(0);
        assertThat(result1.secondAIWins()).isGreaterThan(0);
        assertThat(result1.draws()).isGreaterThan(0);

        assertThat(result2.firstAIIndex()).isZero();
        assertThat(result2.secondAIIndex()).isEqualTo(2);
        assertThat(result2.firstAIWins() + result2.secondAIWins() + result2.draws()).isEqualTo(100);
        assertThat(result2.firstAIWins()).isGreaterThan(0);
        assertThat(result2.secondAIWins()).isGreaterThan(0);
        assertThat(result2.draws()).isGreaterThan(0);

        assertThat(result3.firstAIIndex()).isEqualTo(1);
        assertThat(result3.secondAIIndex()).isEqualTo(2);
        assertThat(result3.firstAIWins() + result3.secondAIWins() + result3.draws()).isEqualTo(100);
        assertThat(result3.firstAIWins()).isGreaterThan(0);
        assertThat(result3.secondAIWins()).isGreaterThan(0);
        assertThat(result3.draws()).isGreaterThan(0);
    }

}
