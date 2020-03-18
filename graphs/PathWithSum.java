import java.util.HashMap;
import java.util.Map;

import commons.Tree;

class PathWithSum {
    static int targetSum = 8;
    static int counter = 0;

    public static void main(String[] args) {
        Tree tree = new Tree(10);
        tree.left = new Tree(5);
        tree.left.left = new Tree(3);
        tree.left.right = new Tree(1);
        tree.left.right.right = new Tree(2);
        tree.left.left.left = new Tree(3);
        tree.left.left.right = new Tree(-2);
        tree.right = new Tree(-3);
        tree.right.right = new Tree(11);

        Map<Integer, Integer> map = new HashMap<>();
        dfsAndCollect(tree, map, 0);
        assert counter == 3;
    }

    static void dfsAndCollect(Tree tree, Map<Integer, Integer> table, int level) {
        if (tree == null)
            return;
        System.out.print("level: " + level + " --> ");
        tree.printNode();

        // At each level, get the sum of levels above it and add it to the current node
        table.put(level, table.getOrDefault(level - 1, 0) + tree.data);

        table.forEach((k, v) -> {
            // Cumulative sum - targetSum = value in table at previous indices, then there exists a pairs with sum
            if (k <= level && (table.get(level) - targetSum == v)) {
                System.out.print("Eureka! at level: " + level + " with node " );
                tree.printNode();
                counter++;
            }
        });

        dfsAndCollect(tree.left, table, level + 1);
        dfsAndCollect(tree.right, table, level + 1);
    }
}