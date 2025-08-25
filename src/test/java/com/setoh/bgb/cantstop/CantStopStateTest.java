package com.setoh.bgb.cantstop;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.Test;

public class CantStopStateTest {

    @Test 
    public void testCopy() {
        Board board = new Board();
        List<Integer> diceRolls = List.of(3, 4);
        CantStopState.Action action = CantStopState.Action.CHOOSE_COLUMNS;
        CantStopState state = new CantStopState(board, diceRolls, action);

        CantStopState copiedState = state.copy();

        assertThat(copiedState).isNotSameAs(state);
        assertThat(copiedState.board()).isNotSameAs(board);

        assertThat(copiedState.action()).isEqualTo(action);
        assertThat(copiedState.board()).isEqualTo(board);
        assertThat(copiedState.diceRolls()).isEqualTo(diceRolls); 
    }
}
