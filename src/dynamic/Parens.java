package dynamic;

import java.util.*;

/**
 * Implement an algorithm to print all valid (i.e., properly opened and closed) combinations
 * of n pairs of parentheses
 */
public class Parens {
    public static void main(String[] args) {
        Set<String> result = generateParens(3);
        for (String s : result) {
            System.out.println(s);
        }

        System.out.println("-------");

        int count = 3;
        List<String> result2 = new ArrayList<>();
        char[] str = new char[count * 2];
        generateParens(result2, count, count, str, 0);
        for (String s : result2) {
            System.out.println(s);
        }
    }

    /**
     * To achieve result for n=2, we need to add parens in every open parens of n=1 & also in front
     * n=1: ()
     * n=2: () (), ( () )
     * n=3: () ()(), ( () )(), ()( () ), ( () ()), (( () ))
     */
    static Set<String> generateParens(int remaining) {
        Set<String> set = new HashSet<>();
        if (remaining == 0) {
            set.add("");
            return set;
        } else {
            Set<String> prev = generateParens(remaining - 1);
            for (String str : prev) {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == '(') {
                        String s = insertParenPairAt(str, i + 1);
                        set.add(s);
                    }
                }
                // One of the result can be achieved by inserting parens at the beginning of string
                set.add("()" + str);
            }
        }
        return set;
    }

    private static String insertParenPairAt(String str, int index) {
        return str.substring(0, index) + "()" + str.substring(index);
    }


    private static void generateParens(List<String> result, int lRemaining, int rRemaining, char[] str, int index) {
        if (lRemaining < 0 || rRemaining < lRemaining) return;
        if (lRemaining == 0 && rRemaining == 0) {
            result.add(String.copyValueOf(str));
            return;
        }

        str[index] = '(';
        generateParens(result, lRemaining - 1, rRemaining, str, index + 1);

        str[index] = ')';
        generateParens(result, lRemaining, rRemaining - 1, str, index + 1);
    }
}
