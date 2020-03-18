package commons;

public class BitUtils {
    public static byte clearBit(byte value, int position) {
        byte b = (byte) (1 << position);
        return (byte) (value & b);
    }

    public static byte setBit(byte value, int position) {
        byte b = (byte) (1 << position);
        return (byte) (value | b);
    }

    public static void main(String[] args) {
        assert clearBit((byte) 0b111111, 2) == 0b111011;
        assert clearBit((byte) 0b111111, 3) == 0b110111;
        assert clearBit((byte) 0b111111, 4) == 0b101111;

        assert setBit((byte) 0b000011, 4) == 0b010011;
        assert setBit((byte) 0b0000, 2) == 0b0100;
    }
}