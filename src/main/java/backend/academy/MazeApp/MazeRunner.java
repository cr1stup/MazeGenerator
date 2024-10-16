package backend.academy.MazeApp;

import backend.academy.MazeApp.Utils.InputProcessor;
import backend.academy.MazeApp.Utils.Renderer;
import java.io.InputStream;
import java.io.PrintStream;

public class MazeRunner {
    private final Renderer renderer;
    private final InputProcessor input;

    public MazeRunner(InputStream inputStream, PrintStream printStream) {
        renderer = new Renderer(printStream);
        input = new InputProcessor(inputStream, printStream);
    }

    public void run() {

    }
}
