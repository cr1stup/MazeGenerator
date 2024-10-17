package backend.academy.MazeApp;

import backend.academy.MazeApp.Solvers.BellmanFordSolver;
import backend.academy.MazeApp.Solvers.MazeSolver;
import backend.academy.MazeApp.Solvers.SPFASolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static backend.academy.MazeApp.Cell.COIN;
import static backend.academy.MazeApp.Cell.PATH;
import static backend.academy.MazeApp.Cell.TRAP;
import static backend.academy.MazeApp.Cell.WALL;

public class SolversTest {

    @ParameterizedTest
    @MethodSource("provideDataForSolversTest")
    @DisplayName("Bellman-Ford solver can find path")
    public void testBellmanFordSolver(Cell[][] grid) {
        Maze maze = new Maze(grid.length, grid[0].length, grid);
        MazeSolver solver = new BellmanFordSolver();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(grid.length - 1, grid[0].length - 1);

        List<Coordinate> path = solver.solve(maze, start, end);

        Assertions.assertFalse(path.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideDataForSolversTest")
    @DisplayName("SPFA solver can find path")
    public void testSPFASolver(Cell[][] grid) {
        Maze maze = new Maze(grid.length, grid[0].length, grid);
        MazeSolver solver = new SPFASolver();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(grid.length - 1, grid[0].length - 1);

        List<Coordinate> path = solver.solve(maze, start, end);

        Assertions.assertFalse(path.isEmpty());
    }

    private static Stream<Arguments> provideDataForSolversTest() {
        Cell[][] grid1 = {
            {PATH, WALL, PATH, PATH, PATH},
            {PATH, WALL, PATH, WALL, PATH},
            {PATH, WALL, PATH, PATH, COIN},
            {PATH, PATH, PATH, PATH, PATH}
        };

        Cell[][] grid2 = {
            {PATH, WALL, PATH, PATH, PATH},
            {PATH, WALL, PATH, PATH, PATH},
            {PATH, WALL, PATH, WALL, PATH},
            {PATH, PATH, PATH, WALL, PATH}
        };

        Cell[][] grid3 = {
            {PATH, WALL, WALL, PATH, WALL},
            {PATH, PATH, TRAP, WALL, WALL},
            {PATH, WALL, TRAP, PATH, PATH}
        };

        return Stream.of(
            Arguments.of((Object) grid1),
            Arguments.of((Object) grid2),
            Arguments.of((Object) grid3)
        );
    }
}
