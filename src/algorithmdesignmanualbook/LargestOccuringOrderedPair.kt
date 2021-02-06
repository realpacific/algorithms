package algorithmdesignmanualbook

import kotlin.test.assertTrue


private data class GenericNode<T>(val value: T?) {
    val nodes = mutableListOf<GenericNode<T>>()
    var count = 1

    fun add(node: GenericNode<T>) {
        nodes.add(node)
    }

    fun find(str: String): GenericNode<T>? {
        return nodes.find { it.value == str }
    }

    fun print() {
        val nodeStr = nodes.map { "${it.value} ${it.count}" }
        println("$value $count $nodeStr")
    }
}

private class PairTrie(val rootNode: GenericNode<String> = GenericNode(null)) {
    var largestCount: Pair<String, Int>? = null
        private set

    fun add(on: String? = null, str: String) {
        // add it on root node
        if (on == null) {
            val existingNode = rootNode.find(str)
            if (existingNode == null) {
                rootNode.add(GenericNode(str))
            } else {
                existingNode.count++
            }
        } else {
            val previousNode = rootNode.find(on)!!
            val existingNode = previousNode.find(str)
            if (existingNode == null) {
                previousNode.add(GenericNode(str))
            } else {
                existingNode.count++
                if (largestCount == null || largestCount!!.second < existingNode.count) {
                    largestCount = Pair("$on $str", existingNode.count)
                }
            }
        }
    }

    fun print() {
        println(rootNode.value)
        rootNode.nodes.map(GenericNode<String>::print)
    }
}

private fun findLargestOccurringOrderedPair(str: String): String {
    val trie = PairTrie()
    val split = str.split(" ")
    for (i in 0..split.lastIndex) {
        trie.add(on = null, str = split[i])
    }
    for (i in 1..split.lastIndex) {
        trie.add(on = split[i - 1], str = split[i])
    }

    trie.print()
    return trie.largestCount!!.first.print()
}

fun main() {
    assertTrue { findLargestOccurringOrderedPair("A new puppy in New York is happy with its New York life") == "New York" }
    assertTrue { findLargestOccurringOrderedPair("I am running from change change searching for change change") == "change change" }
}