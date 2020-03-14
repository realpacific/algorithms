import commons.Tree;
/**
 * left <= current < right
 */
class CheckBST {

    public static void main(String[] args) {

        Tree tree1 = new Tree(25);
        tree1.left = new Tree(20);
        tree1.left.left = new Tree(16);
        tree1.left.right = new Tree(22);
        tree1.right = new Tree(30);
        tree1.right.left = new Tree(29);
        tree1.right.right = new Tree(32);
        CheckBST bst = new CheckBST();
        System.out.println(bst.checkBST(tree1, null, null));

        Tree tree2 = new Tree(10);
        tree2.left = new Tree(22);
        tree2.right = new Tree(2);
        CheckBST bst2 = new CheckBST();
        System.out.println(bst2.checkBST(tree2, null, null));

    }

    boolean checkBST(Tree tree, Integer min, Integer max) {
        if (tree == null)
            return true;
        
         
        if((min != null && min > tree.data) || (max != null && max <= tree.data)) {
            return false;
        }
        
        // OR used so that both left & right gets checked; if one fails, then quit with false result
        if (!(checkBST(tree.left, min, tree.data)) || !(checkBST(tree.right, tree.data, max))) {
            return false;
        }
        return true;
    }
}