package com.setoh.bgb.cantstop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;

import com.setoh.bgb.cantstop.Logic.DiceCombination;
import com.setoh.bgb.cantstop.ai.RandomAI;

public class LogicTest {

    @Test
    public void testColumnToProgressWhenAllCombinationsAreValid() {
        Board state = new Board();
        List<List<Integer>> combinations = Logic.getColumnsToProgress(List.of(1, 2, 3, 4), state);
        assertThat(combinations).hasSize(3);
        assertThat(combinations.get(0)).hasSize(2);
        assertThat(combinations.get(0)).containsExactly(3, 7);
        assertThat(combinations.get(1)).hasSize(2);
        assertThat(combinations.get(1)).containsExactly(4, 6);
        assertThat(combinations.get(2)).hasSize(2);
        assertThat(combinations.get(2)).containsExactly(5, 5);
    }

    @Test
    public void testColumnToProgressWhenNoCombinationIsValid() {
        Board state = new Board();
        state.temporaryProgress(2);
        state.temporaryProgress(2);
        state.temporaryProgress(2);
        state.temporaryProgress(2);
        state.progress();
        List<List<Integer>> combinations = Logic.getColumnsToProgress(List.of(1, 1, 1, 1), state);
        assertThat(combinations).isEmpty();
    }

    @Test
    public void testColumnToProgressWhenOnlyOnePairIsValid() {
        Board state = new Board();
        state.temporaryProgress(2);
        state.temporaryProgress(2);
        state.temporaryProgress(2);
        state.temporaryProgress(2);
        state.temporaryProgress(12);
        state.temporaryProgress(12);
        state.temporaryProgress(12);
        state.temporaryProgress(12);
        List<List<Integer>> combinations = Logic.getColumnsToProgress(List.of(1, 2, 3, 4), state);
        assertThat(combinations).hasSize(5);
        assertThat(combinations.get(0)).containsExactly(3);
        assertThat(combinations.get(1)).containsExactly(7);
        assertThat(combinations.get(2)).containsExactly(4);
        assertThat(combinations.get(3)).containsExactly(6);
        assertThat(combinations.get(4)).containsExactly(5, 5);
    }

    @Test
    public void testRollDice() {
        assertThat(Logic.rollDice(6)).isPositive().isLessThanOrEqualTo(6);
    }

    @Test
    public void testRollDices() {
        assertThat(Logic.rollDices(6)).hasSize(4);
    }

    @Test
    public void testCanProgressOnColumnsWhenColumnClaimed() {
        Board state = new Board();
        state.temporaryProgress(2);
        state.temporaryProgress(2);
        state.temporaryProgress(2);
        state.temporaryProgress(2);
        state.progress();

        assertThat(Logic.canProgressOnColumns(List.of(2), state)).isFalse();
    }

    @Test
    public void testCanProgressOnColumnsWhenColumnIsAlreadyProgressing() {
        Board state = new Board();
        state.temporaryProgress(2);
        state.temporaryProgress(2);
        state.temporaryProgress(2);
        state.temporaryProgress(2);
        state.temporaryProgress(5);
        assertThat(Logic.canProgressOnColumns(List.of(2), state)).isFalse();
        assertThat(Logic.canProgressOnColumns(List.of(5), state)).isTrue();
    }

    @Test
    public void testCanProgressOnColumnWhenColumnIsNotAlreadyProgressing() {
        Board state = new Board();
        state.temporaryProgress(2);
        state.temporaryProgress(5);
        assertThat(Logic.canProgressOnColumns(List.of(6), state)).isTrue();
    }

    @Test
    public void testCanProgressOnColumnsWhenColumnIsNotAlreadyProgressingAnd3ColumnsAreProgressing() {
        Board state = new Board();
        state.temporaryProgress(2);
        state.temporaryProgress(5);
        state.temporaryProgress(7);
        assertThat(Logic.canProgressOnColumns(List.of(6), state)).isFalse();
    }

    @Test
    public void testGetColumnsToProgress() {
        Board state = new Board();
        state.temporaryProgress(2);
        state.temporaryProgress(5);
        state.temporaryProgress(7);
        assertThat(Logic.getColumnsToProgress(new DiceCombination(1, 2, 2, 2), state)).isEmpty();
        assertThat(Logic.getColumnsToProgress(new DiceCombination(1, 2, 2, 3), state)).isNotEmpty();
        assertThat(Logic.getColumnsToProgress(new DiceCombination(1, 1, 2, 2), state)).isNotEmpty();
        assertThat(Logic.getColumnsToProgress(new DiceCombination(1, 1, 2, 3), state)).isNotEmpty();
    }

    @Test
    public void testDiceCombination() {
        DiceCombination diceCombination = new DiceCombination(1, 2, 3, 4);
        assertThat(diceCombination.dice1()).isEqualTo(1);
        assertThat(diceCombination.dice2()).isEqualTo(2);
        assertThat(diceCombination.dice3()).isEqualTo(3);
        assertThat(diceCombination.dice4()).isEqualTo(4);
        assertThat(diceCombination.getFirstSum()).isEqualTo(3);
        assertThat(diceCombination.getSecondSum()).isEqualTo(7);
    }

    @Test
    public void testPlayTurnAlwaysFail() {
        Board state = new Board();
        Logic logic = new Logic(new RandomAI(1.));
        logic.playTurn(state);
        for (int column : state.columns()) {
            assertThat(state.getPlayerHeight(column)).isZero();
        }
    }

    @Test
    public void testIsGameOver() {
        Board state = new Board();

        assertThat(Logic.isGameOver(state)).isFalse();

        state.temporaryProgress(2);
        state.temporaryProgress(2);
        state.temporaryProgress(2);
        
        state.temporaryProgress(3);
        state.temporaryProgress(3);
        state.temporaryProgress(3);
        state.temporaryProgress(3);
        state.temporaryProgress(3);
        
        state.temporaryProgress(12);
        state.temporaryProgress(12);
        state.temporaryProgress(12);
        
        state.progress();
        assertThat(Logic.isGameOver(state)).isTrue();
    }

    @Test  
    public void testPlayGame() {
        Logic logic = new Logic(new RandomAI(0.));
        try (var mocked = Mockito.mockStatic(Logic.class)) {
            mocked.when(() -> Logic.isGameOver(Mockito.any(Board.class))).thenReturn(true);
            assertThat(logic.playGame()).isEqualTo(new Board());
        }
    }

    @Test
    public void testPlayTurnAlwaysStop() {
        Board state = new Board();
        Logic logic = new Logic(new RandomAI(0.));
        logic.playTurn(state);
        assertThat(state.columns().stream().map(state::getPlayerHeight).anyMatch(h -> h > 0)).isTrue();
    }

}