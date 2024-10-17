package backend.academy.MazeApp.Generators;

import backend.academy.MazeApp.Cell;
import backend.academy.MazeApp.Maze;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static backend.academy.MazeApp.Cell.COIN;
import static backend.academy.MazeApp.Cell.PATH;
import static backend.academy.MazeApp.Cell.TRAP;
import static backend.academy.MazeApp.Cell.WALL;

public class RecursiveGenerator extends MazeGenerator {

    private static final int[][] DIRS = {{0, 2}, {2, 0},  {0, -2}, {-2, 0}};

    @Override
    public String toString() {
        return "Recursive Generator";
    }

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height * 2 + 1][width * 2 + 1];

        initializeMaze(grid);
        dfs(1, 1, grid);
        addAdditionalConnections(grid);
        addSpecialSurfaces(grid, COIN);
        addSpecialSurfaces(grid, TRAP);

        return new Maze(height * 2 + 1, width * 2 + 1, grid);
    }

    private void initializeMaze(Cell[][] grid) {
        for (Cell[] cell : grid) {
            Arrays.fill(cell, WALL);
        }
    }

    private void dfs(int row, int col, Cell[][] grid) {
        grid[row][col] = PATH;

        List<Integer> positions = new ArrayList<>(List.of(zero, one, two, three));
        Collections.shuffle(positions);

        for (int i = 0; i < DIRS.length; i++) {
            int[] dir = DIRS[positions.get(i)];
            int newRow = row + dir[zero];
            int newCol = col + dir[one];

            if (isValid(newRow, newCol, grid)) {
                int pathRow = row + dir[zero] / 2;
                int pathCol = col + dir[one] / 2;
                grid[pathRow][pathCol] = PATH;
                dfs(newRow, newCol, grid);
            }
        }
    }

    private void addAdditionalConnections(Cell[][] grid) {
        final double ratio = 0.1;
        int height = grid.length;
        int width = grid[0].length;
        int additionalEdges = (int) (height * width * ratio);

        for (int i = 0; i < additionalEdges; i++) {
            int row = random.nextInt(width);
            int col = random.nextInt(height);

            int[] dir = DIRS[random.nextInt(DIRS.length)];
            int wallRow = row + dir[zero] / 2;
            int wallCol = col + dir[one] / 2;

            if (isValid(wallRow, wallCol, grid)) {
                grid[wallRow][wallCol] = PATH;
            }
        }
    }

    private boolean isValid(int row, int col, Cell[][] grid) {
        return row > 0 && row < grid.length - 1 && col > 0 && col < grid[0].length - 1 && grid[row][col] == WALL;
    }
}
