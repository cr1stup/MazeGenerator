package backend.academy;

import backend.academy.MazeApp.MazeRunner;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        MazeRunner maze = new MazeRunner(System.in, System.out);
        maze.run();
    }
}
