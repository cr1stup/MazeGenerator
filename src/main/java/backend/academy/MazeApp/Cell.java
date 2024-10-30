package backend.academy.MazeApp;

import lombok.Getter;

@Getter
public enum Cell {
    WALL('║', Integer.MAX_VALUE),
    STAR('*', Integer.MAX_VALUE),
    START('A', Integer.MAX_VALUE),
    END('B', Integer.MAX_VALUE),
    PATH(' ', 1),
    COIN('C', -5),
    TRAP('T', 5);

    private final char view;
    private final int cost;

    Cell(char view, int cost) {
        this.view = view;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "" + view;
    }
}
