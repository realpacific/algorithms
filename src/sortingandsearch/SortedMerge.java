package sortingandsearch;

import java.util.Arrays;

/**
 * You are given two sorted arrays, A and B, where A has a large enough buffer at the
 * end to hold B. Write a method to merge B into A in sorted order.
 */
public class SortedMerge {
    public static void main(String[] args) {
        Integer[] array1 = new Integer[12];
        // To avoid NPEs, fill the invalid values with Integer.MAX_VALUE
        for (int i = 0; i < array1.length; i++) {
            array1[i] = (i < 5) ? (i + 10) * 2 * 2 : Integer.MAX_VALUE;
        }
        Integer[] array2 = new Integer[7];
        array2[0] = 1;
        array2[1] = 3;
        array2[2] = 5;
        array2[3] = 9;
        array2[4] = 10;
        array2[5] = 12;
        array2[6] = 14;
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
        merge(array1, array2);
    }

    /**
     * To avoid shifting arrays when inserting in the middle, we merge starting from the last element of both arrays.
     * Compare last two elements of both array and place the largest on at the last
     */
    private static void merge(Integer[] arrA, Integer[] arrB) {
        // Since arrA's empty elements are denoted by Integer.MAX, we find the last valid value of array
        int lastIndexA = findLastFilledIndex(arrA);
        int lastIndexB = arrB.length - 1;

        // The total size of merged array (valid elements of A + elements of B)
        int sizeOfMergedArray = lastIndexA + 1 + arrB.length - 1;

        while (sizeOfMergedArray >= 0) {
            if (lastIndexA >= 0 && arrA[lastIndexA] > arrB[lastIndexB]) {
                // since the last element of A is larger of the two, put it at the last
                arrA[sizeOfMergedArray] = arrA[lastIndexA];
                // decrement A's index by 1
                lastIndexA--;
            } else {
                // B is larger so decrement B by 1
                arrA[sizeOfMergedArray] = arrB[lastIndexB];
                lastIndexB--;
            }
            sizeOfMergedArray--;
        }

        System.out.println(java.util.Arrays.toString(arrA));
    }

    private static int findLastFilledIndex(Integer[] arrA) {
        int current = 0;
        for (Integer i : arrA) {
            if (i == Integer.MAX_VALUE) break;
            current++;
        }
        return current - 1;
    }
}
