package bits;

import bits.commons.BitUtils;

import java.text.MessageFormat;
import java.util.BitSet;

import static java.text.MessageFormat.format;

/**
 * Write a program to swap odd and even bits in an integer with as few instructions as
 * possible (e.g., bit O and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on)
 */
public class PairwiseSwap {

    public static void main(String[] args) {
        assert swapOddAndEven((byte) 0b101011) == 0b010111;
        assert swapOddAndEven((byte) 0b10) == 0b01;
        assert swapOddAndEven((byte) 0b11) == 0b11;
        assert swapOddAndEven((byte) 0b1) == 0b1;
        assert swapOddAndEven((byte) 0b101) == 0b110;
        assert swapOddAndEven((byte) 0b10101) == 0b11010;
        assert swapOddAndEven((byte) 0b111) == 0b111;
        assert swapOddAndEven((byte) 0b110) == 0b101;
    }

    private static byte swapOddAndEven(byte value) {
        int numberOfBits = countBits(value);
        if (value <= 1) {
            return value;
        }
        byte oddMask = (byte) 0b10101010;
        byte evenMask = (byte) 0b01010101;
        // Get all odd bits and even bits and OR it
        byte result = (byte) ((((byte) (oddMask & value) >> 1)) | ((byte) (value & evenMask) << 1));

        // what if the number of bits is odd like in 110
        // result of 101 will be 1010 but should be 110
        // so keep the MSB of [value]
        if (numberOfBits % 2 != 0) {
            // Maintain the MSB of value (1)10
            result = BitUtils.setBit(result, numberOfBits - 1);
            // Clear bit ahead of it
            result = BitUtils.clearBit(result, numberOfBits);
        }
        System.out.println(
                format("Number of bits in {0}: {1} {2}",
                        Integer.toBinaryString(value),
                        Integer.toBinaryString(result),
                        numberOfBits)
        );

        return result;
    }

    /**
     * [Link](https://www.geeksforgeeks.org/count-total-bits-number/)
     */
    static int countBits(int number) {
        // log function in base 2 &  take only integer part
        return (int) (Math.log(number) / Math.log(2) + 1);
    }

}
