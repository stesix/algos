import java.util.*;
import java.lang.*;
import java.io.*;

class RatInAMaze {

    static TreeSet<String> solution;

    public static ArrayList<String> printPath(int[][] m, int n) {
        solution = new TreeSet<String>();
        canMove(m, n, 0, 0, "");
        ArrayList<String> moves = new ArrayList<String>(solution);

        return moves;
    }

    static void canMove(int m[][], int n, int i, int j, String moves) {
        if ( i < 0 || j < 0 || i == n || j == n )
            return;

        if ( i == n - 1 && j == n - 1 ) {
            solution.add(moves);
            return;
        }

        if( m[i][j] == 1 ) {
            // Mark node as visited!
            m[i][j] = 2;

            canMove(m, n, i - 1, j, moves + "U");
            canMove(m, n, i + 1, j, moves + "D");
            canMove(m, n, i, j - 1, moves + "L");
            canMove(m, n, i, j + 1, moves + "R");

            // We finished to check this node completely, roll back.
            m[i][j] = 1;
        }
    }

    static void print(int[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCaseAmount = in.nextInt();

        for (int t = 0; t < testCaseAmount; t++) {
            int N = in.nextInt();
            int[][] maze = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    maze[i][j] = in.nextInt();
                }
            }

            //print(maze);
            ArrayList<String> s = printPath(maze, N);
            String[] solutions = s.toArray(new String[0]);

            for (String sol: solutions)
                System.out.print(sol + " ");
            System.out.println("");
        }
    }
}
