import algorithm.TrominoTiler;
import model.Board;
import util.BoardPrinter;
import util.FileExporter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean continueProgram = true;

        System.out.println("=== L-SHAPE TILING PROBLEM ===");

        while (continueProgram) {

            try {
                // n değeri alma
                System.out.print("\nEnter value of n (board size = 2^n): ");
                int n = sc.nextInt();

                if (n < 1) {
                    System.out.println("Error: n must be at least 1.");
                    continue;
                }

                int size = 1 << n;
                System.out.println("Board size: " + size + " x " + size);

                // damaged cell
                System.out.print("Enter damaged cell row (0 - " + (size - 1) + "): ");
                int x = sc.nextInt();

                System.out.print("Enter damaged cell column (0 - " + (size - 1) + "): ");
                int y = sc.nextInt();

                if (x < 0 || x >= size || y < 0 || y >= size) {
                    System.out.println("Error: Damaged cell is out of board range.");
                    continue;
                }

                // Board oluştur
                Board board = new Board(size, x, y);

                // Solver oluştur
                TrominoTiler solver = new TrominoTiler(board);

                // süre ölçümü
                long startTime = System.nanoTime();
                solver.solve(0, 0, size, x, y);
                long endTime = System.nanoTime();

                long duration = endTime - startTime;

                // çıktı
                System.out.println("\nFinal Tiled Board:");
                BoardPrinter.print(board);

                System.out.println("\nNumber of trominoes used: "
                        + solver.getTileCount());

                System.out.println("Execution time (nanoseconds): "
                        + duration);

                // dosyaya yaz
                FileExporter.export(board, duration, solver.getTileCount());

            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter only integer values.");
                sc.nextLine(); // buffer temizle
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

            // tekrar çalıştırma
            System.out.print("\nDo you want to run another test? (y/n): ");
            String choice = sc.next();

            if (!choice.equalsIgnoreCase("y")) {
                continueProgram = false;
            }
        }

        System.out.println("\nProgram finished.");
        sc.close();
    }
}