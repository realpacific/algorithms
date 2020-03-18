package bits;

import commons.BitUtils;

public class Insertion {
    public static void main(String[] args) {
        Byte m = 0b1011;
        // 0b100(1011)00
        Byte n = (byte) 0b100001100;
        int i = 2;
        int j = 6;

        Byte nullifierBytes = n;
        
        for (int k = 0; k < 10; k++) {
            if (k >= 2 && k < 6) {
                nullifierBytes = BitUtils.clearBit(nullifierBytes, k);
            } else {
                nullifierBytes = BitUtils.setBit(nullifierBytes, k);
            }
        }
        assert nullifierBytes == Byte.valueOf((byte)0b111000011);
        
        byte nullfiedBytes = (byte) (nullifierBytes & n);
        byte shifted = (byte) (m << i);
        System.out.println(Integer.toBinaryString(nullfiedBytes + shifted));
        assert (byte)(nullfiedBytes + shifted) == 0b10010111; 
        assert false == true;
    }
}
