@file:JvmName("Assertions")

package utils


fun <T> assertIterableSame(expected: Iterable<T>, actual: Iterable<T>) {
    require(expected.count() == actual.count())
    expected.forEachIndexed { index, t ->
        require(t == actual.elementAt(index))
    }
}

fun <T> assertIterableSameInAnyOrder(expected: Iterable<T>, actual: Iterable<T>) {
    require(expected.count() == actual.count())
    expected.forEach { t ->
        require(actual.contains(t))
    }
}