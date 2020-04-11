package bits;

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
    }

    private static byte swapOddAndEven(byte value) {
        if (value <= 1) {
            return value;
        }
        byte oddMask = (byte) 0b10101010;
        byte evenMask = (byte) 0b01010101;
        byte result = (byte) ((((byte) (oddMask & value) >>> 1)) | ((byte) (value & evenMask) << 1));
        System.out.println(Integer.toBinaryString(result));
        return result;
    }

}
