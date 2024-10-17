package backend.academy.MazeApp;

import backend.academy.MazeApp.Generators.MazeGenerator;
import backend.academy.MazeApp.Solvers.MazeSolver;
import backend.academy.MazeApp.Utils.InputProcessor;
import backend.academy.MazeApp.Utils.Renderer;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class MazeRunner {
    private final Renderer renderer;
    private final InputProcessor input;

    public MazeRunner(InputStream inputStream, PrintStream printStream) {
        renderer = new Renderer(printStream);
        input = new InputProcessor(inputStream, printStream);
    }

    public void run() {
        renderer.printGreeting();
        int height = input.inputHeight();
        int width = input.inputWidth();

        MazeGenerator generator = input.chooseGenerator();
        Maze maze = generator.generate(height, width);

        renderer.renderGridWithCoordinates(maze.grid());

        Coordinate start = input.inputStart(maze.grid());
        Coordinate end = input.inputEnd(maze.grid());

        MazeSolver solver = input.chooseSolver();
        List<Coordinate> path = solver.solve(maze, start, end);

        renderer.renderGrid(maze.grid(), "Your Maze with A and B!\n");
        renderer.renderPath(maze.grid(), path);
    }
}
