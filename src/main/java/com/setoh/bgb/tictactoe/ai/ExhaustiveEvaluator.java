package com.setoh.bgb.tictactoe.ai;

import java.util.List;

import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Symbol;
import com.setoh.bgb.tictactoe.GameTree;
import com.setoh.bgb.tictactoe.Logic;
import com.setoh.bgb.tictactoe.GameTree.Node;
import com.setoh.bgb.tictactoe.State;

public class ExhaustiveEvaluator {

    private final GameTree gameTree;

    public ExhaustiveEvaluator(GameTree gameTree) {
        this.gameTree = gameTree;
    }

    public Board findNextBestBoard(Board board, Symbol currentPlayer) {
        State initialState = new State(board, currentPlayer);
        List<Node> children = gameTree.getChildren(new Node(initialState));

        Node bestNode = children.get(0);
        double bestEvaluation = evaluateNodeMax(bestNode, currentPlayer);
        for (Node child : children) {
            double evaluation = evaluateNodeMax(child, currentPlayer);
            if (evaluation > bestEvaluation) {
                bestEvaluation = evaluation;
                bestNode = child;
            }
        }
        return bestNode.state().board().copy();
    }

    private double evaluateMax(List<Node> children, Symbol currentPlayer) {
        double maxEvaluation = Double.NEGATIVE_INFINITY;
        for (Node child : children) {
            double evaluation = evaluateNodeMax(child, currentPlayer);
            if (evaluation>maxEvaluation){
                maxEvaluation = evaluation;
            }
        }
        return maxEvaluation;
    }

    private double evaluateNodeMax(Node node, Symbol currentPlayer) {
        State state = node.state();
        Board board = state.board();
        if (Logic.isGameOver(board)) {
            return evaluateGameOverBoard(board, currentPlayer);
        } 
        List<Node> nextChildren = gameTree.getChildren(node);
        return evaluateMin(nextChildren, currentPlayer);
    }

    private double evaluateMin(List<Node> children, Symbol currentPlayer) {
        double minEvaluation = Double.POSITIVE_INFINITY;
        for (Node child : children) {
            double evaluation = evaluateNodeMin(child, currentPlayer);
            if (evaluation < minEvaluation) {
                minEvaluation = evaluation;
            }
        }
        return minEvaluation;
    }

    private double evaluateNodeMin(Node node, Symbol currentPlayer) {
        State state = node.state();
        Board board = state.board();
        if (Logic.isGameOver(board)) {
            return evaluateGameOverBoard(board, currentPlayer);
        } else {
            List<Node> nextChildren = gameTree.getChildren(node);
            return evaluateMax(nextChildren, currentPlayer);
        }
    }

    private double evaluateGameOverBoard(Board board, Symbol currentPlayer) {
        Symbol winner = Logic.getWinner(board);
        if (winner == currentPlayer) {
            return 1.;
        }
        if (winner == Symbol.EMPTY) {
            return 0.;
        }
        return -1.;
    }
}
