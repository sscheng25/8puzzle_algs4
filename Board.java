import java.util.LinkedList;
import java.util.List;

public class Board {

    private int[][] tiles;
    private int N;
    private int blankRow;
    private int blankCol;
    private int ham;
    private int man;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new NullPointerException();
        }
        N = tiles.length;
        this.tiles = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int m = 0; m < N; m++) {
                this.tiles[i][m] = tiles[i][m];
                if (tiles[i][m] == 0) {
                    blankCol = m;
                    blankRow = i;
                }

                if (tiles[i][m] != 0 && tiles[i][m] != i + N * m + 1) {
                    ham++;
                }
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(N).append("\n");
        for (int i = 0; i < N; i++) {
            for (int m = 0; m < N; m++) {
                sb.append(String.format("%2d ", tiles[i][m]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return N;
    }

    // number of tiles out of place
    public int hamming() {
        return ham;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        man = 0;
        for (int i = 0; i < N; i++) {
            for (int m = 0; m < N; m++) {
                int value = tiles[i][m] - 1;
                int row = value / N;
                int col = value % N;
                man = man + Math.abs(row - i) + Math.abs(col - m);
            }
        }
        return man;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return man == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        if (that.dimension() != this.dimension()) return false;
        for (int i = 0; i < N; i++) {
            for (int m = 0; m < N; m++) {
                if (this.tiles[i][m] != that.tiles[i][m]) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> ls = new LinkedList<>();

        if (blankRow > 0) {
            int[][] up = tiles.clone();
            int temp = up[blankRow][blankCol];
            up[blankRow][blankCol] = up[blankRow - 1][blankCol];
            up[blankRow - 1][blankCol] = temp;
            ls.add(new Board(up));
        }
        if (blankRow < N - 1) {
            int[][] down = tiles.clone();
            int temp = down[blankRow][blankCol];
            down[blankRow][blankCol] = down[blankRow + 1][blankCol];
            down[blankRow + 1][blankCol] = temp;
            ls.add(new Board(down));
        }
        if (blankCol > 0) {
            int[][] left = tiles.clone();
            int temp = left[blankRow][blankCol];
            left[blankRow][blankCol] = left[blankRow][blankCol - 1];
            left[blankRow][blankCol - 1] = temp;
            ls.add(new Board(left));
        }
        if (blankCol < N - 1) {
            int[][] right = tiles.clone();
            int temp = right[blankRow][blankCol];
            right[blankRow][blankCol] = right[blankRow][blankCol + 1];
            right[blankRow][blankCol + 1] = temp;
            ls.add(new Board(right));
        }

        return ls;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twin = tiles.clone();
        int row = 0;
        if (row == blankRow) {
            row++;
        }
        int temp = twin[row][0];
        twin[row][0] = twin[row][1];
        twin[row][1] = temp;

        Board twins = new Board(twin);
        return twins;
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }

}
