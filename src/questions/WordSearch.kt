package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import kotlin.collections.LinkedHashSet

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.

 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are
 * horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * [Source](https://leetcode.com/problems/word-search)
 */
@UseCommentAsDocumentation
private class WordSearch {
    private class Tree(val location: Location) {
        private val _children = mutableSetOf<Tree>()

        val children: Set<Tree>
            get() = _children.toSet()


        val childrenLength: Int
            get() = _children.size

        fun add(tree: Tree) {
            _children.add(tree)
        }

        fun add(location: Location) {
            add(Tree(location))
        }

    }

    private val visited: LinkedHashSet<Location> = LinkedHashSet()

    fun exist(board: Array<CharArray>, word: String): Boolean {
        if (word.length > board[0].size * board.size) return false
        val occurrencesOfFirstChar = findOccurrencesInBoard(word[0], board) ?: return false

        // when word length is 1, only check occurrences
        if (word.length == 1 && occurrencesOfFirstChar.isNotEmpty()) {
            return true
        }
        // else do DFS with every first character occurrence as root
        for (occurrence in occurrencesOfFirstChar) {
            visited.clear()             // restart
            val node = Tree(occurrence) // root
            if (checkIfExists(board, word.substring(1), node)) {
                return true
            }
        }
        return false
    }

    private fun checkIfExists(board: Array<CharArray>, remaining: String, node: Tree): Boolean {
        visited.add(node.location)
        val preLength = node.childrenLength
        addMatchingCharactersInNeighbors(node, board, remaining[0], node.location) // add children to the Tree [node]
        val postLength = node.childrenLength

        val matchExists = preLength != postLength
        if (matchExists && remaining.length == 1) { // only one character remains and there is already a match
            return true
        }
        for (child in node.children) {              // DFS on children
            if (checkIfExists(board, remaining.substring(1), child)) {
                return true
            } else {
                visited.remove(child.location)      // make it available for revisit later
            }
        }
        return false
    }

    /**
     * Adds the matches of [nextChar] to [node] in the vertical and horizontal neighbors if they haven't been visited yet
     */
    private fun addMatchingCharactersInNeighbors(
        node: Tree,
        board: Array<CharArray>,
        nextChar: Char?,
        location: Pair<Int, Int>,
    ) {

        if (nextChar == null) return
        val (row, col) = location
        board.getOrNull(row + 1)?.getOrNull(col)?.also {
            if (nextChar == it && !visited.contains(row + 1 to col)) node.add(row + 1 to col)

        }
        board.getOrNull(row - 1)?.getOrNull(col)?.also {
            if (nextChar == it && !visited.contains(row - 1 to col)) node.add(row - 1 to col)
        }
        board.getOrNull(row)?.getOrNull(col + 1)?.also {
            if (nextChar == it && !visited.contains(row to col + 1)) node.add(row to col + 1)
        }
        board.getOrNull(row)?.getOrNull(col - 1)?.also {
            if (nextChar == it && !visited.contains(row to col - 1)) node.add(row to col - 1)
        }
    }

    private fun findOccurrencesInBoard(char: Char, board: Array<CharArray>): MutableList<Location>? {
        val occurrences = mutableListOf<Location>()
        for (row in 0..board.lastIndex) {
            for (col in 0..board[0].lastIndex) {
                val character = board[row][col]
                if (char == character) {
                    occurrences.add(row to col)
                }
            }
        }
        return if (occurrences.isNotEmpty()) occurrences else null
    }

}

private typealias Location = Pair<Int, Int>

fun main() {

    WordSearch().exist(
        board = arrayOf(
            charArrayOf('A', 'B', 'C', 'E'),
            charArrayOf('S', 'F', 'E', 'S'),
            charArrayOf('A', 'D', 'E', 'E')
        ), word = "ABCESEEEFSAD"
    ) shouldBe true

    WordSearch().exist(
        board = arrayOf(
            charArrayOf('A', 'B', 'C', 'E'),
            charArrayOf('S', 'F', 'E', 'S'),
            charArrayOf('A', 'D', 'E', 'E')
        ), word = "ABCESEEEFS"
    ) shouldBe true

    WordSearch().exist(
        board = arrayOf(
            charArrayOf('A', 'B', 'C', 'E'),
            charArrayOf('S', 'F', 'E', 'S'),
            charArrayOf('A', 'D', 'E', 'E')
        ), word = "ABCEFSADEESE"
    ) shouldBe true

    WordSearch().exist(
        board = arrayOf(
            charArrayOf('b'),
            charArrayOf('a'),
            charArrayOf('b'),
            charArrayOf('b'),
            charArrayOf('a')
        ), word = "baa"
    ) shouldBe false

    WordSearch().exist(
        board = arrayOf(
            charArrayOf('a', 'a', 'a', 'a'),
            charArrayOf('a', 'a', 'a', 'a'),
            charArrayOf('a', 'a', 'a', 'a')
        ), word = "aaaaaaaaaaaaa"
    ) shouldBe false


    WordSearch().exist(
        board = arrayOf(
            charArrayOf('a', 'b'),
            charArrayOf('c', 'd')
        ), word = "abcd"
    ) shouldBe false

    WordSearch().exist(
        board = arrayOf(
            charArrayOf('a')
        ), word = "a"
    ) shouldBe true

    WordSearch().exist(
        board = arrayOf(
            charArrayOf('A', 'B', 'C', 'E'),
            charArrayOf('S', 'F', 'C', 'S'),
            charArrayOf('A', 'D', 'E', 'E')
        ), word = "SEE"
    ) shouldBe true

    WordSearch().exist(
        board = arrayOf(
            charArrayOf('A', 'B', 'C', 'E'),
            charArrayOf('S', 'F', 'C', 'S'),
            charArrayOf('A', 'D', 'E', 'E')
        ), word = "ABCCED"
    ) shouldBe true

    WordSearch().exist(
        board = arrayOf(
            charArrayOf('A', 'B', 'C', 'E'),
            charArrayOf('S', 'F', 'C', 'S'),
            charArrayOf('A', 'D', 'E', 'E')
        ), word = "ABCB"
    ) shouldBe false

}