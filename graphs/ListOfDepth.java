package graphs;

import graphs.commons.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, design an algorithm which creates a linked list of all
 * the nodes at each depth (e.g., if you have a tree with depth D, you'll have D
 * linked lists).
 */
class ListOfDepth {
    public static void main(final String[] args) {
        final int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        // Generate binary tree
        final Tree binaryTree = MinimalTree.fromSortedArray(array);
        LinkedList<Tree> current = new LinkedList<>();
        final List<List<Tree>> result = new ArrayList<>();

        current.add(binaryTree);

        while (!current.isEmpty()) {
            result.add(current);
            final List<Tree> parents = current;

            // At each level, load the children into current level's list
            current = new LinkedList<>();
            for (final Tree t : parents) {
                if (t.left != null)
                    current.add(t.left);

                if (t.right != null)
                    current.add(t.right);
            }
        }
        for (final List<Tree> list : result) {
            System.out.println("Size: " + list.size() + "; " + list);
        }
    }
}
