package backend.academy.MazeApp;

public enum Cell {
    WALL('║'),
    PATH(' '),
    COIN('C'),
    TRAP('T'),
    STAR('*'),
    START('A'),
    END('B');

    private final char view;

    Cell(char view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "" + view;
    }
}
