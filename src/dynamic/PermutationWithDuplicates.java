package dynamic;

import java.util.*;

/**
 * [Video](https://www.youtube.com/watch?v=JF4QrlUJItk)
 */
public class PermutationWithDuplicates {
    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        String str = "aabc";

        Map<Character, Integer> map = calculateFrequency(str);

        printPerms(map, "", str.length(), result);
        System.out.println(Arrays.toString(result.toArray()));
    }

    private static Map<Character, Integer> calculateFrequency(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        return map;
    }


    private static void printPerms(Map<Character, Integer> map, String prefix, int remaining, List<String> result) {
        if (remaining == 0) {
            result.add(prefix);
            return;
        }
        for (Character c : map.keySet()) {
            int count = map.get(c);
            if (count > 0) {
                map.put(c, count - 1);
                printPerms(map, prefix + c, remaining - 1, result);
                map.put(c, count);
            }
        }

    }


}
