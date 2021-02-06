package threads;

import java.util.function.Function;
import java.util.function.Predicate;

public class FizzBuzz {
    public static void main(String[] args) {
        Thread[] threads = {
                new FizzBuzzThread(i -> i % 3 == 0 && i % 5 == 0,
                        i -> "FizzBuzz", 100, "FizzBuzz"),
                new FizzBuzzThread(i -> i % 3 == 0 && i % 5 != 0,
                        i -> "Fizz", 100, "Fizz"),
                new FizzBuzzThread(i -> i % 5 == 0 && i % 3 != 0,
                        i -> "Buzz", 100, "buzz"),
                new FizzBuzzThread(i -> i % 5 != 0 && i % 3 != 0,
                        i -> Integer.toString(i), 100, "number"),
        };

        for (Thread thread : threads) {
            thread.start();
        }
    }


    static class FizzBuzzThread extends Thread {
        private static final Object LOCK = new Object();
        Predicate<Integer> predicate;
        private static int current = 1;
        Function<Integer, String> printer;
        int max;
        final String name;

        public FizzBuzzThread(Predicate<Integer> predicate,
                              Function<Integer, String> printer, int max, String name) {
            this.predicate = predicate;
            this.printer = printer;
            this.max = max;
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    if (current > max) return;
                    if (predicate.test(current)) {
                        System.out.println(printer.apply(current) +"  " + name);
                        current++;
                    }
                }
            }
        }
    }
}
