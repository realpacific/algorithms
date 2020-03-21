package bits;

/**
 * Given a real number between 0 & 1 (eg. 0.72) that is passed in as double, print the binary representation.
 * If # cannot be expressed in binary with at most 32 bits, then throw ERROR.
 */
class BinaryToString {
    public static void main(String[] args) {
        System.out.println(binary(0.04));
    }


    static String binary(double num) {
        if (num > 1 && num < 0) {
            throw new RuntimeException("ERROR");
        }
        StringBuilder b = new StringBuilder(".");
        while (num > 0) {
//            if (b.length() >= 32) {
//                throw new RuntimeException("ERROR");
//            }
            double r = num * 2;
            if (r >= 1) {
                b.append(1);
                num = r - 1;
            } else {
                b.append(0);
                num = r;
            }
        }
        return b.toString();
    }
}
