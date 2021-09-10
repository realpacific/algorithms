@file:JvmName("Assertions")

package utils

import _utils.SkipDocumentation

@SkipDocumentation


fun <T> assertIterableSame(expected: Iterable<T>, actual: Iterable<T>) {
    require(expected.count() == actual.count())
    expected.forEachIndexed { index, t ->
        require(t == actual.elementAt(index))
    }
}

fun <T> assertArraysSame(expected: Array<T>, actual: Array<T>) {
    assertIterableSame(expected = expected.toList(), actual = actual.toList())
}

fun <T> assertIterableSameInAnyOrder(expected: Iterable<T>, actual: Iterable<T>) {
    require(expected.count() == actual.count())
    expected.forEach { t ->
        require(actual.contains(t))
    }
}