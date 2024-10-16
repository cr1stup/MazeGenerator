package backend.academy.MazeApp.Generators;

import backend.academy.MazeApp.Cell;
import backend.academy.MazeApp.Maze;
import java.security.SecureRandom;
import static backend.academy.MazeApp.Cell.PATH;

public abstract class MazeGenerator {
    protected final SecureRandom random = new SecureRandom();

    abstract public Maze generate(int height, int width);

    protected void addSpecialSurfaces(Cell[][] grid, Cell type) {
        final double ratio = 0.05;
        int height = grid.length;
        int width = grid[0].length;
        int count = (int) ((height * width) * ratio);

        while (count > 0) {
            int row = random.nextInt(height);
            int col = random.nextInt(width);

            if (grid[row][col] == PATH) {
                grid[row][col] = type;
                count--;
            }
        }
    }
}
