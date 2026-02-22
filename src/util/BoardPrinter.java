package util;

import model.Board;

public class BoardPrinter {

    public static void print(Board board) {

        int[][] grid = board.getGrid();

        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == Board.DAMAGED)
                    System.out.print("  X ");
                else
                    System.out.printf("%3d ", cell);
            }
            System.out.println();
        }
    }
}