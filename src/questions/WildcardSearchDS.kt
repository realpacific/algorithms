package questions

import _utils.UseCommentAsDocumentation
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 * Your data structure should implement two methods
 * * `addWord(word)`- Adds word to the data structure
 * * `searchWorld(word)`- Returns true if there is any string in the data structure that matches word. Word may contain dots where a dot can be matched with any letter (a dot represents a wildcard).
 */
@UseCommentAsDocumentation
class Trie {
    var value: Char? = null

    private val trieMap: MutableMap<Char, Trie> = mutableMapOf()

    fun addWord(word: String) {
        addWord(word, 0)
    }

    private fun addWord(word: String, index: Int) {
        val letter = word.getOrNull(index) ?: return
        if (letter !in trieMap) {
            val newTrie = Trie()
            trieMap[letter] = newTrie
            newTrie.value = letter
            newTrie.addWord(word, index + 1)
        } else {
            val trie = trieMap[letter]!!
            trie.addWord(word, index + 1)
        }
    }

    override fun toString(): String {
        return "Trie($value, ${trieMap.values})"
    }

    private fun depthFirstTraversal(trie: Trie, current: String, word: MutableList<String>) {
        if (trie.trieMap.isEmpty()) {
            word.add(current)
        }
        trie.trieMap.forEach { (k, v) ->
            depthFirstTraversal(v, current + k, word)
        }
    }

    fun searchWord(word: String): Boolean {
        return searchWord(word, 0)
    }

    private fun searchWord(word: String, index: Int): Boolean {
        val letter = word.getOrNull(index) ?: return true
        if (letter == '.') { // wildcard
            // Ignore this and try to find any other node that matches next character
            return trieMap.values.map {
                it.searchWord(word, index + 1)
            }.any { it }
        } else {
            val nextTrie = trieMap[letter] ?: return false
            return nextTrie.searchWord(word, index + 1)
        }
    }


    fun branches(): List<String> {
        val words = mutableListOf<String>()
        depthFirstTraversal(this, "", words)
        println(words)
        return words
    }
}

fun main() {
    run {
        val trie = Trie()
        trie.addWord("hello")
        trie.addWord("word")
        trie.addWord("woke")
        trie.addWord("world")

        assertTrue { trie.searchWord("hello") }
        assertTrue { trie.searchWord("word") }
        assertFalse { trie.searchWord("worry") }
        assertTrue { trie.searchWord("wo.d") }
        assertTrue { trie.searchWord("wor.d") }

        trie.addWord("wore")
        trie.addWord("wop")
        trie.addWord("won")
        trie.addWord("worst")

        assertTrue { trie.searchWord("wor.t") }
        assertFalse { trie.searchWord("wor.ts") }
        assertFalse { trie.searchWord("worm") }
        trie.addWord("worm")
        assertTrue { trie.searchWord("worm") }
        assertTrue { trie.searchWord("w.rm") }
        assertTrue { trie.searchWord("w..m") }
        assertTrue { trie.searchWord("...m") }
        assertFalse { trie.searchWord("...ms") }
        assertTrue { trie.searchWord("w...") }
    }

    run {
        val trie = Trie()
        trie.addWord("egg")
        trie.addWord("eggplant")
        trie.addWord("eggshell")
        trie.addWord("elephant")
        trie.addWord("eleanor")
        trie.addWord("eleven")
        trie.addWord("elegant")
        trie.addWord("evil")


        assertTrue { trie.searchWord("egg") }
        assertTrue { trie.searchWord("eg.") }
        assertTrue { trie.searchWord("eg.p.a.t") }
        assertTrue { trie.searchWord("elep.a.t") }
        assertFalse { trie.searchWord("elope") }
        assertFalse { trie.searchWord("el.phat") }
        assertFalse { trie.searchWord("elev.ns") }
        assertFalse { trie.searchWord("elevens") }
        assertFalse { trie.searchWord("eleventh") }
        assertFalse { trie.searchWord("eleven.") }

        trie.addWord("watch")
        trie.addWord("witch")
        trie.addWord("with")
        trie.addWord("without")
        trie.addWord("withe")
        trie.addWord("wither")
        trie.addWord("wit")
        trie.addWord("withering")
        trie.branches()
    }
}