package backend.academy.MazeApp.Solvers;

import backend.academy.MazeApp.Cell;
import backend.academy.MazeApp.Coordinate;
import backend.academy.MazeApp.Maze;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import static backend.academy.MazeApp.Cell.WALL;

public abstract class MazeSolver {
    protected int[][] costs;
    protected int[][] tempCosts;
    protected Map<Coordinate, Coordinate> cameFrom = new HashMap<>();
    protected int zero = 0;
    protected int one = 1;

    abstract public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);

    protected void initialization(Coordinate start) {
        cameFrom.put(start, null);

        for (int[] cost : costs) {
            Arrays.fill(cost, Integer.MAX_VALUE);
        }

        costs[start.row()][start.col()] = 0;
        tempCosts = Arrays.stream(costs).map(int[]::clone).toArray(int[][]::new);
    }

    protected void checkAllDirections(int i, int j, Cell[][] grid) {
        checkDirections(i, j, grid, null);
    }

    protected void checkAllDirections(int i, int j, Cell[][] grid, Queue<Coordinate> q) {
        checkDirections(i, j, grid, q);
    }

    private void checkDirections(int i, int j, Cell[][] grid, Queue<Coordinate> q) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        if (costs[i][j] == Integer.MAX_VALUE || grid[i][j] == WALL) {
            return;
        }

        for (int[] dir : directions) {
            int row = i + dir[zero];
            int col = j + dir[one];

            if (outOfBounds(row, col, grid) || grid[row][col] == WALL) {
                continue;
            }

            int newCost = costs[i][j] + grid[row][col].getCost();
            Coordinate newPosition = new Coordinate(row, col);
            Coordinate parent = new Coordinate(i, j);

            if (newCost < tempCosts[row][col] && !cameFrom.containsValue(newPosition)) {
                tempCosts[row][col] = newCost;
                cameFrom.put(newPosition, parent);
                if (q != null) {
                    q.add(new Coordinate(row, col));
                }
            }
        }
    }

    private boolean outOfBounds(int row, int col, Cell[][] grid) {
        return row < 0 || row >= grid.length || col < 0 || col >= grid[0].length;
    }

    protected List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> cameFrom, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        if (!cameFrom.containsValue(start) || !cameFrom.containsKey(end)) {
            return path;
        }

        Coordinate curr = end;

        while (curr != null && curr != start) {
            path.add(curr);
            curr = cameFrom.get(curr);
        }

        return path;
    }
}
