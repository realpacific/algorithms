package utils;

public class PrintUtils {
    public static <T> void printArr(Iterable<T[]> it) {
        for (T[] t : it) {
            for (Object t1 : t) {
                System.out.print(t1 + " ");
            }
            System.out.println();
        }
    }

    public static void printIntArrWithCast(Iterable<Integer[]> it) {
        for (Integer[] t : it) {
            for (Integer t1 : t) {
                System.out.print(castToInfinity(t1) + " ");
            }
            System.out.println();
        }
    }

    public static <T> void println(Iterable<T> it) {
        for (Object o : it) {
            System.out.println(o);
        }
    }

    public static <T> void print(Iterable<T> it) {
        for (Object o : it) {
            System.out.print(o + " ");
        }
        System.out.println("");
    }

    static String castToInfinity(Integer t) {
        if (t == Integer.MAX_VALUE) return "∞";
        else return t.toString();
    }

}
