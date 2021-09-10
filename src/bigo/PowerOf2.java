package bigo;


public class PowerOf2 {
    public static void main(String[] args) {
        powerOf2(64);
//        powerOf2Easy(4);
    }

    static int powerOf2(int n) {
        if (n < 1) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            int prev = powerOf2(n / 2);
            int curr = prev * 2;
            System.out.println(curr);
            return curr;
        }
    }

    static void powerOf2Easy(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println((int) Math.pow(2, i));
        }
    }

}
