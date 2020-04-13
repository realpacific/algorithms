package dynamic;

public class Coins {
    public static void main(String[] args) {
        int[] coins = {25, 10, 5, 1};
        int amount = 10;
        System.out.println(calculateChange(coins, amount, 0));
    }

    private static int calculateChange(int[] coins, int amount, int index) {
        if (index >= coins.length - 1) return 1;
        int currentCoin = coins[index];
        int ways = 0;
        for (int i = 0; i * currentCoin <= amount; i++) {
            int remaining = amount - (i * currentCoin);
            ways += calculateChange(coins, remaining, index + 1);
        }
        return ways;
    }
}
