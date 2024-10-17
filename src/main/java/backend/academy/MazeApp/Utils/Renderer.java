package backend.academy.MazeApp.Utils;

import backend.academy.MazeApp.Cell;
import backend.academy.MazeApp.Coordinate;
import java.io.PrintStream;
import java.util.List;
import static backend.academy.MazeApp.Cell.END;
import static backend.academy.MazeApp.Cell.STAR;
import static backend.academy.MazeApp.Cell.START;

public class Renderer {
    private final PrintStream printStream;
    private static final int TEN = 10;

    public Renderer(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printGreeting() {
        printStream.println("Welcome to the maze generator and solver!");
        printStream.println("Enter the height and the width of your maze!");
        printStream.println("-------------------------------------------");
        printStream.format("Height range: [%d : %d]%n", InputProcessor.HEIGHT_MIN, InputProcessor.HEIGHT_MAX);
        printStream.format("Width range: [%d : %d]%n", InputProcessor.WIDTH_MIN, InputProcessor.WIDTH_MAX);
        printStream.println("---------------------");
    }

    public void renderGridWithCoordinates(Cell[][] grid) {
        int zero = 0;
        printStream.println();
        printXAxis(grid[zero].length);

        for (int i = 0; i < grid.length; i++) {

            if (lastDigit(i) == 0 && i != 0) {
                printStream.print('-');
            } else {
                printStream.print(i >= TEN ? lastDigit(i) : i);
            }

            for (int j = 0; j < grid[zero].length; j++) {
                printStream.print(grid[i][j]);
            }

            printStream.println();
        }

        printXAxis(grid[zero].length);
    }

    private void printXAxis(int length) {
        printStream.print(' ');

        for (int j = 0; j < length; j++) {
            if (lastDigit(j) == 0 && j != 0) {
                printStream.print('-');
            } else {
                printStream.print(j >= TEN ? lastDigit(j) : j);
            }
        }

        printStream.println();
    }

    public void renderGrid(Cell[][] grid, String message) {
        printStream.format("%n" + message);

        for (Cell[] cellRow : grid) {
            for (Cell c : cellRow) {
                printStream.print(c);
            }
            printStream.println();
        }
    }

    private int lastDigit(int number) {
        return number % TEN;
    }

    public void renderPath(Cell[][] grid, List<Coordinate> path) {
        if (path == null || path.isEmpty()) {
            printStream.format("%nThe path was not found!");
            return;
        }

        printStream.format("%nYour Maze with path!");

        for (Coordinate curr : path) {
            if (!(grid[curr.row()][curr.col()] == START || grid[curr.row()][curr.col()] == END)) {
                grid[curr.row()][curr.col()] = STAR;
            }
        }

        renderGrid(grid, "");
    }
}
