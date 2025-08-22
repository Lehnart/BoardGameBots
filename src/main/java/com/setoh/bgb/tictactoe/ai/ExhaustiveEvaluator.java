package com.setoh.bgb.tictactoe.ai;

import java.util.List;

import com.setoh.bgb.ai.GameTree;
import com.setoh.bgb.ai.GameTree.Node;
import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Symbol;
import com.setoh.bgb.tictactoe.Logic;
import com.setoh.bgb.tictactoe.TicTacToeState;

public class ExhaustiveEvaluator {

    private final GameTree<TicTacToeState> gameTree;

    public ExhaustiveEvaluator(GameTree<TicTacToeState> gameTree) {
        this.gameTree = gameTree;
    }

    public Board findNextBestBoard(Board board, Symbol currentPlayer) {
        TicTacToeState initialState = new TicTacToeState(board, currentPlayer);
        List<Node<TicTacToeState>> children = gameTree.getChildren(new Node<>(initialState));

        Node<TicTacToeState> bestNode = children.get(0);
        double bestEvaluation = evaluateNodeMax(bestNode, currentPlayer);
        for (Node<TicTacToeState> child : children) {
            double evaluation = evaluateNodeMax(child, currentPlayer);
            if (evaluation > bestEvaluation) {
                bestEvaluation = evaluation;
                bestNode = child;
            }
        }
        return bestNode.state().board().copy();
    }

    double evaluateMax(List<Node<TicTacToeState>> children, Symbol currentPlayer) {
        double maxEvaluation = Double.NEGATIVE_INFINITY;
        for (Node<TicTacToeState> child : children) {
            double evaluation = evaluateNodeMax(child, currentPlayer);
            if (evaluation>maxEvaluation){
                maxEvaluation = evaluation;
            }
        }
        return maxEvaluation;
    }

    double evaluateNodeMax(Node<TicTacToeState> node, Symbol currentPlayer) {
        TicTacToeState state = node.state();
        Board board = state.board();
        if (Logic.isGameOver(board)) {
            return evaluateGameOverBoard(board, currentPlayer);
        } 
        List<Node<TicTacToeState>> nextChildren = gameTree.getChildren(node);
        return evaluateMin(nextChildren, currentPlayer);
    }

    double evaluateMin(List<Node<TicTacToeState>> children, Symbol currentPlayer) {
        double minEvaluation = Double.POSITIVE_INFINITY;
        for (Node<TicTacToeState> child : children) {
            double evaluation = evaluateNodeMin(child, currentPlayer);
            if (evaluation < minEvaluation) {
                minEvaluation = evaluation;
            }
        }
        return minEvaluation;
    }

    double evaluateNodeMin(Node<TicTacToeState> node, Symbol currentPlayer) {
        TicTacToeState state = node.state();
        Board board = state.board();
        if (Logic.isGameOver(board)) {
            return evaluateGameOverBoard(board, currentPlayer);
        } else {
            List<Node<TicTacToeState>> nextChildren = gameTree.getChildren(node);
            return evaluateMax(nextChildren, currentPlayer);
        }
    }

    static double evaluateGameOverBoard(Board board, Symbol currentPlayer) {
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
