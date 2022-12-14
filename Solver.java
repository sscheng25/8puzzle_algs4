/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private int move;
    private MinPQ pq;
    private Searchnode init;

    private class Searchnode implements Comparable<Searchnode> {
        private Board board;
        private int move;
        private Searchnode pre;
        private int manPriority;

        public Searchnode(Board cur, int move, Searchnode p) {
            board = cur;
            this.move = move;
            pre = p;
            manPriority = cur.manhattan() + move;
        }

        public int compareTo(Searchnode that) {
            return this.manPriority - that.manPriority;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        /*
        pq = new MinPQ<>();
        pq.insert(initial);

        Board temp = initial;
        while (temp.manhattan() != 0) {
            Iterable<Board> next = temp.neighbors();
            next[0]
        }
        */
        MinPQ<Searchnode> ques = new MinPQ<>();


    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return true;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        move = pq.size();
        return move;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {

    }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}

