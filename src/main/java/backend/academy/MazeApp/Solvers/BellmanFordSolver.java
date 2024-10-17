package backend.academy.MazeApp.Solvers;

import backend.academy.MazeApp.Cell;
import backend.academy.MazeApp.Coordinate;
import backend.academy.MazeApp.Maze;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static backend.academy.MazeApp.Cell.WALL;

public class BellmanFordSolver extends MazeSolver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        costs = new int[maze.height()][maze.width()];

        cameFrom = doBellmanFord(maze.grid(), start);

        return reconstructPath(cameFrom, start, end);
    }

    private Map<Coordinate, Coordinate> doBellmanFord(Cell[][] grid, Coordinate start) {
        int countK = countPaths(grid) - 1;
        initialization(start);

        for (int k = 0; k < countK; k++) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[zero].length; j++) {
                    checkAllDirections(i, j, grid);
                }
            }

            costs = Arrays.stream(tempCosts).map(int[]::clone).toArray(int[][]::new);
        }

        return cameFrom;
    }

    private int countPaths(Cell[][] grid) {
        int res = 0;

        for (Cell[] cells : grid) {
            for (int j = 0; j < grid[zero].length; j++) {
                if (cells[j] != WALL) {
                    res++;
                }
            }
        }

        return res;
    }
}
