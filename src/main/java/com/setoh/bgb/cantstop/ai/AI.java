package com.setoh.bgb.cantstop.ai;

import java.util.List;

import com.setoh.bgb.cantstop.Board;

public abstract class AI {
    public abstract List<Integer> chooseCombination(List<List<Integer>> columnsToProgress);

    public abstract boolean choseToContinueOrNot(Board state);
}