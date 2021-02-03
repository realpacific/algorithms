package arraysandstrings;

import java.util.Arrays;

public class UniqueCharacters {

    public static void main(String[] args) {
        assert !hasUniqueCharacters("abca");
        assert hasUniqueCharacters("abcd");
        assert hasUniqueCharacters("1234567890qwertyuiopasdfghjklzxcvbnm");

        assert !hasUniqueCharactersSort("abca");
        assert hasUniqueCharactersSort("abcd");
        assert hasUniqueCharactersSort("1234567890qwertyuiopasdfghjklzxcvbnm");

    }

    private static boolean hasUniqueCharacters(String str) {
        long count = str.chars().distinct().count();
        int actualSize = str.length();
        return count == actualSize;
    }


    private static boolean hasUniqueCharactersSort(String str) {
        char[] contents = str.toCharArray();
        Arrays.sort(contents);
        boolean[] store = new boolean[128];
        for (int c : contents) {
            if (store[c]) {
                return false;
            }
            store[c] = true;
        }
        return true;
    }
}
