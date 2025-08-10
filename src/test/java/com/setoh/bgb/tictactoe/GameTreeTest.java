package com.setoh.bgb.tictactoe;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTreeTest {

    @Test
    public void testAddNode() {
        GameTree gameTree = new GameTree();
        GameTree.GameTreeNode node = new GameTree.GameTreeNode(){};

        assertThat(gameTree.isIn(node)).isFalse();
        gameTree.addNode(node);
        assertThat(gameTree.isIn(node)).isTrue();
    }

    @Test
    public void testAddEdge() {
        GameTree gameTree = new GameTree();
        GameTree.GameTreeNode from = new GameTree.GameTreeNode(){};
        GameTree.GameTreeNode to = new GameTree.GameTreeNode(){};
        assertThat(gameTree.isIn(from)).isFalse();
        assertThat(gameTree.isIn(to)).isFalse();

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
