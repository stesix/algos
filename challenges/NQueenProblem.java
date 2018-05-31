import java.util.*;
import java.lang.*;
import java.io.*;

class NQueenProblem {

    public static boolean isValid(int[] queens, int n) {
        for (int i = 0; i < n; i++) {
            if (queens[i] == queens[n])
                // The queens are on the same column
                return false;

            int absDiff = Math.abs(queens[i] - queens[n]);
            if ( absDiff == (n - i))
                // The queens are not on the same diagonals
                return false;
        }
        return true;
    }

    static void solve(int[] queens, int N, int q) {
        if (N == q) {
            System.out.print("[");
            for (int i = 0; i < q; i++) {
                System.out.print( (queens[i] + 1) + " ");
            }
            System.out.print("] ");
            return;
        }

        for (int i = 0; i < N; i++) {
            //System.out.println("Trying to position Queen " + q + " in " + i);
            queens[q] = i;
            if (isValid(queens, q))
                solve(queens, N, q + 1);
        }
    }

    public static void main (String[] args) {
	    Scanner in = new Scanner(System.in);
        int testCaseAmount = in.nextInt();

        for (int i = 0; i < testCaseAmount; i++) {
            int N = in.nextInt();
            if (N != 1 && N < 4)
                System.out.print("-1");
            else
                solve(new int[N], N, 0);
            System.out.println("");
        }
    }
}
