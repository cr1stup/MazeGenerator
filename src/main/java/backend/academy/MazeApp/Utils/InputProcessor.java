package backend.academy.MazeApp.Utils;

import backend.academy.MazeApp.Cell;
import backend.academy.MazeApp.Coordinate;
import backend.academy.MazeApp.Exceptions.InvalidInputException;
import backend.academy.MazeApp.Generators.KruskalGenerator;
import backend.academy.MazeApp.Generators.MazeGenerator;
import backend.academy.MazeApp.Generators.RecursiveGenerator;
import backend.academy.MazeApp.Solvers.BellmanFordSolver;
import backend.academy.MazeApp.Solvers.MazeSolver;
import backend.academy.MazeApp.Solvers.SPFASolver;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import static backend.academy.MazeApp.Cell.END;
import static backend.academy.MazeApp.Cell.START;
import static backend.academy.MazeApp.Cell.WALL;

public class InputProcessor {
    private final Scanner scanner;
    private final PrintStream printStream;
    private final String errorMessage;
    private final String enumeration;
    private final List<MazeGenerator> generatorList;
    private final List<MazeSolver> solverList;
    public static final int HEIGHT_MAX = 20;
    public static final int HEIGHT_MIN = 5;
    public static final int WIDTH_MAX = 80;
    public static final int WIDTH_MIN = 5;

    public InputProcessor(InputStream inputStream, PrintStream printStream) {
        Charset charset = StandardCharsets.UTF_8;
        this.scanner = new Scanner(inputStream, charset);
        this.printStream = printStream;
        errorMessage = "%nInvalid input! Try again%n";
        enumeration = "%d. %s%n";
        generatorList = List.of(new KruskalGenerator(), new RecursiveGenerator());
        solverList = List.of(new BellmanFordSolver(), new SPFASolver());
    }

    public int inputHeight() {
        String message = "Enter height: ";
        printStream.print(message);
        int inputNum = getInput(HEIGHT_MIN, HEIGHT_MAX, message);

        return (inputNum - 1) / 2;
    }

    public int inputWidth() {
        String message = "Enter width: ";
        printStream.print(message);
        int inputNum = getInput(WIDTH_MIN, WIDTH_MAX, message);

        return (inputNum - 1) / 2;
    }

    public MazeGenerator chooseGenerator() {
        printStream.println();
        for (int i = 0; i < generatorList.size(); i++) {
            printStream.format(enumeration, i + 1, generatorList.get(i));
        }

        String message = "Choose your generator: ";
        printStream.format("%n" + message);
        int inputNum = getInput(1, generatorList.size(), message);

        return generatorList.get(inputNum - 1);
    }

    public MazeSolver chooseSolver() {
        printStream.println();
        for (int i = 0; i < solverList.size(); i++) {
            printStream.format(enumeration, i + 1, solverList.get(i));
        }

        String message = "Choose your solver: ";
        printStream.format("%n" + message);
        int inputNum = getInput(1, solverList.size(), message);

        return solverList.get(inputNum - 1);
    }

    public Coordinate inputStart(Cell[][] grid) {
        printStream.format("%nInput start [A]:%n");

        Coordinate start = inputCoordinate(grid);
        grid[start.row()][start.col()] = START;

        return start;
    }

    public Coordinate inputEnd(Cell[][] grid) {
        printStream.format("%nInput end [B]:%n");

        Coordinate end = inputCoordinate(grid);
        grid[end.row()][end.col()] = END;

        return end;
    }

    private int getInput(int min, int max, String message) {
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            int inputNum;

            try {
                inputNum = Integer.parseInt(input);
                if (inputNum > max || inputNum < min) {
                    throw new InvalidInputException("%nInvalid! Check the input range!%n");
                }
            } catch (InvalidInputException e) {
                printStream.format(e.getMessage());
                printStream.print(message);
                continue;
            } catch (NumberFormatException e) {
                printStream.format(errorMessage);
                printStream.print(message);
                continue;
            }

            return inputNum;
        }
    }

    private Coordinate inputCoordinate(Cell[][] grid) {
        int row;
        int col;

        while (true) {
            String rowMessage = "row: ";
            printStream.print(rowMessage);
            row = getInput(0, grid.length - 1, rowMessage);

            String colMessage = "col: ";
            printStream.print(colMessage);
            col = getInput(0, grid[0].length - 1, colMessage);

            if (grid[row][col] == WALL) {
                printStream.format("Here is wall! Try again!%n");
                continue;
            } else if (grid[row][col] == START) {
                printStream.format("End is equal to start! Try again!%n");
                continue;
            }

            return new Coordinate(row, col);
        }
    }
}
