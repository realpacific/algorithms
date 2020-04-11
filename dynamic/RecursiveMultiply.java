package dynamic;

public class RecursiveMultiply {
    public static void main(String[] args) {
        assert multiply(10, 10) == 100;
        assert multiply(10, 0) == 0;
        assert multiply(11, 10) == 110;
        assert multiply(11, 11) == 121;
        assert multiply(1, 1) == 1;
        assert multiply(2, 2) == 4;
    }

    static int multiply(int val1, int val2) {
        int bigger = Math.max(val1, val2);
        int smaller = Math.min(val1, val2);
        if (smaller == 0) {
            return 0;
        }
        int sum = 0;
        boolean isEven = (smaller % 2 == 0);

        // 20,11 ==> 20 * (10 + 1) ==> 20 * (5 * 2 + 1)
        int numberOfLoops = isEven ? smaller / 2 : (smaller - 1) / 2;

        for (int i = 0; i < numberOfLoops; i++) {
            sum += bigger;
        }
        sum = sum << 1;
        if (!isEven) {
            sum = sum + bigger;
        }
        return sum;
    }
}
