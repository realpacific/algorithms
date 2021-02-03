package sortingandsearch;

import utils.PrintUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * You are given an array-like data structure Listy which lacks a size
 * method. It does, however, have an elementAt(i) method that returns the element at index i in
 * 0(1) time. If i is beyond the bounds of the data structure, it returns -1. (For this reason, the data
 * structure only supports positive integers.) Given a Listy which contains sorted, positive integers,
 * fnd the index at which an element x occurs. If x occurs multiple times, you may return any index.
 */
public class SortedSearchNoSize {

    public static void main(String[] args) {
        Listy listy = new Listy();
        for (int i = 0; i < 10; i++) {
            listy.add(i);
        }
        assert listy.indexOf(1) == 1;
        assert listy.indexOf(110) == -1;
        assert listy.indexOf(2) == 2;
        assert listy.indexOf(3) == 3;
        assert listy.indexOf(0) == 0;
        assert listy.indexOf(11) == -1;
    }

    static class Listy {
        private List<Integer> list;

        public Listy() {
            this.list = new ArrayList<>();
        }

        public void add(int value) {
            if (value < 0) {
                throw new RuntimeException("Not supported");
            }
            list.add(value);
            list.sort(Comparator.naturalOrder());
        }

        public int elementAt(int index) {
            try {
                return list.get(index);
            } catch (IndexOutOfBoundsException e) {
                return -1;
            }
        }


        public int indexOf(int value) {
            int maxValue = getIndexBeyondMax();
            return binarySearch(0, maxValue, value);
        }

        private int binarySearch(int low, int high, int value) {
            int mid;
            while (low <= high) {
                mid = (low + high) / 2;
                // Since high at first is not necessarily the maxIndex of the list, it may/may not contain any element
                int elementAtMid = elementAt(mid);
                if (elementAtMid > value || elementAtMid == -1) {
                    high = mid - 1;
                } else if (elementAtMid < value) {
                    low = mid + 1;
                } else {
                    return mid;
                }
            }
            return -1;
        }

        /**
         * Does not return the exact max index in the list
         * Returns the value just beyond the max index
         * Needs to be backtrack multiple times to get the max index in the list
         */
        private int getIndexBeyondMax() {
            PrintUtils.print(list);
            int maxIndex = 1;
            while (elementAt(maxIndex) != -1) {
                maxIndex *= 2;
            }
            System.out.println("maxIndex: " + maxIndex);
            return maxIndex;
        }
    }
}
