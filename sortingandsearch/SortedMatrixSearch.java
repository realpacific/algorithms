package sortingandsearch;

public class SortedMatrixSearch {
    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = i * j + j * 3 + 10;
            }
        }
        for (int[] row : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(row[j] + "  ");
            }
            System.out.println();
        }

        search(matrix, 20);
        search(matrix, 24);
        search(matrix, 11);
        search(matrix, 0);
    }

    private static int[] search(int[][] matrix, int value) {
        System.out.println("\n\nSearching for: " + value);
        int maxRowIndex = matrix.length - 1;
        int maxColIndex = matrix[0].length - 1;
        // Start from (0, c)
        int rowIndex = 0;
        int colsIndex = maxColIndex;

        // Does not exist if the value is less than (0,0)
        if (value < matrix[0][0]) {
            System.out.println(value + " NOT FOUND if check");
            return null;
        }

        while (rowIndex <= maxRowIndex && colsIndex >= 0) {
            System.out.println("Current: " + matrix[rowIndex][colsIndex]);
            if (matrix[rowIndex][colsIndex] == value) {
                System.out.format("%s is at index (%s, %s)", value, rowIndex, colsIndex);
                return new int[]{rowIndex, colsIndex};

            } else if (matrix[rowIndex][colsIndex] > value) {
                colsIndex--;
            } else if (matrix[rowIndex][colsIndex] < value) {
                rowIndex++;
                // From current column, remove the right columns to limit the search
                // Since the columns can go out of bound, use this
                colsIndex = Math.min(colsIndex + 1, maxColIndex);
            }
        }
        System.out.println(value + " NOT FOUND\n");
        return null;
    }

    private static void binarySearch(int[][] matrix, int value, int low, int high) {

    }
}
