package sortingandsearch;

import java.util.Arrays;

public class PeaksAndValley {

    public static void main(String[] args) {
        arrangePeakAndValley(new Integer[]{5, 8, 6, 2, 3, 4, 6, 7});
        arrangePeakAndValleyOptimal(new Integer[]{5, 8, 6, 2, 3, 4, 6, 7});
        arrangePeakAndValleyOptimal(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        arrangePeakAndValleyOptimal(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        arrangePeakAndValleyOptimal(new Integer[]{1, 2});
    }

    /**
     * Sort and swap method
     */
    static void arrangePeakAndValley(Integer[] arr) {
        Arrays.sort(arr);
        for (int i = 1; i < arr.length - 1; i += 2) {
            swap(arr, i, i + 1);
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void swap(Integer[] arr, int i, int i1) {
        int temp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = temp;
    }

    static void arrangePeakAndValleyOptimal(Integer[] arr) {
        if (arr.length <= 2) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        for (int i = 1; i < arr.length - 1; i += 2) {
            moveLargestToCenter(arr, i);
        }
        System.out.println(Arrays.toString(arr));
    }


    private static void moveLargestToCenter(Integer[] arr, int current) {
        int centralValue1 = arr[current];
        // Move the largest of prev & current to center
        if (centralValue1 < arr[current - 1]) {
            arr[current] = arr[current - 1];
            arr[current - 1] = centralValue1;
        }

        // Move the largest of current & next to center IF AVAILABLE
        int centralValue2 = arr[current];
        if ((current + 1) < arr.length && centralValue2 < arr[current + 1]) {
            arr[current] = arr[current + 1];
            arr[current + 1] = centralValue2;
        }
    }

}
