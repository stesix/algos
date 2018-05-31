import java.util.*;
import java.lang.*;
import java.io.*;

class RatInAMaze {

    static String result = "";

    static boolean solver(int[][] maze, int[] currentPosition, String moves) {
        int col = currentPosition[0];
        int row = currentPosition[1];
        String lastMove = "";

        if (moves.length() > 0) {
            char[] tmp = moves.toCharArray();
            lastMove = "" + tmp[tmp.length - 1];
        }

        if (col == maze.length - 1 && row == maze.length - 1) {
            System.out.print(moves + " ");
            return true;
        }

        //System.out.println("Last move was: " + lastMove);

        if (col < maze.length - 1 && maze[col + 1][row] == 1 && lastMove != "U" ) {
            if (solver(maze, new int[] {col + 1, row}, moves + "D")) {
                return true;
            }
        }

        if (row < maze.length - 1 && maze[col][row + 1] == 1 && lastMove != "L" ) {
            if (solver(maze, new int[] {col, row + 1}, moves + "R")) {
                return true;
            }
        }

        if (col > 0 && maze[col - 1][row] == 1 && lastMove != "D" ) {
            solver(maze, new int[] {col - 1, row}, moves + "U");
        }

        if (row > 0 && maze[col][row - 1] == 1 && lastMove != "R" ) {
            solver(maze, new int[] {col, row - 1}, moves + "L");
        }

        return false;
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

            solver(maze, new int[] {0, 0}, "");
            System.out.println("");
        }
    }
}
