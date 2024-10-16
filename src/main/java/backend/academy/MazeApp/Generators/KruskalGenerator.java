package backend.academy.MazeApp.Generators;

import backend.academy.MazeApp.Cell;
import backend.academy.MazeApp.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static backend.academy.MazeApp.Cell.COIN;
import static backend.academy.MazeApp.Cell.PATH;
import static backend.academy.MazeApp.Cell.TRAP;
import static backend.academy.MazeApp.Cell.WALL;

public class KruskalGenerator extends MazeGenerator {

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height * 2 + 1][width * 2 + 1];
        UnionFind unionFind = new UnionFind(height * width);
        List<Edge> edges = new ArrayList<>();

        initializeMaze(height, width, edges);
        Collections.shuffle(edges);

        for (int row = 0; row < height * 2 + 1; row++) {
            for (int col = 0; col < width * 2 + 1; col++) {
                if (row % 2 == 0 || col % 2 == 0) {
                    grid[row][col] = WALL;
                } else {
                    grid[row][col] = PATH;
                }
            }
        }

        for (Edge edge : edges) {
            int root1 = unionFind.find(edge.cell1);
            int root2 = unionFind.find(edge.cell2);

            if (root1 != root2) {
                unionFind.union(root1, root2);
                removeWall(edge.cell1, edge.cell2, width, grid);
            }
        }

        addAdditionalConnections(unionFind, width, grid, edges);
        addSpecialSurfaces(grid, TRAP);
        addSpecialSurfaces(grid, COIN);

        return new Maze(height * 2 + 1, width * 2 + 1, grid);
    }

    private void initializeMaze(int height, int width, List<Edge> edges) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (col < width - 1) {
                    edges.add(new Edge(row * width + col, row * width + (col + 1)));
                }
                if (row < height - 1) {
                    edges.add(new Edge(row * width + col, (row + 1) * width + col));
                }
            }
        }
    }

    private void removeWall(int cell1, int cell2, int width, Cell[][] grid) {
        int row1 = (cell1 / width) * 2 + 1;
        int col1 = (cell1 % width) * 2 + 1;
        int row2 = (cell2 / width) * 2 + 1;
        int col2 = (cell2 % width) * 2 + 1;

        if (col1 == col2) {
            if (row1 < row2) {
                grid[row1 + 1][col1] = PATH;
            } else {
                grid[row1 - 1][col1] = PATH;
            }
        } else if (row1 == row2) {
            if (col1 < col2) {
                grid[row1][col1 + 1] = PATH;
            } else {
                grid[row1][col1 - 1] = PATH;
            }
        }
    }

    private void addAdditionalConnections(UnionFind unionFind, int width, Cell[][] grid, List<Edge> edges) {
        final double ratio = 0.2;
        int additionalEdges = (int) (edges.size() * ratio);

        for (int i = 0; i < additionalEdges; i++) {
            Edge randomEdge = edges.get(random.nextInt(edges.size()));
            int cell1 = randomEdge.cell1;
            int cell2 = randomEdge.cell2;

            int root1 = unionFind.find(cell1);
            int root2 = unionFind.find(cell2);

            if (root1 == root2) {
                removeWall(cell1, cell2, width, grid);
            }
        }
    }

    static class Edge {
        int cell1;
        int cell2;

        Edge(int cell1, int cell2) {
            this.cell1 = cell1;
            this.cell2 = cell2;
        }
    }

    static class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int cord) {
            if (parent[cord] == cord) {
                return cord;
            }

            parent[cord] = find(parent[cord]);
            return parent[cord];
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                parent[rootX] = rootY;
            }
        }
    }
}
