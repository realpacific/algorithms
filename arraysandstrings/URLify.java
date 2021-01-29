package arraysandstrings;

import java.util.Arrays;
import java.util.stream.Collectors;

public class URLify {
    public static void main(String[] args) {
        assert urlify("Mr John Smith     ").equals("Mr%20John%20Smith");
    }

    private static String urlify(String str) {
        String[] arr = str.split(" ");
        String collect = Arrays.stream(arr).filter(s -> !s.isEmpty()).collect(Collectors.joining("%20"));
        System.out.println(collect);
        return collect;
    }
}
