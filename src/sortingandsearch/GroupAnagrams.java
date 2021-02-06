package sortingandsearch;

import utils.PrintUtils;

import java.util.*;

/**
 * Write a method to sort an array ot strings so that all tne anagrams are next to each other.
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        String[] array1 = {"bac", "dab", "race", "evil", "acre", "live", "abc", "cab", "vile"};
        sortUsingComparator(array1);

        String[] array2 = {"bac", "dab", "race", "evil", "acre", "live", "abc", "cab", "vile"};
        groupAnagram(array2);
    }

    private static void sortUsingComparator(String[] array) {
        Arrays.sort(array, new AnagramComparator());
        System.out.println(Arrays.toString(array));
    }

    /**
     * Anagrams when sorted will have same hash value
     * eg abc, bac, cab ---(sort)---> abc
     * So using THE SORTED value as key in HashMap, and its ORIGINAL value as value
     */
    private static void groupAnagram(String[] array) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : array) {
            String sorted = sort(s);
            List<String> value = map.computeIfAbsent(sorted, (key) -> new ArrayList<>());
            value.add(s);
        }
        List<String> result = new ArrayList<>();
        for (List<String> list : map.values()) {
            result.addAll(list);
        }
        PrintUtils.print(result);
    }

    private static String sort(String s) {
        char[] characters = s.toCharArray();
        // IT IS IMPORTANT TO PASS IN [characters] AFTER ASSIGNMENT TO SORT AS IT WILL BE IN PLACE CHANGE
        Arrays.sort(characters);
        return new String(characters);
    }

    private static class AnagramComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return sort(o1).compareTo(sort(o2));
        }

        private String sort(String s) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }
    }
}
