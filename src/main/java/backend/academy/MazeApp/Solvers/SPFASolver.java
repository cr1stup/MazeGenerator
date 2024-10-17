package backend.academy.MazeApp.Solvers;

import backend.academy.MazeApp.Cell;
import backend.academy.MazeApp.Coordinate;
import backend.academy.MazeApp.Maze;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SPFASolver extends MazeSolver {

    @Override
    public String toString() {
        return "Shortest-Path-Faster-Algorithm";
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        costs = new int[maze.height()][maze.width()];
        cameFrom = doSPFASolve(maze.grid(), start);

        return reconstructPath(cameFrom, start, end);
    }

    private Map<Coordinate, Coordinate> doSPFASolve(Cell[][] grid, Coordinate start) {
        Queue<Coordinate> q = new LinkedList<>();
        q.add(new Coordinate(start.row(), start.col()));

        initialization(start);

        while (!q.isEmpty()) {
            Coordinate curr = q.poll();
            int i = curr.row();
            int j = curr.col();

            checkAllDirections(i, j, grid, q);

            costs = Arrays.stream(tempCosts).map(int[]::clone).toArray(int[][]::new);
        }

        return cameFrom;
    }
}
