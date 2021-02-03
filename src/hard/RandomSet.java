package hard;

import java.util.Arrays;

public class RandomSet {

    public static void main(String[] args) {
        int[] sets = new int[32];
        for (int i = 0; i < sets.length; i++) {
            sets[i] = i;
        }
        pickMSubset(sets, 28);
    }

    private static void pickMSubset(int[] sets, int m) {
        int[] pick = new int[m];

        for (int i = 0; i < pick.length; i++) {
            pick[i] = sets[i];
        }

        for (int i = m; i < sets.length; i++) {
            int k = rand(0, i);
            if (k < m) {
                pick[k] = sets[i];
            }
        }
        print(sets);
        print(pick);
    }

    private static void print(int[] pick) {
        System.out.println(Arrays.toString(pick));
    }

    private static int rand(int low, int high) {
        return low + (int) (Math.random() * (high - low + 1));
    }
}
