package graphs;

import java.util.Random;

class PickRandomNode {
    public static void main(String[] args) {
        Random random = new Random(300);
        
        RandomNodeTree tree = new RandomNodeTree(60);
        for (int i = 0; i < 20; i++) {
            tree.insert(random.nextInt(300));
        }

        System.out.println(tree.size);

        for (int i = 0; i < 20; i++) {
            System.out.println(tree.getRandomNode());
        }

    }

    static class RandomNodeTree {
        private RandomNodeTree left;
        private RandomNodeTree right;
        private int size;
        private int data;

        public RandomNodeTree(int data) {
            this.data = data;
            size = 1;
        }

        public void insert(int d) {
            if (this.data <= d) {
                if (left == null)
                    left = new RandomNodeTree(d);
                else
                    left.insert(d);
            } else {
                if (right == null)
                    right = new RandomNodeTree(d);
                else
                    right.insert(d);
            }
            size++;
        }

        public RandomNodeTree getRandomNode() {
            int sizeOfLeft = left == null ? 0 : left.size;
            Random random = new Random();
            int index = random.nextInt(size);
            if (index < sizeOfLeft) {
                return left.getRandomNode();
            } else if (index == sizeOfLeft) {
                return this;
            }
            return right.getRandomNode();
        }

        @Override
        public String toString() {
            return String.format("RandomNodeTree(%s)", data);
        }

    }
}
