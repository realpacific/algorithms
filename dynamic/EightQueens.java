package dynamic;

import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EightQueens {
    private static final int GRID = 4;

    public static void main(String[] args) {
        Integer[] board = new Integer[GRID];

        Arrays.fill(board, 0);

        List<Integer[]> result = new ArrayList<>();

        placeQueens(0, board, result);

        Utils.printArr(result);

    }

    static void placeQueens(int row, Integer[] board1D, List<Integer[]> results) {
        if (row == GRID) {
            results.add(board1D.clone());
        } else {
            for (int col = 0; col < GRID; col++) {
                if (checkValid(board1D, row, col)) {
                    board1D[row] = col;
                    placeQueens(row + 1, board1D, results);
                }
            }
        }
    }

    /**
     * We have represented the 2D chess board as 1D so
     * index ----> row
     * column ---> board1d[row]
     */
    private static boolean checkValid(Integer[] board1D, int row1, int col1) {

        // row2 in 0..row1 because rows beyond row1 has not been filled yet.
        for (int row2 = 0; row2 < row1; row2++) {
            // Getting column by using formula
            int col2 = board1D[row2];

            if (col1 == col2) {
                return false;
            }

            // Diagonal check
            int colDistance = Math.abs(col1 - col2);
            int rowDistance = Math.abs(row1 - row2);
            // in diagonal, row & column are equal.
            if (colDistance == rowDistance) {
                return false;
            }
        }

        return true;
    }

}
