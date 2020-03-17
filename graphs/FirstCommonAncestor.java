import commons.BidirectionalTree;

class FirstCommonAncestor {
    public static void main(final String[] args) {
        // Root of the tree
        final BidirectionalTree tree = new BidirectionalTree(null, 10);

        tree.left = new BidirectionalTree(tree, 20);
        tree.right = new BidirectionalTree(tree, 80);

        tree.right.right = new BidirectionalTree(tree, 100);
        tree.left.left = new BidirectionalTree(tree.left, 30);
        tree.left.right = new BidirectionalTree(tree.left, 60);

        tree.left.left.left = new BidirectionalTree(tree.left.left, 40);
        tree.left.left.right = new BidirectionalTree(tree.left.left, 50);

        // Node two
        final BidirectionalTree q = new BidirectionalTree(tree.left.right, 70);
        tree.left.right.right = q;

        // Node one
        final BidirectionalTree p = new BidirectionalTree(tree.left.left.left, 90);
        tree.left.left.left.left = p;

        // Find the diff between two tree from the root
        int delta = depth(p) - depth(q);
        System.out.println("Diff=" + delta);

        // Move the deepest tree by the difference so that two nodes are at equal depth
        BidirectionalTree t1 = p;
        BidirectionalTree t2 = q;
        if (delta < 0) {
            t2 = goUpBy(q, delta);
        } else if (delta > 0) {
            t1 = goUpBy(p, delta);
        }
        assert commonAncestor(t1, t2).data == 20;

    }

    static BidirectionalTree goUpBy(BidirectionalTree tree, int times) {
        BidirectionalTree current = tree;
        for (int i = 1; i <= times; i++) {
            current = current.parent;
        }
        return current;
    }

    static Integer depth(BidirectionalTree tree) {
        BidirectionalTree current = tree;
        int count = 0;
        while (current.parent != null) {
            current = current.parent;
            count++;
        }
        return count;
    }

    static BidirectionalTree commonAncestor(BidirectionalTree t1, BidirectionalTree t2) {
        BidirectionalTree current1 = t1;
        BidirectionalTree current2 = t2;

        // since they are in same depth, move up to parent till the common parent is found
        while (current1 != current2) {
            current1 = current1.parent;
            current2 = current2.parent;
        }
        return current1;
    }
}