@file:JvmName("Assertions")

package utils

import _utils.SkipDocumentation
import java.lang.Exception
import kotlin.test.assertEquals
import kotlin.test.assertTrue


@SkipDocumentation


fun <T> assertIterableSame(expected: Iterable<T>, actual: Iterable<T>) {
    require(expected.count() == actual.count()) {
        "Size mismatch (expected=${expected.toList()}, actual=${actual.toList()})."
    }
    expected.forEachIndexed { index, t ->
        if (t!!::class.java.isArray) {
            // handle array of arrays recursively
            assertIterableSame(
                tryInvokeToList(t) as List<*>,
                tryInvokeToList(actual.elementAt(index)) as List<*>
            )
        } else {
            assertEquals(
                t, actual.elementAt(index),
                "Failed \nexpected=${expected.toList()},\nactual  =${actual.toList()}"
            )
        }

    }
}

fun <T> assertArraysSame(expected: Array<T>, actual: Array<T>) {
    assertIterableSame(expected = expected.toList(), actual = actual.toList())
}

fun <T : Comparable<T>> assertIterableSameInAnyOrder(expected: Iterable<T>, actual: Iterable<T>) {
    require(expected.count() == actual.count()) {
        "Size mismatch (expected=${expected.toList()}, actual=${actual.toList()})."
    }
    for (i in 0 until expected.count()) {
        require(actual.contains(expected.elementAt(i)) && expected.contains(actual.elementAt(i))) {
            "Failed (expected=${expected.toList()}, actual=${actual.toList()})."
        }
    }
}

infix fun <T> T?.shouldBe(value: T?): T? {
    when (this) {
        is CharArray -> assertIterableSame(actual = this.toList(), expected = (value as CharArray).toList())
        is IntArray -> assertIterableSame(actual = this.toList(), expected = (value as IntArray).toList())
        is List<*> -> assertIterableSame(actual = this.toList(), expected = (value as List<*>).toList())
        is Array<*> -> assertIterableSame(actual = this.toList(), expected = (value as Array<*>).toList())
        else -> assertEquals(value, this)
    }
    // for chain-ability
    return this
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
    val messageBuilder: (String, String) -> String = { actual: String, expected: String ->
        "$actual does not match any values in $expected"
    }
    if (this!!::class.java.isArray) {
        val list = Class.forName("kotlin.collections.ArraysKt")
            .getMethod("toList", this!!::class.java)
            .also { it.isAccessible = true }
            .invoke(null, this)
        assertTrue(
            isGood,
            messageBuilder(list.toString(), values.map { tryInvokeToList(it) }.toList().toString())
        )
    } else {
        assertTrue(isGood, messageBuilder(this.toString(), values.toList().toString()))
    }
}

private fun tryInvokeToList(obj: Any?): Any? {
    if (obj == null) return obj
    if (obj::class.java.isArray) {
        return try {
            val list = Class.forName("kotlin.collections.ArraysKt")
                .getMethod("toList", obj!!::class.java)
                .also { it.isAccessible = true }
                .invoke(null, obj)
            list as List<*>
        } catch (e: Exception) {
            obj
        }
    }
    return obj
}

fun <T, U> assertAllWithArgs(expected: T, argsProducer: () -> U, vararg block: (U) -> T) {
    require(block.isNotEmpty())
    val results = block.map {
        val cloned = argsProducer.invoke()
        it.invoke(cloned)
    }
    results.forEach {
        it shouldBe expected
    }
}
