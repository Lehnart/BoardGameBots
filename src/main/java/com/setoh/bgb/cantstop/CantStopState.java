package com.setoh.bgb.cantstop;

import java.util.List;

public record CantStopState (Board board, List<Integer> diceRolls, Action action) {
    
    public static enum Action{
        ROLL_DICE,
        CHOOSE_COLUMNS,
        DECIDE_TO_CONTINUE_OR_NOT
    } 

    public CantStopState copy(){
        return new CantStopState(board.copy(), List.copyOf(diceRolls), action);
    }

}
