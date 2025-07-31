package com.setoh.bgb.tictactoe;

import java.util.Map;

import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;
import com.setoh.bgb.tictactoe.ai.BasicEvaluator;
import com.setoh.bgb.tictactoe.ai.BasicExplorator;

public class BasicExploratorTest {
    @Test
    public void testExplore() {
        Board board = new Board();
        board.setSymbol(new Position(0, 0), Symbol.X);
        board.setSymbol(new Position(0, 1), Symbol.X);

        BasicExplorator explorator = new BasicExplorator();
        Map<Position, Double> results = explorator.explore(board, Symbol.X, new BasicEvaluator());
        assertThat(results).containsOnlyKeys(
            new Position(0, 2), 
            new Position(1, 0),
            new Position(1, 1),
            new Position(1, 2),
            new Position(2, 0),
            new Position(2, 1),
            new Position(2, 2)
        );
        assertThat(results.get(new Position(0, 2))).isCloseTo(1., Offset.offset(0.001));
        assertThat(results.get(new Position(1, 0))).isCloseTo(0.5, Offset.offset(0.001));
        assertThat(results.get(new Position(1, 1))).isCloseTo(0.5, Offset.offset(0.001));
        assertThat(results.get(new Position(1, 2))).isCloseTo(0.5, Offset.offset(0.001));
        assertThat(results.get(new Position(2, 0))).isCloseTo(0.5, Offset.offset(0.001));
        assertThat(results.get(new Position(2, 1))).isCloseTo(0.5, Offset.offset(0.001));
        assertThat(results.get(new Position(2, 2))).isCloseTo(0.5, Offset.offset(0.001));

    }
}
