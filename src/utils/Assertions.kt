@file:JvmName("Assertions")

package utils

import _utils.SkipDocumentation
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SkipDocumentation


fun <T> assertIterableSame(expected: Iterable<T>, actual: Iterable<T>) {
    require(expected.count() == actual.count()) {
        "Size mismatch (expected=${expected.toList()}, actual=${actual.toList()})."
    }
    expected.forEachIndexed { index, t ->
        assertEquals(
            t, actual.elementAt(index),
            "Failed \nexpected=${expected.toList()},\nactual  =${actual.toList()}"
        )
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
        is CharArray -> assertIterableSame(actual = this.toList(), expected = (value as CharArray).toList())
        is IntArray -> assertIterableSame(actual = this.toList(), expected = (value as IntArray).toList())
        is List<*> -> assertIterableSame(actual = this.toList(), expected = (value as List<*>).toList())
        is Array<*> -> assertIterableSame(actual = this.toList(), expected = (value as Array<*>).toList())
        else -> assertEquals(value, this)
    }
}


infix fun <T> T?.shouldBeOneOf(values: Iterable<T?>) {
    require(values.count() > 0)
    var isGood = false
    for (i in values) {
        try {
            this shouldBe i
            isGood = true
            break
        } catch (e: AssertionError) {
            isGood = false
        }
    }
    assertTrue(isGood, "$this does not match any values in $values")
}