package sortingandsearch;

/**
 * Given a sorted array of strings that is interspersed with empty strings, write a
 * method to fnd the location of a given string.
 */
public class SparseSearch {
    public static void main(String[] args) {
        String[] array = {"at", "", "", "", "ball", "", "", "", "cat", "", "", "", "", "dog", ""};
        assert search(array, "at") == 0;
        assert search(array, "dog") == 13;
        assert search(array, "cat") == 8;
        assert search(array, "ball") == 4;
        assert search(array, "zzz") == -1;

        String[] array2 = {"at", "", "", "", "", "", "cat", "", "", "", "", "dog"};
        assert search(array2, "dog") == 11;
        assert search(array2, "at") == 0;

    }

    private static int search(String[] array, String x) {
        return binarySearchIgnoringEmpties(array, x, 0, array.length - 1);
    }

    private static int binarySearchIgnoringEmpties(String[] array, String x, int low, int high) {
        if (low >= high) {
            return -1;
        }
        int mid = (low + high) / 2;

        // If the middle string is empty, we will find the closest non-empty string by going left and right
        // When found, we will use that to find whether we need to take left or right
        if (array[mid].length() == 0) {
            int goLeft = mid - 1;
            int goRight = mid + 1;
            while (true) {
                // Condition to exit
                if (goLeft < 0 || goRight >= array.length) {
                    break;
                }
                if (array[goLeft].length() > 0) {
                    mid = goLeft;
                    break;
                } else if (array[goRight].length() > 0) {
                    mid = goRight;
                    break;
                }
                goLeft--;
                goRight++;
            }
        }

        if (array[mid].compareTo(x) > 0) {
            // mid is bigger than the x so go left
            return binarySearchIgnoringEmpties(array, x, low, mid - 1);
        } else if (array[mid].compareTo(x) < 0) {
            // mid is lesser than the x so go right
            return binarySearchIgnoringEmpties(array, x, mid + 1, high);
        } else {
            // WE BLOODY FOUND IT
            return mid;
        }
    }
}
