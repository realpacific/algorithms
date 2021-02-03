package bits;


import bits.commons.BitUtils;

public class Insertion {
    public static void main(String[] args) {
        byte m = 0b1011;
        // 0b100(1011)00
        byte n = (byte) 0b101001100;
        int i = 2;
        int j = 6;

        // Clear all the bits in range i~j
        for (int k = 0; k < 10; k++) {
            if (k >= i && k < j) {
                n = BitUtils.clearBit(n, k);
            }
        }
        assert n == (byte) 0b101000000;

        // Shift the bits to be inserted by i so that we get 1011(00)
        byte shifted = (byte) (m << i);

        // n OR shifted will give result
        assert (n | shifted) == (byte) 0b101101100;
    }
}
