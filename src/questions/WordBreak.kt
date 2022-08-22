package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import kotlin.collections.HashSet

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a
 * space-separated sequence of one or more dictionary words.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * [Source](https://leetcode.com/problems/word-break/) – [Solution](https://leetcode.com/problems/word-break/discuss/43790/Java-implementation-using-DP-in-two-ways)
 */
@UseCommentAsDocumentation
private fun wordBreak(s: String, wordDict: List<String>): Boolean {
    val dict = HashSet<String>(wordDict)
    // https://leetcode.com/problems/word-break/discuss/43790/Java-implementation-using-DP-in-two-ways/43003
    // canBeBuilt[i] stands for whether subarray(0, i) can be segmented into words from the dictionary.
    // So canBeBuilt[0] means whether subarray(0, 0) (which is an empty string) can be segmented, and of course the answer is yes.
    val canBeBuilt = Array<Boolean?>(s.length + 1) { false }

    // The default value for boolean array is false. Therefore, we need to set canBeBuilt[0] to be true.
    canBeBuilt[0] = true
    for (i in 1..s.length) {
        for (j in 0 until i) {
            if (canBeBuilt[j] == true && dict.contains(s.substring(j, i))) {
                canBeBuilt[i] = true
                break
            }
        }
    }
    return canBeBuilt[s.length]!!
}


fun main() {
    wordBreak(s = "catsandog", wordDict = listOf("cats", "dog", "sand", "and", "cat")) shouldBe false
    wordBreak(s = "leetcode", wordDict = listOf("leet", "code")) shouldBe true
    wordBreak(s = "aaaaaaa", wordDict = listOf("aaaa", "aaa")) shouldBe true
    wordBreak(s = "applepenapple", wordDict = listOf("apple", "pen")) shouldBe true
}