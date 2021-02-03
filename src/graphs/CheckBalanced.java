package graphs;

import graphs.commons.Tree;

class CheckBalanced {
    public static void main(String[] args) {
        final int[] array = new int[21];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        // Generate binary tree
        final Tree binaryTree = MinimalTree.fromSortedArray(array);
        assert isBalanced(binaryTree);

        Tree notBinaryTree = new Tree(1);
        notBinaryTree.left = new Tree(2);
        notBinaryTree.left.left = new Tree(2);
        notBinaryTree.left.right = new Tree(2);
        notBinaryTree.left.left.left = new Tree(2);
        assert !isBalanced(notBinaryTree);

    }

    static int getHeight(Tree tree) {
        if (tree == null) {
            return 0;
        }
        // The height of a node at a level is the height of the node below it plus 1.
        // Binary tree has two leaves at most, so height of the deepest tree is counted
        return Math.max(getHeight(tree.left), getHeight(tree.right)) + 1;
    }

    static Boolean isBalanced(Tree tree) {
        return Math.abs(getHeight(tree.left) - getHeight(tree.right)) <= 1;
    }

}
