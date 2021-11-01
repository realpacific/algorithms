package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an `m x n` matrix board containing 'X' and 'O',
 * capture all regions that are 4-directionally surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * [Source](https://leetcode.com/problems/surrounded-regions/)
 */
@UseCommentAsDocumentation
private fun solve(board: Array<CharArray>) {
    val borderOs = mutableListOf<Pair<Int, Int>>()
    val nonBorderOs = mutableSetOf<Pair<Int, Int>>()
    val m = board.lastIndex
    val n = board[0].lastIndex

    for (row in 0..m) {
        for (col in 0..n) {
            if (board[row][col] == 'O') {
                if (row == 0 || row == m || col == 0 || col == n) {
                    // Collect all the Os in the borders
                    borderOs.add(row to col)
                } else {
                    // Collect all the inside Os
                    nonBorderOs.add(row to col)
                }
            }
        }
    }
    traverseFromBorderOsAndRemoveConnected(board, borderOs, nonBorderOs)
}

private fun traverseFromBorderOsAndRemoveConnected(
    board: Array<CharArray>,
    borderOs: List<Pair<Int, Int>>,
    nonBorderOs: MutableSet<Pair<Int, Int>>
) {
    val connected = mutableSetOf<Pair<Int, Int>>() // collect location of Os that are connected to border Os
    borderOs.forEach {
        isConnectedToBorderOsOrNull(board, it, it.first + 1 to it.second, connected)
        isConnectedToBorderOsOrNull(board, it, it.first to it.second + 1, connected)
        isConnectedToBorderOsOrNull(board, it, it.first - 1 to it.second, connected)
        isConnectedToBorderOsOrNull(board, it, it.first to it.second - 1, connected)
    }

    // Now that we have all the Os that are connected to the border
    nonBorderOs.forEach {
        if (it.first == 0 || it.first == board.lastIndex || it.second == 0 || it.second == board[0].lastIndex) {
            return@forEach // ignore Os at the borders
        } else if (!connected.contains(it)) {
            board[it.first][it.second] = 'X' // if not connected to border, then flip it
        }
    }
}

private fun isConnectedToBorderOsOrNull(
    board: Array<CharArray>,
    src: Pair<Int, Int>,
    dest: Pair<Int, Int>,
    connected: MutableSet<Pair<Int, Int>>
) {
    if (dest.first > board.lastIndex || dest.first < 0 || dest.second > board[0].lastIndex || dest.second < 0) {
        return
    }
    if (connected.contains(dest)) {
        return // already seen, no need to traverse
    }

    // These two are connected and since this is called from the bordering Os,
    // so [src] & [dest] are also connected to border Os
    if (board[src.first][src.second] == 'O' && board[dest.first][dest.second] == 'O') {
        connected.add(dest)
        isConnectedToBorderOsOrNull(board, dest, dest.first + 1 to dest.second, connected)
        isConnectedToBorderOsOrNull(board, dest, dest.first to dest.second + 1, connected)
        isConnectedToBorderOsOrNull(board, dest, dest.first - 1 to dest.second, connected)
        isConnectedToBorderOsOrNull(board, dest, dest.first to dest.second - 1, connected)
    }
}

fun main() {
    run {
        val board = arrayOf(
            //           0    1    2    3
            charArrayOf('X', 'X', 'X', 'X'), // o
            charArrayOf('X', 'O', 'O', 'X'), // 1
            charArrayOf('X', 'X', 'O', 'X'), // 2
            charArrayOf('X', 'O', 'X', 'X') // 3
        )
        val expected = arrayOf(
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'O', 'X', 'X')
        )
        solve(board)
        board.forEachIndexed { index, chars ->
            chars shouldBe expected[index]
        }
    }

    run {
        val board = arrayOf(
            charArrayOf('X', 'O', 'X', 'O', 'X', 'O'),
            charArrayOf('O', 'X', 'O', 'X', 'O', 'X'),
            charArrayOf('X', 'O', 'X', 'O', 'X', 'O'),
            charArrayOf('O', 'X', 'O', 'X', 'O', 'X')
        )
        val expected =
            arrayOf(
                charArrayOf('X', 'O', 'X', 'O', 'X', 'O'),
                charArrayOf('O', 'X', 'X', 'X', 'X', 'X'),
                charArrayOf('X', 'X', 'X', 'X', 'X', 'O'),
                charArrayOf('O', 'X', 'O', 'X', 'O', 'X')
            )

        solve(board)
        board.forEachIndexed { index, chars ->
            chars shouldBe expected[index]
        }
    }

    run {
        val board =
            arrayOf(
                charArrayOf('X', 'O', 'X', 'X'),
                charArrayOf('O', 'X', 'O', 'X'),
                charArrayOf('X', 'O', 'X', 'O'),
                charArrayOf('O', 'X', 'O', 'X'),
                charArrayOf('X', 'O', 'X', 'O'),
                charArrayOf('O', 'X', 'O', 'X')
            )

        val expected =
            arrayOf(
                charArrayOf('X', 'O', 'X', 'X'),
                charArrayOf('O', 'X', 'X', 'X'),
                charArrayOf('X', 'X', 'X', 'O'),
                charArrayOf('O', 'X', 'X', 'X'),
                charArrayOf('X', 'X', 'X', 'O'),
                charArrayOf('O', 'X', 'O', 'X')
            )


        solve(board)
        board.forEachIndexed { index, chars ->
            println("at index $index")
            chars shouldBe expected[index]
        }
    }
}