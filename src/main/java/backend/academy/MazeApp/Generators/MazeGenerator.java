package backend.academy.MazeApp.Generators;

import backend.academy.MazeApp.Maze;

public abstract class MazeGenerator {

    abstract public Maze generate(int height, int width);
}
