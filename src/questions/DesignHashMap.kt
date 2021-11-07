package questions

import _utils.UseCommentAsDocumentation
import questions.common.ClassInvoker
import utils.shouldBe

/**
 * Design a HashMap without using any built-in hash table libraries:
 * * `MyHashMap()` initializes the object with an empty map.
 * * `void put(int key, int value)` inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
 * * `int get(int key)` returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * * `void remove(key)` removes the key and its corresponding value if the map contains the mapping for the key.
 *
 * [Source](https://leetcode.com/problems/design-hashmap/)
 *
 * @see MyHashSet
 */
@UseCommentAsDocumentation
class MyHashMap {
    private var array = Array<Node?>(10) { null }

    fun put(key: Int, value: Int) {
        val index = getHashCode(key)
        val (parent, self) = findParentAndSelf(index, key)
        if (self != null) { // key already exists
            self.value = value // then just update it
        } else {
            if (parent != null) { // append it to current bucket
                parent.next = Node(key, value)
            } else {
                array[index] = Node(key, value) // no element in the current bucket so add it as first node
            }
        }
    }

    private fun findParentAndSelf(index: Int, key: Int): Pair<Node?, Node?> {
        var current: Node? = array[index] ?: return null to null
        if (current?.key == key) { // first item has the key
            return null to current // so no parents
        }
        while (current != null) {
            if (current.next?.key == key) { // next item has the key
                return current to current.next
            }
            if (current.next == null) { // next item is null so last most item is the parent
                return current to null
            }
            current = current.next
        }
        return current to null
    }

    private fun getHashCode(key: Int): Int {
        return Integer.hashCode(key) % array.size // what is key is -ve, use abs?
    }

    fun get(key: Int): Int {
        val index = getHashCode(key)
        val (_, self) = findParentAndSelf(index, key)
        return self?.value ?: -1
    }

    fun remove(key: Int) {
        val index = getHashCode(key)
        val (parent, self) = findParentAndSelf(index, key)
        if (self == null) {
            return
        }
        if (parent == null) { // this was the first node
            array[index] = self.next
        } else { // its parent exists so join it with the child
            parent.next = self.next
        }
    }

    inner class Node(val key: Int, var value: Int) {
        var next: Node? = null
    }

}


fun main() {
    run {
        MyHashMap().apply {
            put(1, 1) // The map is now [[1,1]]
            put(2, 2) // The map is now [[1,1], [2,2]]
            get(1) shouldBe 1// return 1, The map is now [[1,1], [2,2]]
            get(3) shouldBe -1 // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
            put(2, 1) // The map is now [[1,1], [2,1]] (i.e., update the existing value)
            get(2) shouldBe 1// return 1, The map is now [[1,1], [2,1]]
            remove(2) // remove the mapping for 2, The map is now [[1,1]]
            get(2) shouldBe -1 // return -1 (i.e., not found), The map is now [[1,1]]
            put(10, 5)
            get(10) shouldBe 5
            put(10, 6)
            get(10) shouldBe 6
            remove(10)
            get(10) shouldBe -1
        }
    }
    run {
        MyHashMap().apply {
            for (j in 1..50) {
                val i = j * 9
                remove(i)
                get(i) shouldBe -1
                put(i, i * 2)
                get(i) shouldBe i * 2
                put(i, i * 3)
                get(i) shouldBe i * 3
                remove(i)
                get(i) shouldBe -1
                put(i, i)
                get(i) shouldBe i
            }
        }
    }
    run {
        val invoker = ClassInvoker<IntArray, IntArray>(
            listOf("MyHashMap", "remove", "get", "put", "put", "put", "get", "put", "put", "put", "put"),
        ) {
            it
        }
        invoker.invoke(
            listOf(
                intArrayOf(),
                intArrayOf(14),
                intArrayOf(4),
                intArrayOf(7, 3),
                intArrayOf(11, 1),
                intArrayOf(12, 1),
                intArrayOf(7),
                intArrayOf(1, 19),
                intArrayOf(0, 3),
                intArrayOf(1, 8),
                intArrayOf(2, 6)
            )
        )
    }
}