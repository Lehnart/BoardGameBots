package com.setoh.bgb.cantstop.ai;

import java.util.List;

import com.setoh.bgb.ai.AleatoryGameTree;
import com.setoh.bgb.ai.AleatoryGameTree.AleatoryTransition;
import com.setoh.bgb.ai.AleatoryGameTree.Choice;
import com.setoh.bgb.ai.AleatoryGameTreeExplorator;
import com.setoh.bgb.cantstop.Board;
import com.setoh.bgb.cantstop.CantStopState;
import com.setoh.bgb.cantstop.Logic;

public class ExhaustiveExplorator extends AleatoryGameTreeExplorator<CantStopState> {

    public static record DiceRollProbability(List<Integer> diceRolls, double probability) {
    }

    public static final List<DiceRollProbability> twoFaceDiceProbabilityDistribution = List.of(
            new DiceRollProbability(List.of(1, 1, 1, 1), 0.5 * 0.5 * 0.5 * 0.5),
            new DiceRollProbability(List.of(1, 1, 1, 2), 0.5 * 0.5 * 0.5 * 0.5 * 4),
            new DiceRollProbability(List.of(1, 1, 2, 2), 0.5 * 0.5 * 0.5 * 0.5 * 6),
            new DiceRollProbability(List.of(1, 2, 2, 2), 0.5 * 0.5 * 0.5 * 0.5 * 4),
            new DiceRollProbability(List.of(2, 2, 2, 2), 0.5 * 0.5 * 0.5 * 0.5)

    );

    private final List<DiceRollProbability> diceRollProbabilities; 

    public ExhaustiveExplorator() {
        this(twoFaceDiceProbabilityDistribution);
    }

    public ExhaustiveExplorator(List<DiceRollProbability> diceRollProbabilities) {
        super();
        this.diceRollProbabilities = diceRollProbabilities;
    }


    @Override
    protected void exploreState(AleatoryGameTree<CantStopState> gameTree, CantStopState state) {
        Board currentBoard = state.board();
        if (!Logic.isGameOver(currentBoard)) {
            addNextPositionsToExplore(gameTree, state);
        }
    }

    void addNextPositionsToExplore(AleatoryGameTree<CantStopState> gameTree, CantStopState state) {
        Board board = state.board();
        List<Integer> diceRolls = state.diceRolls();
        CantStopState.Action action = state.action();
        if (action == CantStopState.Action.ROLL_DICE) {
            for(DiceRollProbability drp : diceRollProbabilities){
                CantStopState nextState = new CantStopState(board.copy(), drp.diceRolls(), CantStopState.Action.CHOOSE_COLUMNS);
                gameTree.addState(nextState);
                gameTree.addTransition(state, new AleatoryTransition<>(state, nextState, drp.probability));
                nextStatesToExplore.add(nextState);
            }
        }
        else if (action == CantStopState.Action.CHOOSE_COLUMNS) {
            List<List<Integer>> columnChoices = Logic.getColumnsToProgress(diceRolls, board);
            if (columnChoices.isEmpty()){
                Board nextBoard = board.copy();
                nextBoard.failToProgress();
                CantStopState nextState = new CantStopState(nextBoard, null, CantStopState.Action.ROLL_DICE);
                gameTree.addState(nextState);
                gameTree.addTransition(state, new Choice<>(state, nextState));
                nextStatesToExplore.add(nextState);
            }
            else {
                for(List<Integer> columns: columnChoices){
                    Board nextBoard = board.copy();
                    columns.forEach(nextBoard::temporaryProgress);
                    CantStopState nextState = new CantStopState(nextBoard, null, CantStopState.Action.DECIDE_TO_CONTINUE_OR_NOT);
                    gameTree.addState(nextState);
                    gameTree.addTransition(state, new Choice<>(state, nextState));
                    nextStatesToExplore.add(nextState);
                }
            }
        }
        else if(action == CantStopState.Action.DECIDE_TO_CONTINUE_OR_NOT){
            Board nextBoardStop = board.copy();
            nextBoardStop.progress();
            CantStopState nextStateStop = new CantStopState(nextBoardStop, null, CantStopState.Action.ROLL_DICE);
            gameTree.addState(nextStateStop);
            gameTree.addTransition(state, new Choice<>(state, nextStateStop));
            nextStatesToExplore.add(nextStateStop);

            CantStopState nextStateContinue = new CantStopState(board.copy(), null, CantStopState.Action.ROLL_DICE);
            gameTree.addState(nextStateContinue);
            gameTree.addTransition(state, new Choice<>(state, nextStateContinue));
            nextStatesToExplore.add(nextStateContinue);
        }
    }
}
