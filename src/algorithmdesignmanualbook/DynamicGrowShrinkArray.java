package algorithmdesignmanualbook;

import utils.Assertions;

import java.util.Arrays;
import java.util.List;

public class DynamicGrowShrinkArray {

    private static class DynamicArray {
        private Integer[] _array = new Integer[0];
        private int _nextIndex = 0;

        public void add(int value) {
            if (_array.length <= _nextIndex) {
                Integer[] temp = new Integer[_array.length == 0 ? 1 : _array.length * 2];
                System.out.println(String.format("Increasing length from %s to %s", _array.length, temp.length));
                System.arraycopy(_array, 0, temp, 0, _nextIndex);
                _array = temp;
            }
            System.out.println("Adding " + value);
            _array[_nextIndex] = value;
            _nextIndex++;
        }

        public void removeLast() {
            System.out.println("Removing");
            _array[_nextIndex - 1] = null;
            _nextIndex--;
            if (_nextIndex <= _array.length / 2) {
                Integer[] temp = new Integer[_array.length == 1 ? 0 : _array.length / 2];
                System.out.println(String.format("Shrinking length from %s to %s", _array.length, temp.length));
                System.arraycopy(_array, 0, temp, 0, _nextIndex);
                _array = temp;
            }
        }

        public void print() {
            System.out.println(Arrays.toString(_array));
        }

        public List<Integer> asArray() {
            return Arrays.asList(_array);
        }
    }

    public static void main(String[] args) {
        DynamicArray array = new DynamicArray();
        array.add(0);
        array.add(1);
        array.add(2);
        array.add(3);

        Assertions.assertIterableSame(Arrays.asList(0, 1, 2, 3), array.asArray());

        array.print();
        array.add(4);
        array.add(5);
        array.add(6);
        array.add(7);

        Assertions.assertIterableSame(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7), array.asArray());

        array.print();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        Assertions.assertIterableSame(Arrays.asList(0, 1), array.asArray());
        array.removeLast();
        array.removeLast();
        Assertions.assertIterableSame(Arrays.asList(), array.asArray());
        array.print();
    }
}

