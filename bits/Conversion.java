package bits;

/**
 * Number of bits you have to flip to convert bits A to bits B
 */
public class Conversion {
    public static void main(String[] args) {
        assert numberOfBitsToFlip((byte) 0b11101, (byte) 0b01111) == 2;
        assert numberOfBitsToFlip((byte) 0b001100, (byte) 0b000000) == 2;
        assert numberOfBitsToFlip((byte) 0b1, (byte) 0b1) == 0;
        assert numberOfBitsToFlip((byte) 0b11111, (byte) 0b00000) == 5;
        assert numberOfBitsToFlip((byte) 0b0, (byte) 0b0) == 0;

    }

    private static int numberOfBitsToFlip(byte a, byte b) {
        // xor = both same then 0 else 1
        byte xor = (byte) (a ^ b);
        // counting the number of 1s will give the number of flipping needed
        int countOfOnes = 0;

        System.out.println(Integer.toBinaryString(xor));
        // Check for integer value of xor. Do this until xor = 0000000;
        while (xor != 0) {
            // a & 00001 to get the least significant bit
            int lsb = xor & 1;
            if (lsb == 1) {
                countOfOnes++;
            }

            // Shift the byte by 1  to the right
            xor = (byte) (xor >> 1);
        }
        System.out.println(countOfOnes);
        return countOfOnes;
    }
}
