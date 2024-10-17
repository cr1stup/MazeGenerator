package backend.academy.MazeApp.Exceptions;

public class InvalidInputException extends NumberFormatException {
    public InvalidInputException(String message) {
        super(message);
    }
}
