import java.util.*;
import java.lang.*;
import java.io.*;

class SudokuSolver {
    static int[][] result;

    static int[] findEmptyLocations(int[][] sudoku) {
        for (int c = 0; c < 9; c++) {
            for (int r = 0; r < 9; r++) {
                if (sudoku[c][r] == 0) {
                    return new int[] { c, r };
                }
            }
        }
        return new int[] { -1 , -1 };
    }

    static boolean isUsedInRow(int[][] sudoku, int num, int row) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][row] == num)
                return true;
        }
        return false;
    }

    static boolean isUsedInCol(int[][] sudoku, int num, int col) {
        for (int j = 0; j < 9; j++) {
            if (sudoku[col][j] == num)
                return true;
        }
        return false;
    }

    static boolean isUsedInArea(int[][] sudoku, int num, int col, int row) {
        int startingPointCol = (col/3) * 3;
        int startingPointRow = (row/3) * 3;

        for (int i = startingPointCol; i < startingPointCol + 3; i++) {
            for (int j = startingPointRow; j < startingPointRow + 3; j++) {
                if (sudoku[i][j] == num)
                    return true;
            }
        }
        return false;
    }

    static boolean solver(int[][] sudoku) {
        int[] pos = findEmptyLocations(sudoku);

        if (pos[0] == -1) {
            result = sudoku;
            return true;
        }


        int col = pos[0];
        int row = pos[1];

        for (int val = 1; val <= 9; val++ ) {
            if (!isUsedInCol(sudoku, val, col) && !isUsedInRow(sudoku, val, row) && !isUsedInArea(sudoku, val, col, row)) {
                sudoku[col][row] = val;
                if (solver(sudoku))
                    return true;

                sudoku[col][row] = 0;
            }
        }

        return false;
    }

    public static void main (String[] args) {
	    Scanner in = new Scanner(System.in);
        int testCaseAmount = in.nextInt();

        for (int t = 0; t < testCaseAmount; t++) {
            int[][] sudoku = new int[9][9];
            for (int i = 0; i < 9; i++)
                for (int j = 0; j < 9; j++)
                    sudoku[i][j] = in.nextInt();

            if (solver(sudoku)) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        System.out.print(result[i][j] + " ");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solutions!");
            }


        }
    }
}
