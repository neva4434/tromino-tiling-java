package algorithm;

import model.Board;

public class TrominoTiler {

    private Board board;
    private int tile = 1;

    public TrominoTiler(Board board) {
        this.board = board;
    }

    public int getTileCount() {
        return tile - 1;
    }

    // main recursive solver
    public void solve(int a, int b, int size, int damagedRow, int damagedCol) {

        if (size == 1) return;

        int middle = size / 2;
        int number = tile++;

        int[][] grid = board.getGrid();

        // TOP LEFT
        if (damagedRow < a + middle && damagedCol < b + middle) {

            grid[a + middle - 1][b + middle] = number;
            grid[a + middle][b + middle - 1] = number;
            grid[a + middle][b + middle] = number;

            solve(a, b, middle, damagedRow, damagedCol);
            solve(a, b + middle, middle, a + middle - 1, b + middle);
            solve(a + middle, b, middle, a + middle, b + middle - 1);
            solve(a + middle, b + middle, middle, a + middle, b + middle);
        }

        // TOP RIGHT
        else if (damagedRow < a + middle && damagedCol >= b + middle) {

            grid[a + middle - 1][b + middle - 1] = number;
            grid[a + middle][b + middle - 1] = number;
            grid[a + middle][b + middle] = number;

            solve(a, b, middle, a + middle - 1, b + middle - 1);
            solve(a, b + middle, middle, damagedRow, damagedCol);
            solve(a + middle, b, middle, a + middle, b + middle - 1);
            solve(a + middle, b + middle, middle, a + middle, b + middle);
        }

        // BOTTOM LEFT
        else if (damagedRow >= a + middle && damagedCol < b + middle) {

            grid[a + middle - 1][b + middle - 1] = number;
            grid[a + middle - 1][b + middle] = number;
            grid[a + middle][b + middle] = number;

            solve(a, b, middle, a + middle - 1, b + middle - 1);
            solve(a, b + middle, middle, a + middle - 1, b + middle);
            solve(a + middle, b, middle, damagedRow, damagedCol);
            solve(a + middle, b + middle, middle, a + middle, b + middle);
        }

        // BOTTOM RIGHT
        else {

            grid[a + middle - 1][b + middle - 1] = number;
            grid[a + middle - 1][b + middle] = number;
            grid[a + middle][b + middle - 1] = number;

            solve(a, b, middle, a + middle - 1, b + middle - 1);
            solve(a, b + middle, middle, a + middle - 1, b + middle);
            solve(a + middle, b, middle, a + middle, b + middle - 1);
            solve(a + middle, b + middle, middle, damagedRow, damagedCol);
        }
    }
}