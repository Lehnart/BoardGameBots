package com.setoh.bgb.cantstop;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board {

    public static final Map<Integer, Integer> DEFAULT_COLUMN_HEIGHTS = Map.ofEntries(
        Map.entry(2, 3), 
        Map.entry(3, 5),
        Map.entry(4, 7), 
        Map.entry(5, 9), 
        Map.entry(6, 11), 
        Map.entry(7, 13), 
        Map.entry(8, 11), 
        Map.entry(9, 9), 
        Map.entry(10, 7), 
        Map.entry(11, 5), 
        Map.entry(12, 3)
    );

    private final Map<Integer, Integer> columnHeights = new HashMap<>();
    private final Map<Integer, Integer> playerHeightsPerColumn = new HashMap<>();
    private final Map<Integer, Integer> temporaryHeights = new HashMap<>();

    public Board() {
        this(DEFAULT_COLUMN_HEIGHTS);
    }

    public Board(Map<Integer, Integer> columnHeights) {
        this.columnHeights.putAll(columnHeights);
        for (int column : columns()) {
            playerHeightsPerColumn.put(column, 0);
        }
    }

    public Board copy() {
        Board copy = new Board(this.columnHeights);
        for (int column : columns()) {
            copy.playerHeightsPerColumn.put(column, this.playerHeightsPerColumn.get(column));
        }
        copy.temporaryHeights.putAll(this.temporaryHeights);
        return copy;
    }

    public Set<Integer> columns() {
        return columnHeights.keySet();
    }

    public int getPlayerHeight(int column) {
        if (!columns().contains(column)) {
            throw new IllegalArgumentException("Column " + column + " is not a valid column key.");
        }
        return playerHeightsPerColumn.get(column);
    }

    public Map<Integer, Integer> getTemporaryHeights() {
        return new HashMap<>(temporaryHeights);
    }

    public int getTemporaryHeight(int column) {
        if (temporaryHeights.containsKey(column)) {
            return temporaryHeights.get(column);
        }
        return 0;
    }

    public int getColumnHeight(int column) {
        if (!columns().contains(column)) {
            throw new IllegalArgumentException("Column " + column + " is not a valid column key.");
        }
        return columnHeights.get(column);
    }

    public boolean isColumnClaimed(int column) {
        return getPlayerHeight(column) >= getColumnHeight(column);
    }

    public void temporaryProgress(int column) {
        temporaryHeights.putIfAbsent(column, playerHeightsPerColumn.get(column));
        temporaryHeights.put(column, temporaryHeights.get(column) + 1);
    }

    public void progress() {
        for (Map.Entry<Integer, Integer> columnAndHeight : temporaryHeights.entrySet()) {
            playerHeightsPerColumn.put(columnAndHeight.getKey(), columnAndHeight.getValue());
        }
        temporaryHeights.clear();
    }

    public void failToProgress() {
        temporaryHeights.clear();
    }

    public int getColumnClaimedCount() {
        int count = 0;
        for (int column : columns()) {
            if (isColumnClaimed(column)) {
                count += 1;
            }
        }
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return columnHeights.equals(board.columnHeights)
                && playerHeightsPerColumn.equals(board.playerHeightsPerColumn)
                && temporaryHeights.equals(board.temporaryHeights);
    }

    @Override
    public int hashCode() {
        int result = columnHeights.hashCode();
        result = 31 * result + playerHeightsPerColumn.hashCode();
        result = 31 * result + temporaryHeights.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Board{\n");
        sb.append("  columnHeights=").append(columnHeights).append(",\n");
        sb.append("  playerHeightsPerColumn=").append(playerHeightsPerColumn).append(",\n");
        sb.append("  temporaryHeights=").append(temporaryHeights).append("\n");
        sb.append('}');
        return sb.toString();
    }
}