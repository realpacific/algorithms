package linkedlists

import kotlin.test.assertFalse
import kotlin.test.assertTrue


/**
 * @suppress
 */
fun main() {
    val list = LinkedList()
    list.addLast(Node(0))
    list.addLast(Node(1))
    list.addLast(Node(2))
    list.addLast(Node(3))
    list.addLast(Node(4))
    assertFalse(list.hasDuplicates())
    list.addLast(Node(1))
    list.printAll()
    assertTrue(list.hasDuplicates())
    list.subListFrom(2).also {
        println(it)
        assertTrue(it.isNotEmpty())
    }
    list.subListFrom(100).also {
        println(it)
        assertTrue(it.isEmpty())
    }

    list.deleteAt(2).also {
        assertTrue(it != null && it.value == 2)
    }
    list.printAll()

    list.deleteAt(100).also {
        assertTrue(it == null)
    }

    list.nodeAt(0).also {
        assertTrue(it != null && it.value == 0)
    }

    println("____REVERSE__")
    list.printAll()
    list.reverse().also {
        print("Reversed: ")
        it.printAll()
    }
}
