@file:JvmName("Assertions")

package utils

import _utils.SkipDocumentation
import kotlin.test.assertEquals

@SkipDocumentation


fun <T> assertIterableSame(expected: Iterable<T>, actual: Iterable<T>) {
    require(expected.count() == actual.count()) {
        "Size mismatch (expected=${expected.toList()}, actual=${actual.toList()})."
    }
    expected.forEachIndexed { index, t ->
        require(t == actual.elementAt(index)) {
            "Failed (expected=${expected.toList()}, actual=${actual.toList()})."
        }
    }
}

fun <T> assertArraysSame(expected: Array<T>, actual: Array<T>) {
    assertIterableSame(expected = expected.toList(), actual = actual.toList())
}

fun <T> assertIterableSameInAnyOrder(expected: Iterable<T>, actual: Iterable<T>) {
    require(expected.count() == actual.count())
    expected.forEach { t ->
        require(actual.contains(t)) {
            "Failed (expected=${expected.toList()}, actual=${actual.toList()})."
        }
    }
}

infix fun <T> T?.shouldBe(value: T?) {
    when (this) {
        is IntArray -> assertIterableSame(actual = this.toList(), expected = (value as IntArray).toList())
        is List<*> -> assertIterableSame(actual = this.toList(), expected = (value as List<*>).toList())
        is Array<*> -> assertIterableSame(actual = this.toList(), expected = (value as Array<*>).toList())
        else -> assertEquals(value, this)
    }
}