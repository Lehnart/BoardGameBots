package com.setoh.bgb.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;
import com.setoh.bgb.tictactoe.Match.Winner;
import com.setoh.bgb.tictactoe.ai.AI;

public class MatchTest {

    private static class PresetMoveAI implements AI {
        private Position[] moves;
        private int moveIndex = 0;

        public PresetMoveAI(Position... moves) {
            this.moves = moves;
        }

        @Override
        public Position play(Board board, Symbol playerSymbol) {
            if (moveIndex < moves.length) {
                return moves[moveIndex++];
            }
            return board.getEmptyPositions().get(0);
        }
    }

    @Test
    public void testMatchWithDraw() {
        AI drawAI1 = new PresetMoveAI(
            new Position(2, 1),
            new Position(0, 0), 
            new Position(0, 2), 
            new Position(1, 1), 
            new Position(1, 0)
        );
        AI drawAI2 = new PresetMoveAI(
            new Position(0, 1),
            new Position(1, 2),
            new Position(2, 0),
            new Position(2, 2)

        );
        Match match = new Match();
        Winner winner = match.play(drawAI1, drawAI2);
        assertThat(winner).isEqualTo(Match.Winner.DRAW);
    }

    @Test
    public void testMatchWithFirstAIWinning() {
        for (int i = 0; i < 10; i++) {
            AI winnerAI = new PresetMoveAI(
                new Position(0, 0),
                new Position(0, 1), 
                new Position(0, 2)
            );
            AI loserAI = new PresetMoveAI(
                new Position(1, 0),
                new Position(1, 1),
                new Position(2, 2) 
            );
        
            Match match = new Match();
            Winner winner = match.play(winnerAI, loserAI);
            assertThat(winner).isEqualTo(Match.Winner.FIRST_AI);
        }
    }

    @Test
    public void testMatchWithSecondAIWinning() {
        for (int i = 0; i < 10; i++) {
            AI winnerAI = new PresetMoveAI(
                new Position(0, 0),
                new Position(0, 1), 
                new Position(0, 2)
            );
            AI loserAI = new PresetMoveAI(
                new Position(1, 0),
                new Position(1, 1),
                new Position(2, 2) 
            );
            Match match = new Match();
            Winner winner = match.play(loserAI, winnerAI);
            assertThat(winner).isEqualTo(Match.Winner.SECOND_AI);
        }
    }

}
