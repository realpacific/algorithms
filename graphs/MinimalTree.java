package graphs;

import graphs.commons.Tree;

/**
 * Given a sorted (increasing order) array with unique integer elements, write
 * an algorithm to create a binary search tree with minimal height.
 */
class MinimalTree {
    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        System.out.println(createMinimalTree(array, 0, array.length - 1));
    }

    public static Tree fromSortedArray(int[] array) {
        return createMinimalTree(array, 0, array.length - 1);
    }

    private static Tree createMinimalTree(int[] array, int start, int end) {
        if (start > end)
            return null;
        int middle = (int) ((start + end) / 2);
        Tree tree = new Tree(array[middle]);
        tree.left = createMinimalTree(array, start, middle - 1);
        tree.right = createMinimalTree(array, middle + 1, end);
        return tree;
    }
}
