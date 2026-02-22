package model;

public class Board {

    public static final int DAMAGED = -1;

    private int[][] grid;
    private int size;

    public Board(int size, int damagedRow, int damagedCol) {
        this.size = size;
        grid = new int[size][size];
        grid[damagedRow][damagedCol] = DAMAGED;
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getSize() {
        return size;
    }

    public void setCell(int r, int c, int value) {
        grid[r][c] = value;
    }
}