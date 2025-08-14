package com.setoh.bgb.tictactoe.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.setoh.bgb.tictactoe.Board;
import com.setoh.bgb.tictactoe.Board.Position;
import com.setoh.bgb.tictactoe.Board.Symbol;
import com.setoh.bgb.tictactoe.GameTree;
import com.setoh.bgb.tictactoe.Logic;

public class ExhaustiveExplorator implements Explorator<Board.Position> {

    private GameTree gameTree = new GameTree();

    public ExhaustiveExplorator() {
        Board currentBoard = new Board();
        Symbol currentSymbol = Symbol.X;

        gameTree.addNode(new GameTree.Node(currentBoard.copy()));
        List<Position> positionsToExplore = currentBoard.getEmptyPositions();

        for(Position p : positionsToExplore) {
            Board newBoard = currentBoard.copy();
            newBoard.setSymbol(p, currentSymbol);
            gameTree.addNode(new GameTree.Node(newBoard));
            gameTree.addEdge(new GameTree.Node(currentBoard.copy()), new GameTree.Node(newBoard));
        }

        List<Position> nextPositionsToExplore = new ArrayList<>();
        while (!nextPositionsToExplore.isEmpty()) {
            currentSymbol = (currentSymbol == Symbol.X) ? Symbol.O : Symbol.X;
            positionsToExplore.clear();
            positionsToExplore.addAll(nextPositionsToExplore);
            nextPositionsToExplore.clear();

            for (Position position : positionsToExplore) {
                Board newBoard = currentBoard.copy();
                newBoard.setSymbol(position, currentSymbol);

                if (!Logic.isGameOver(newBoard)) {
                    List<Position> newPositionsToExplore = newBoard.getEmptyPositions();
                    for (Position p : newPositionsToExplore) {
                        Board nextBoard = newBoard.copy();
                        nextBoard.setSymbol(p, currentSymbol);
                        gameTree.addNode(new GameTree.Node(nextBoard));
                        gameTree.addEdge(new GameTree.Node(newBoard), new GameTree.Node(nextBoard));
                    }
                    positionsToExplore.addAll(newPositionsToExplore);
                }
                currentBoard = newBoard;
            }
        }

    }

    @Override
    public Map<Board.Position, Double> explore(Board board, Symbol currentPlayer, Evaluator evaluator) {
        Map<Board.Position, Double> scores = new HashMap<>();
        for(Board.Position position : board.getEmptyPositions()) {
            Board newBoard = new Board(board);
            newBoard.setSymbol(position, currentPlayer);
            Double score = evaluator.evaluate(newBoard, currentPlayer);
            scores.put(position, score);
        }
        return scores;
    }

}
