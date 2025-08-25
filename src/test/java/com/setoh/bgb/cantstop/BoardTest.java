package com.setoh.bgb.cantstop;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;
import java.util.Set;

public class BoardTest {

    @Test
    public void testBoardConstructor() {
        Board board = new Board();
        assertThat(Map.ofEntries(
                Map.entry(2, 3), Map.entry(3, 5), Map.entry(4, 7), Map.entry(5, 9), Map.entry(6, 11), Map.entry(7, 13),
                Map.entry(8, 11), Map.entry(9, 9), Map.entry(10, 7), Map.entry(11, 5), Map.entry(12, 3))).isEqualTo(
                        Board.DEFAULT_COLUMN_HEIGHTS);
        assertThat(board.columns()).isEqualTo(Set.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        assertThat(board.getTemporaryHeights()).isEmpty();
        for (int column : board.columns()) {
            assertThat(board.getPlayerHeight(column)).isZero();
        }
        assertThatThrownBy(() -> board.getPlayerHeight(1)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Column 1 is not a valid column key.");
    }

    @Test
    public void testTemporaryProgress() {
        Board board = new Board();
        assertThat(board.getTemporaryHeights()).isEmpty();
        board.temporaryProgress(2);
        assertThat(board.getTemporaryHeights()).hasSize(1);
        assertThat(board.getTemporaryHeights()).containsOnly(Map.entry(2, 1));
        board.temporaryProgress(2);
        assertThat(board.getTemporaryHeights()).hasSize(1);
        assertThat(board.getTemporaryHeights()).containsOnly(Map.entry(2, 2));
        board.temporaryProgress(5);
        assertThat(board.getTemporaryHeights()).hasSize(2);
        assertThat(board.getTemporaryHeights()).containsOnly(Map.entry(2, 2), Map.entry(5, 1));
    }

    @Test
    public void testTemporaryHeight() {
        Board board = new Board();
        for (int column : board.columns()) {
            assertThat(board.getTemporaryHeight(column)).isZero();
        }
        board.temporaryProgress(2);
        for (int column : board.columns()) {
            if (column == 2) {
                assertThat(board.getTemporaryHeight(column)).isEqualTo(1);
            } else {
                assertThat(board.getTemporaryHeight(column)).isZero();
            }
        }
    }

    @Test
    public void testProgress() {
        Board board = new Board();
        assertThat(board.getTemporaryHeights()).isEmpty();
        board.progress();
        assertThat(board.getTemporaryHeights()).isEmpty();
        for (int column : board.columns()) {
            assertThat(board.getPlayerHeight(column)).isZero();
        }
        board.temporaryProgress(2);
        board.temporaryProgress(2);
        board.temporaryProgress(5);
        board.progress();
        assertThat(board.getTemporaryHeights()).isEmpty();
        for (int column : board.columns()) {
            if (column == 2) {
                assertThat(board.getPlayerHeight(column)).isEqualTo(2);
            } else if (column == 5) {
                assertThat(board.getPlayerHeight(column)).isEqualTo(1);
            } else {
                assertThat(board.getPlayerHeight(column)).isZero();
            }
        }
        board.temporaryProgress(2);
        board.temporaryProgress(2);
        board.temporaryProgress(5);
        board.progress();
        assertThat(board.getTemporaryHeights()).isEmpty();
        for (int column : board.columns()) {
            if (column == 2) {
                assertThat(board.getPlayerHeight(column)).isEqualTo(4);
            } else if (column == 5) {
                assertThat(board.getPlayerHeight(column)).isEqualTo(2);
            } else {
                assertThat(board.getPlayerHeight(column)).isZero();
            }
        }
    }

    @Test
    public void testIsColumnClaimed() {
        Board board = new Board();
        for (int column : board.columns()) {
            assertThat(board.isColumnClaimed(column)).isFalse();
        }
        board.temporaryProgress(2);
        board.temporaryProgress(2);
        board.progress();
        for (int column : board.columns()) {
            assertThat(board.isColumnClaimed(column)).isFalse();
        }
        board.temporaryProgress(2);
        board.progress();
        for (int column : board.columns()) {
            if (column == 2) {
                assertThat(board.isColumnClaimed(column)).isTrue();
            } else {
                assertThat(board.isColumnClaimed(column)).isFalse();
            }
        }
    }

    @Test
    public void testGetColumnClaimedCount() {
        Board board = new Board();
        assertThat(board.getColumnClaimedCount()).isZero();
        board.temporaryProgress(2);
        board.temporaryProgress(2);
        board.temporaryProgress(2);
        board.progress();
        assertThat(board.getColumnClaimedCount()).isOne();
        board.temporaryProgress(12);
        board.temporaryProgress(12);
        board.temporaryProgress(12);
        board.progress();
        assertThat(board.getColumnClaimedCount()).isEqualTo(2);
    }

    @Test
    public void testGetColumnHeight() {
        Board board = new Board();
        assertThat(board.getColumnHeight(2)).isEqualTo(3);
        assertThatThrownBy(() -> board.getColumnHeight(1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Column 1 is not a valid column key.");
    }

    @Test
    public void testCopy() {
        Board board = new Board();
        board.temporaryProgress(5);
        board.progress();
        board.temporaryProgress(2);
        Board copy = board.copy();
        assertThat(copy).isNotSameAs(board);
        assertThat(copy.getTemporaryHeights()).isEqualTo(board.getTemporaryHeights());
        assertThat(copy.getPlayerHeight(5)).isEqualTo(board.getPlayerHeight(5)).isEqualTo(1);
    }

    @Test
    public void testEqualsAndHashCode() {
        Board board1 = new Board();
        Board board2 = new Board();
        assertThat(board1).isEqualTo(board1).hasSameHashCodeAs(board1);
        assertThat(board1).isNotEqualTo(null);
        assertThat(board1).isEqualTo(board2).hasSameHashCodeAs(board2);
        board1.temporaryProgress(2);
        assertThat(board1).isNotEqualTo(board2);
        board2.temporaryProgress(2);
        assertThat(board1).isEqualTo(board2).hasSameHashCodeAs(board2);
        board1.progress();
        assertThat(board1).isNotEqualTo(board2);
        board2.progress();
        assertThat(board1).isEqualTo(board2).hasSameHashCodeAs(board2);
        board1.temporaryProgress(3);
        assertThat(board1).isNotEqualTo(board2);
    }
}