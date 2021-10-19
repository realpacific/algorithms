package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Minimum Deletions to Make Character Frequencies Unique
 *
 * A string s is called good if there are no two different characters in s that have the same frequency.
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 *
 * [Source](https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/) – [Solution](https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/discuss/1107954/Java-Simple-Solution)
 */
@UseCommentAsDocumentation
private fun minDeletions(s: String): Int {
    val countArray = IntArray(26) { 0 } // Maintain array of counts
    s.forEach {
        countArray[it - 'a']++
    }
    val alreadyTaken = mutableSetOf<Int>() // the counts that has already been seen
    var deletion = 0
    for (i in 0..countArray.lastIndex) {
        var counts = countArray[i]
        while (counts > 0 && alreadyTaken.contains(counts)) {
            countArray[i]-- // Count already exists so mark it for deletion
            counts = countArray[i]
            deletion++
        }
        alreadyTaken.add(counts) // Unique count
    }
    return deletion
}

fun main() {
    // s is already good.
    minDeletions("aab") shouldBe 0

    // You can delete two 'b's resulting in the good string "aaabcc".
    // Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
    minDeletions(s = "aaabbbcc") shouldBe 2

    // You can delete both 'c's resulting in the good string "eabaab".
    minDeletions(s = "ceabaacb") shouldBe 2

}