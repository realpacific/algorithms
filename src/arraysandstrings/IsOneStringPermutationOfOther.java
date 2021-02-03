
package arraysandstrings;

import java.util.Arrays;

public class IsOneStringPermutationOfOther {
    public static void main(String[] args) {
        assert !isOneStringPermutationOfOther("abc", "dog");
        assert isOneStringPermutationOfOther("god", "dog");
        assert !isOneStringPermutationOfOther("god   ", "dog");
        assert !isOneStringPermutationOfOther("123", "dog");
    }

    private static boolean isOneStringPermutationOfOther(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] content1 = str1.toCharArray();
        char[] content2 = str2.toCharArray();

        Arrays.sort(content1);
        Arrays.sort(content2);

        return Arrays.toString(content1).equals(Arrays.toString(content2));
    }
}
