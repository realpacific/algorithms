package questions

import _utils.UseCommentAsDocumentation
import kotlin.test.assertEquals

/**
 * Assume you are an awesome parent and want to give your children some cookies.
 * But, you should give each child at most one cookie.

 * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with;
 * and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i,
 * and the child i will be content.
 * Your goal is to maximize the number of your content children and output the maximum number.
 *
 * [Source](https://leetcode.com/problems/assign-cookies/)
 */
@UseCommentAsDocumentation
private fun findContentChildren(g: IntArray, s: IntArray): Int {
    if (s.isEmpty()) return 0
    // Sort
    g.sort()
    s.sort()
    // Store index of g that was satisfied
    val result = mutableSetOf<Int>()

    for (i in 0..s.lastIndex) { // every cookie
        for (j in 0..g.lastIndex) { // loop over kids
            if (!result.contains(j) && g[j] <= s[i]) { // hasn't already been satisfied and can be satisfied
                result.add(j) // give cookie
                break
            }
        }
    }
    return result.size
}

private fun findContentChildrenOptimal(g: IntArray, s: IntArray): Int {
    if (s.isEmpty()) return 0
    // Sort
    g.sort()
    s.sort()
    var count = 0
    for (i in 0..s.lastIndex) { // every cookie
        val greed = g.getOrNull(count) ?: return count // try to satisfy the NEXT easiest/lowest greed factor first
        if (greed <= s[i]) count++
    }
    return count
}

fun main() {
    assertEquals(
        2, findContentChildrenOptimal(g = intArrayOf(10, 9, 8, 7), s = intArrayOf(5, 6, 7, 8))
    )
    assertEquals(
        4, findContentChildrenOptimal(g = intArrayOf(10, 9, 8, 7), s = intArrayOf(10, 9, 8, 7))
    )
    assertEquals(
        1, findContentChildrenOptimal(g = intArrayOf(1, 2, 3), s = intArrayOf(1, 1))
    )
    assertEquals(
        2, findContentChildrenOptimal(g = intArrayOf(1, 2), s = intArrayOf(1, 2, 3))
    )
}