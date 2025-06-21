package com.setoh.bgb.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Board {
    
    public enum Symbol {
        EMPTY, X, O
    }

    public record Position(int row, int col) {
        public Position {
            if (row < 0 || row >= 3 || col < 0 || col >= 3) {
                throw new IndexOutOfBoundsException("Row and column must be between 0 and 2.");
            }
        }
    }

    private Symbol[][] grid;

    public Board() {
        grid = new Symbol[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = Symbol.EMPTY;
            }
        }
    }

    public Symbol getSymbol(Position position) {
        return grid[position.row][position.col];
    }

    public void setSymbol(Position position, Symbol symbol) {
        if (grid[position.row][position.col] != Symbol.EMPTY) {
            throw new IllegalStateException("Cell is already occupied.");
        }
        grid[position.row][position.col] = symbol;
    }

    public boolean isGameFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(getSymbol(new Position(i,j)).equals(Symbol.EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Position> getEmptyPositions(){
        List<Position> emptyPositions = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(getSymbol(new Position(i,j)).equals(Symbol.EMPTY)) {
                    emptyPositions.add(new Position(i, j));
                }
            }
        }
        return emptyPositions;
    }

}
