package com.setoh.bgb.tictactoe;

import org.junit.Test;

import com.setoh.bgb.ai.GameTree;
import com.setoh.bgb.ai.GameTree.Node;
import com.setoh.bgb.tictactoe.Board.Position;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTreeTest {

    @Test
    public void testConstructor() {
        Board board = new Board();
        board.setSymbol(new Position(0, 0), Board.Symbol.X);
        TicTacToeState state = new TicTacToeState(board, Board.Symbol.X);
        Node<TicTacToeState> initialNode = new Node<>(state);
        GameTree<TicTacToeState> gameTree = new GameTree<>(initialNode);
        assertThat(gameTree.isIn(initialNode)).isTrue();
    }

    @Test
    public void testAddNode() {
        GameTree<TicTacToeState> gameTree = new GameTree<>();
        Board board = new Board();
        Node<TicTacToeState> node = new Node<>(new TicTacToeState(board, Board.Symbol.X));

        assertThat(gameTree.isIn(node)).isFalse();
        gameTree.addNode(node);
        assertThat(gameTree.isIn(node)).isTrue();
    }

    @Test
    public void testAddEdge() {
        GameTree<TicTacToeState> gameTree = new GameTree<>();
        Board board = new Board();
        Node<TicTacToeState> from = new Node<>(new TicTacToeState(board, Board.Symbol.X));
        Node<TicTacToeState> to = new Node<>(new TicTacToeState(board, Board.Symbol.O));
        
        assertThat(gameTree.isIn(from)).isFalse();
        assertThat(gameTree.isIn(to)).isFalse();
        assertThat(gameTree.getChildren(from)).isEmpty();
        assertThat(gameTree.getChildren(to)).isEmpty();

        gameTree.addNode(from);
        gameTree.addNode(to);
        
        assertThat(gameTree.getChildren(from)).isEmpty();
        assertThat(gameTree.getChildren(to)).isEmpty();

        gameTree.addEdge(from, to);

        assertThat(gameTree.isIn(from)).isTrue();
        assertThat(gameTree.isIn(to)).isTrue();
        assertThat(gameTree.getChildren(from)).containsExactly(to);
    }
}
