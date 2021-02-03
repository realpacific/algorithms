package graphs;

import graphs.commons.BidirectionalTree;

class CheckIfSubTree {
    public static void main(String[] args) {
        // bigger tree
        final BidirectionalTree biggerTree = new BidirectionalTree(null, 10);
        biggerTree.left = new BidirectionalTree(biggerTree, 20);
        biggerTree.right = new BidirectionalTree(biggerTree, 80);
        biggerTree.right.right = new BidirectionalTree(biggerTree, 100);

        // Subtree
        final BidirectionalTree subTree = new BidirectionalTree(biggerTree.left, 30);
        subTree.left = new BidirectionalTree(subTree, 40);
        subTree.right = new BidirectionalTree(subTree, 50);
        subTree.right.right = new BidirectionalTree(subTree.right, 70);
        subTree.left.left = new BidirectionalTree(subTree.left, 90);

        biggerTree.left.left = subTree;
        assert traverse(biggerTree, subTree);

        // Test for not subtree
        final BidirectionalTree tree3 = new BidirectionalTree(null, 30);
        tree3.left = new BidirectionalTree(tree3, 40);
        tree3.right = new BidirectionalTree(tree3, 50);
        assert !traverse(biggerTree, tree3);

        // Smaller has one more extra node
        subTree.left.left.left = new BidirectionalTree(subTree.left.left, 55);
        assert !traverse(biggerTree, subTree);

    }

    static boolean traverse(BidirectionalTree bigTree, BidirectionalTree smallTree) {
        if (bigTree == null) {
            return false;
        } else if (bigTree.data.equals(smallTree.data) && checkIfSubtree(bigTree, smallTree)) {
            // If same data found, then check if they are subtree
            return true;
        }
        // Keep traversing until root of small tree has same data as that of big tree
        return traverse(bigTree.left, smallTree) || traverse(bigTree.right, smallTree);
    }

    private static boolean checkIfSubtree(BidirectionalTree bigTree, BidirectionalTree smallTree) {
        if(smallTree == null) {
            return true;
        }
        // Big tree is already finished but small tree is not
        if (bigTree == null && smallTree != null) {
            return false;
        }
        // If their data is not same, then return false
        if (smallTree != null && !smallTree.data.equals(bigTree.data)) {
            return false;
        }
        return checkIfSubtree(bigTree.left, smallTree.left) && checkIfSubtree(bigTree.right, smallTree.right);
    }
}
