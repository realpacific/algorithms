package sortingandsearch;

/**
 * Imagine you are reading in a stream of integers. Periodically, you wish
 * to be able to look up the rank of a number x (the number of values less than or equal to x).
 * Implement the data structures and algorithms to support these operations. That is, implement
 * the method track(int x), which is called when each number is generated, and the method getRankOfNumber(int x),
 * which returns the number of values less than or equal to x (not including x itself).
 */
public class RankFromStream {
    public static void main(String[] args) {
        RankNode node = new RankNode(5);
        node.insert(1);

        node.insert(4);
        assert node.getRankOf(5) == 2;

        node.insert(6);
        assert node.getRankOf(6) == 3;

        node.insert(7);
        assert node.getRankOf(7) == 4;

        node.insert(8);
        assert node.getRankOf(8) == 5;

        node.insert(0);
        assert node.getRankOf(0) == 0;

        node.insert(3);
        assert node.getRankOf(3) == 2;

        node.insert(2);
        assert node.getRankOf(2) == 2;
        assert node.getRankOf(3) == 3;
        assert node.getRankOf(100) == -1;
        assert node.getRankOf(-200) == -1;

        System.out.println(node);

    }

    static class RankNode {
        RankNode left, right;
        int data;
        int rank = 0;

        public RankNode(int data) {
            this.data = data;
        }

        void insert(int d) {
            if (d <= data) {
                if (left == null) left = new RankNode(d);
                else left.insert(d);
                // insert into binary tree and keep count of how many nodes is in its left
                rank++;
            } else {
                if (right == null) right = new RankNode(d);
                else right.insert(d);
            }
        }


        int getRankOf(int d) {
            if (d == data) {
                return rank;
            } else if (d < data) {
                if (left == null) return -1;
                return left.getRankOf(d);
            } else {
                int rankOf = (right == null) ? -1 : right.getRankOf(d);
                if (rankOf == -1) return -1;
                return rank + rankOf + 1;
            }
        }

        @Override
        public String toString() {
            return String.format("RankNode(%d l=%s, r=%s)", data, left, right);
        }
    }
}
