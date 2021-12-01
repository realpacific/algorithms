package questions.common

import _utils.SkipDocumentation
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

@SkipDocumentation

class TestClass {
    fun returnOne() = 1
    fun returnTwo() = 2
    fun returnThree() = 3
    fun returnArgs(arg: Int) = arg
}

class ClassInvokerTest {

    @Test
    fun testForClassInvoker() {
        val invoker = ClassInvoker<IntArray, Int?>(
            listOf("TestClass", "returnOne", "returnTwo", "returnOne", "returnArgs"),
            "questions.common"
        ) {
            it.getOrNull(0)
        }
        assertDoesNotThrow {
            invoker.invoke(
                listOf(intArrayOf(), intArrayOf(), intArrayOf(), intArrayOf(), intArrayOf(100)),
                listOf(null, 1, 2, 1, 100)
            )
        }
    }

    @Test
    fun whenClassInvoker_IsInvocationValid() {
        val invoker = ClassInvoker<IntArray, Int?>(
            listOf("TestClass", "returnOne", "returnTwo", "returnOne", "returnArgs"),
            "questions.common"
        ) {
            it.getOrNull(0)
        }
        assertThrows<AssertionError> {
            invoker.invoke(
                listOf(intArrayOf(), intArrayOf(), intArrayOf(), intArrayOf(), intArrayOf(100)),
                listOf(
                    null,
                    1,
                    2,
                    1,
                    2 //should be 100
                )
            )
        }
    }
}