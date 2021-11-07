package questions

import _utils.UseCommentAsDocumentation

/**
 * Implement MyHashSet class:
 * * `void add(key)` Inserts the value key into the HashSet.
 * * `bool contains(key)` Returns whether the value key exists in the HashSet or not.
 * * `void remove(key)` Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.

 * [Source](https://leetcode.com/problems/design-hashset/)
 *
 * @see MyHashMap
 */
@UseCommentAsDocumentation
class MyHashSet {
    private val array: Array<Node?> = Array(100) { null }

    private fun getIndex(key: Int) = key % array.size

    fun add(key: Int) {
        val index = getIndex(key)
        val (parent, self) = findParentAndSelf(index, key)
        if (self == null) {
            if (parent == null) {
                array[index] = Node(key)
            } else {
                parent.next = Node(key)
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

    fun remove(key: Int) {
        val index = getIndex(key)
        val (parent, self) = findParentAndSelf(index, key)
        if (self != null) {
            if (parent == null) {
                array[index] = self.next
            } else {
                parent.next = self.next
            }
        }
    }

    fun contains(key: Int): Boolean {
        val index = getIndex(key)
        val (_, self) = findParentAndSelf(index, key)
        return self != null
    }

    inner class Node(val key: Int) {
        var next: Node? = null
    }
}

fun main() {
    run {
        val myHashSet = MyHashSet()
        myHashSet.add(1) // set = [1]
        myHashSet.add(2) // set = [1, 2]
        myHashSet.contains(1) // return True
        myHashSet.contains(3) // return False, (not found)
        myHashSet.add(2) // set = [1, 2]
        myHashSet.contains(2) // return True
        myHashSet.remove(2) // set = [1]
        myHashSet.contains(2) // return False, (already removed)
    }
}