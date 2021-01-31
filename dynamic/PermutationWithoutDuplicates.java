package dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationWithoutDuplicates {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(getPermutation("abcd").toArray()));
        System.out.println(Arrays.toString(getPermutation("abc").toArray()));

        ArrayList<String> result = new ArrayList<>();
        getPermutation("", "abcd", result);
        System.out.println(Arrays.toString(result.toArray()));

    }

    static List<String> getPermutation(String s) {
        if (s == null) {
            return null;
        }
        List<String> result = new ArrayList<>();

        if (s.length() == 0) {
            result.add("");
            return result;
        }

        char firstChar = s.charAt(0);
        String remainder = s.substring(1);
        List<String> permutation = getPermutation(remainder);

        // Permutation of abc is inserting "c" in every position of permutation of "ab"
        for (String word : permutation) {
            // We have to insert firstChar even after the last character of word so "<=" is important here
            for (int i = 0; i <= word.length(); i++) {
                String inserted = insertChartAt(firstChar, i, word);
                result.add(inserted);
            }
        }
        return result;
    }

    private static String insertChartAt(char firstChar, int i, String str) {
        return str.substring(0, i) + firstChar + str.substring(i);
    }

    private static void getPermutation(String prefix, String remainder, List<String> result) {
        //  if remainder is empty then we have full length eg "abc" in prefix
        if (remainder.length() == 0) {
            result.add(prefix);
        }

        for (int i = 0; i < remainder.length(); i++) {
            String ahead = remainder.substring(0, i);
            String back = remainder.substring(i + 1);
            char removed = remainder.charAt(i);
            getPermutation(prefix + removed, ahead + back, result);
        }
    }
}
