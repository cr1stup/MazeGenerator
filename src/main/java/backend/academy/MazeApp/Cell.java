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
    private static final int WALL_COST = Integer.MAX_VALUE;
    private static final int PATH_COST = 1;
    private static final int COIN_COST = -5;
    private static final int TRAP_COST = 5;

    Cell(char view) {
        this.view = view;
    }

    public int getCost() {
        int cost;

        switch (this) {
            case WALL -> cost = WALL_COST;
            case PATH -> cost = PATH_COST;
            case COIN -> cost = COIN_COST;
            case TRAP -> cost = TRAP_COST;
            default -> cost = 0;
        }

        return cost;
    }

    @Override
    public String toString() {
        return "" + view;
    }
}
