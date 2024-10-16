package backend.academy.MazeApp.Utils;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class InputProcessor {
    private final Scanner scanner;
    private final PrintStream printStream;

    public InputProcessor(InputStream inputStream, PrintStream printStream) {
        Charset charset = StandardCharsets.UTF_8;
        this.scanner = new Scanner(inputStream, charset);
        this.printStream = printStream;
    }
}
