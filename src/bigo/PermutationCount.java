package bigo;

public class PermutationCount {
    public static void main(String[] args) {
        String str = "abc";
        permutation(str, 0, str.length() - 1);
    }

    private static void permutation(String str, String prefix) {
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                String remainder = str.substring(0, i) + str.substring(i + 1);
                permutation(remainder, prefix + str.charAt(i));
            }
        }
    }

    private static void permutation(String str, int l, int r) {
        if (l == r) {
            System.out.println(str);
        }
        for (int i = l; i <= r; i++) {
            String swapped = swap(str, l, i);
            permutation(swapped, l + 1, r);
        }
    }

    private static String swap(String str, int i, int j) {
        char[] arr = str.toCharArray();
        char temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
        return String.valueOf(arr);

    }
}
