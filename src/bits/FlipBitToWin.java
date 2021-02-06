package bits;

import java.util.Stack;

/**
 * Find the largest sequence of 1 that can be obtained by flipping a 0 to 1.
 */
public class FlipBitToWin {
    public static void main(String[] args) {
        assert flipToWin("1101111100111") == 8;

        assert flipToWin("11011101111") == 8;
        assert flipToWin("110011100111101") == 6;
        assert flipToWin("11111111111101") == 14;
        assert flipToWin("010000000") == 1;
        assert flipToWin("0000000") == 1;
        assert flipToWin("00000000010000001000001101") == 4;
        assert flipToWin("000001110000011") == 3;
        assert flipToWin("010101110100011") == 5;
        assert flipToWin("000001110001011") == 4;
        assert flipToWin("000000000") == 1;
        assert flipToWin("1111") == 4;
        assert flipToWin("1111001111111") == 7;
    }

    private static int flipToWin(String bit) {
        Stack<Integer> stack = new Stack<>();
        int countOfOnes = 0;
        int countOfZeros = 0;

        // Count the number of 1s and push the count in stack
        for (int i = 0; i < bit.length(); i++) {
            if (bit.charAt(i) == '1') {
                countOfZeros = 0;
                countOfOnes++;
            } else {
                countOfZeros++;
                // if 2 or more 0s occur sequentially, push -1 if latest element is not -1
                // This will make sure 2 or more continuous 0s is denoted by -1 in the stack.
                if (countOfZeros > 1) {
                    if (stack.isEmpty() || stack.peek() != -1) {
                        stack.push(-1);
                    }
                } else {
                    if (countOfOnes > 0) stack.push(countOfOnes);
                }
                countOfOnes = 0;
            }
        }

        // Push one last time as for loop exits before putting it on the stack
        if (countOfOnes > 0) {
            stack.push(countOfOnes);
        }

        // If stack comprises of [-1], [1]
        if (stack.size() == 1) {
            // 0000 ---> 1
            // 1111 ---> 4
            return Math.max(1, stack.peek());
        }
        System.out.println(stack);

        return findLongestPossibleSequence(stack);
    }

    private static int findLongestPossibleSequence(Stack<Integer> stack) {
        int longestPossibleSequence = 0;
        // Using rolling window
        for (int i = 1; i < stack.size(); i++) {
            int prev = stack.get(i - 1);
            int now = stack.get(i);
            int sum = 0;
            // if one of them is -1, then sum is the max of two
            // [(5, -1), 1, 4]
            // -1 denotes continuous occurrence of 0s
            if (prev == -1 || now == -1) {
                sum = Math.max(prev, now);
            } else {
                // if none of them is -1, then sum them and add 1 as only one 0s exists between them
                // which can be flipped
                sum = prev + now + 1;
            }
            // override [longestPossibleSequence] if it is less than sum
            longestPossibleSequence = Math.max(longestPossibleSequence, sum);
        }
        System.out.println(longestPossibleSequence);
        return longestPossibleSequence;
    }
}
