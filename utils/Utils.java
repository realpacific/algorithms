package utils;

public class Utils {


    public static <T> void printArr(Iterable<T[]> it) {
        for (T[] t : it) {
            for (Object t1 : t) {
                System.out.print(t1 + " ");
            }
            System.out.println();
        }
    }

    public static <T extends Object> void print(Iterable<T> it) {
        for (Object o : it) {
            System.out.println(o);
        }
    }
}
