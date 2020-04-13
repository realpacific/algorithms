package dynamic;

public class BooleanEvaluation {

    public static void main(String[] args) {
        String s = "1^0|0|1";
        System.out.println(countEval(s, false));
    }

    private static int countEval(String s, boolean result) {
        if (s.length() == 0) return 0;
        if (s.length() == 1) {
            return s.equals("1") == result ? 1 : 0;
        }
        int ways = 0;
        for (int i = 1; i < s.length(); i = i + 2) {
            char operator = s.charAt(i);
            String left = s.substring(0, i);
            String right = s.substring(i + 1);

            int leftFalse = countEval(left, false);
            int rightFalse = countEval(right, false);
            int leftTrue = countEval(left, true);
            int rightTrue = countEval(right, true);
            int total = (leftTrue + leftFalse) * (rightFalse + rightTrue);

            int totalTrue = 0;
            if (operator == '^') {
                totalTrue = leftTrue * rightFalse + leftFalse * rightTrue;
            } else if (operator == '&') {
                totalTrue = leftTrue * rightTrue;
            } else if (operator == '|') {
                totalTrue = leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
            }

            int subways = result ? totalTrue : total - totalTrue;
            ways += subways;
        }
        return ways;
    }
}
