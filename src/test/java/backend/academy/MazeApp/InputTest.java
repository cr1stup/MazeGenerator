package backend.academy.MazeApp;

import backend.academy.MazeApp.Generators.KruskalGenerator;
import backend.academy.MazeApp.Utils.InputProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InputTest {
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private final PrintStream ps = new PrintStream(byteArrayOutputStream);
    private final Maze maze = new KruskalGenerator().generate(10, 10);

    @ParameterizedTest
    @ValueSource(strings = {"\t", "  ", "\n", "1234123", "-1"})
    @DisplayName("height input is resistant to incorrect data")
    public void testHeightInput(String input) {
        input = input + String.format("%n15%n");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputProcessor inputProcess = new InputProcessor(inputStream, ps);
        inputProcess.inputHeight();

        String output = byteArrayOutputStream.toString();

        Assertions.assertTrue(output.contains("Enter height:"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"\t", "  ", "\n", "1234123", "-1"})
    @DisplayName("width input is resistant to incorrect data")
    public void testWidthInput(String input) {
        input = input + String.format("%n15%n");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputProcessor inputProcess = new InputProcessor(inputStream, ps);
        inputProcess.inputWidth();

        String output = byteArrayOutputStream.toString();

        Assertions.assertTrue(output.contains("Enter width:"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"\t", "  ", "\n", "1234123", "-1"})
    @DisplayName("generator input is resistant to incorrect data")
    public void testGeneratorInput(String input) {
        input = input + String.format("%n1%n");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputProcessor inputProcess = new InputProcessor(inputStream, ps);
        inputProcess.chooseGenerator();

        String output = byteArrayOutputStream.toString();

        Assertions.assertTrue(output.contains("Choose your generator:"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"\t", "  ", "\n", "1234123", "-1"})
    @DisplayName("solver input is resistant to incorrect data")
    public void testSolverInput(String input) {
        input = input + String.format("%n1%n");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputProcessor inputProcess = new InputProcessor(inputStream, ps);
        inputProcess.chooseSolver();

        String output = byteArrayOutputStream.toString();

        Assertions.assertTrue(output.contains("Choose your solver:"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"\t", "  ", "\n", "1234123", "-1"})
    @DisplayName("start input is resistant to incorrect data")
    public void testStartInput(String input) {
        input = input + String.format("%n1%n1%n");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputProcessor inputProcess = new InputProcessor(inputStream, ps);
        inputProcess.inputStart(maze.grid());

        String output = byteArrayOutputStream.toString();

        Assertions.assertTrue(output.contains("Input start"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"\t", "  ", "\n", "1234123", "-1"})
    @DisplayName("end input is resistant to incorrect data")
    public void testEndInput(String input) {
        input = input + String.format("%n1%n1%n");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputProcessor inputProcess = new InputProcessor(inputStream, ps);
        inputProcess.inputEnd(maze.grid());

        String output = byteArrayOutputStream.toString();

        Assertions.assertTrue(output.contains("Input end"));
    }
}
