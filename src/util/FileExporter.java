package util;

import model.Board;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileExporter {

    public static void export(Board board, long time, int tileCount) {

        int size = board.getSize();
        int[][] grid = board.getGrid();

        String fileName = "TiledBoard_" + size + "x" + size + ".txt";

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(fileName))) {

            writer.write("L-TROMINO TILING OUTPUT\n");
            writer.write("Board size: " + size + " x " + size + "\n");
            writer.write("Number of trominoes used: " + tileCount + "\n");
            writer.write("Execution time (nanoseconds): " + time + "\n\n");

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {

                    if (grid[i][j] == Board.DAMAGED)
                        writer.write(" X ");
                    else
                        writer.write(String.format("%3d ", grid[i][j]));
                }
                writer.newLine();
            }

            System.out.println("Output exported to file: " + fileName);

        } catch (IOException e) {
            System.out.println("File writing error: " + e.getMessage());
        }
    }
}