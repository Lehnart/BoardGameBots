package com.setoh.bgb.cantstop.ai;

import org.junit.Test;

import com.setoh.bgb.cantstop.Board;
import com.setoh.bgb.cantstop.CantStopState;
import com.setoh.bgb.cantstop.ai.ExhaustiveExplorator.DiceRollProbability;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

public class ExhaustiveExploratorTest {

    @Test
    public void testExplore() {
        Map<Integer, Integer> heigths = Map.of(
                2, 3,
                3, 5,
                4, 7,
                5, 9,
                6, 7,
                7, 5,
                8, 3
            );

        Board board = new Board(heigths);
        board.temporaryProgress(2);
        board.temporaryProgress(2);

        board.temporaryProgress(3);
        board.temporaryProgress(3);
        board.temporaryProgress(3);
        board.temporaryProgress(3);
        board.temporaryProgress(3);

        board.temporaryProgress(4);
        board.temporaryProgress(4);
        board.temporaryProgress(4);
        board.temporaryProgress(4);
        board.temporaryProgress(4);
        board.temporaryProgress(4);

        board.temporaryProgress(5);
        board.temporaryProgress(5);
        board.temporaryProgress(5);
        board.temporaryProgress(5);
        board.temporaryProgress(5);
        board.temporaryProgress(5);
        board.temporaryProgress(5);
        board.temporaryProgress(5);

        board.temporaryProgress(6);
        board.temporaryProgress(6);
        board.temporaryProgress(6);
        board.temporaryProgress(6);
        board.temporaryProgress(6);
        board.temporaryProgress(6);

        board.temporaryProgress(7);
        board.temporaryProgress(7);
        board.temporaryProgress(7);
        board.temporaryProgress(7);

        board.temporaryProgress(8);
        board.temporaryProgress(8);
        board.temporaryProgress(8);        

        board.progress();
        CantStopState initialState = new CantStopState(board, null, CantStopState.Action.ROLL_DICE);

        List<DiceRollProbability> testDiceProbabilityDistribution = List.of(
            new DiceRollProbability(List.of(1, 1, 1, 1), 0.5 * 0.5 * 0.5 * 0.5)
        );

        ExhaustiveExplorator explorator = new ExhaustiveExplorator(testDiceProbabilityDistribution);
        var gameTree = explorator.explore(initialState);
        assertThat(gameTree.getTransitions(initialState)).hasSize(1);
    }
}
