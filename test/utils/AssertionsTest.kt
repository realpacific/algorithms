package utils

import _utils.SkipDocumentation
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.random.Random

@SkipDocumentation

class AssertionsTest {

    @Test
    fun `test for shouldBe using singular item`() {
        val testList = listOf<Any>("", 1, BigDecimal.ONE, 10f, 10L, BigInteger.TEN)
        testList
            .forEach {
                assertDoesNotThrow { it shouldBe it }
            }

        testList
            .forEachIndexed { index, any ->
                assertThrows<AssertionError> {
                    testList.getOrNull(index - 1) shouldBe any
                }
            }
    }

    @Test
    fun `test for shouldBe using collection item`() {
        listOf(
            intArrayOf(1, 2, 3),
            charArrayOf('a', 'b'), intArrayOf(),
            arrayOf(1, 2, 3), arrayOf("hello"), listOf("1", "hello"), listOf(1, 2, 3),
            emptyArray<String>(), emptyList<Char>()
        ).forEach {
            assertDoesNotThrow { it shouldBe it }
        }
        assertThrows<AssertionError> {
            intArrayOf(1, 2, 3) shouldBe intArrayOf(1, 3, 2)
        }

        assertThrows<AssertionError> {
            intArrayOf(1) shouldBe intArrayOf(1, 3, 2)
        }

        assertThrows<AssertionError> {
            arrayOf(1, 1, 1) shouldBe arrayOf(1, 1)
        }
    }

    @Test
    fun `test for assertIterableSame using list item`() {
        val testList = listOf<Iterable<String>>(
            listOf("1", "2", "3"),
            listOf("1", "1", "1"),
            listOf(),
            listOf("1")
        )
        testList.forEach {
            assertDoesNotThrow { assertIterableSame(it, it.asIterable().toList()) }
        }
        testList.forEach {
            assertDoesNotThrow {
                assertIterableSameInAnyOrder(
                    it.sorted(),
                    it.asIterable().reversed().toList()
                )
            }
        }

        assertThrows<AssertionError> {
            assertIterableSameInAnyOrder(listOf(1, 2, 3), listOf(1))
        }

        assertThrows<AssertionError> {
            assertIterableSameInAnyOrder(listOf(1, 2), listOf(1, 3))
        }
    }

    @Test
    fun `test for shouldBeOneOf`() {
        assertDoesNotThrow {
            1 shouldBeOneOf listOf(1, 2, 3, 4, 5)
        }

        val testList = listOf<Iterable<Any>>(
            listOf(1, 2, 3),
            listOf(listOf(1, 2, 3), listOf(2, 3, 4), listOf(4, 5, 6)),
            listOf(setOf(1, 2, 3), setOf(2, 3, 4), setOf(4, 5, 6)),
            listOf("a", "b", "c"),
        )

        val random = Random(1)
        testList.forEach {
            // Get random element from iterable
            val index = random.nextInt(it.count())
            assertDoesNotThrow { it.elementAt(index) shouldBeOneOf it }
        }

        assertThrows<AssertionError> {
            1 shouldBeOneOf listOf(2, 3, 4, 5)
        }
    }
}