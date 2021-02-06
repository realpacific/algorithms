package dynamic;

import java.util.Stack;

public class TowerOfHanoi {

    public static void main(String[] args) {
        int numberOfDisks = 20;

        Tower[] towers = new Tower[3];
        for (int i = 0; i < 3; i++) {
            towers[i] = new Tower(i);
        }

        for (int i = numberOfDisks; i > 0; i--) {
            towers[0].add(i);
        }


        printAll(towers);

        towers[0].moveDisks(numberOfDisks, towers[2], towers[1]);

        printAll(towers);
    }

    private static void printAll(Tower[] towers) {
        for (Tower tower : towers) {
            tower.printContents();
        }
        System.out.println("-------------------");
        System.out.println("");
    }

    public static class Tower {
        private Stack<Integer> disks;
        int index;

        public Tower(int index) {
            this.index = index;
            this.disks = new Stack<>();
        }

        public void add(int i) {
            if (!disks.isEmpty() && disks.peek() <= i) {

            } else {
                disks.push(i);
            }
        }

        public void moveTopTo(Tower destination) {
            Integer pop = disks.pop();
            destination.add(pop);
        }

        public void moveDisks(int n, Tower destination, Tower buffer) {
            if (n > 0) {
                moveDisks(n - 1, buffer, destination);
                moveTopTo(destination);
                buffer.moveDisks(n - 1, destination, this);
            }
        }

        public void printContents() {
            System.out.print("TOWER " + index + ": ");
            for (Integer disk : disks) {
                System.out.print(disk + " ");
            }
            System.out.println();
        }

    }
}
