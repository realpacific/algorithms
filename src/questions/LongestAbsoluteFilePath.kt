package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Suppose we have a file system that stores both files and directories.
 *
 * <img src="https://assets.leetcode.com/uploads/2020/08/28/mdir.jpg" height="150" width="150"/>
 * Every file and directory has a unique absolute path in the file system,
 * which is the order of directories that must be opened to reach the file/directory itself, all concatenated by '/'s.
 *
 * Given a string input representing the file system in the explained format,
 * return the length of the longest absolute path to a file in the abstracted file system.
 * If there is no file in the system, return 0.
 *
 * [Source](https://leetcode.com/problems/longest-absolute-file-path/)
 */
@UseCommentAsDocumentation
private fun lengthLongestPath(input: String): Int {
    val split = input.split("\n")
    val lines = split.toList()
    val depthToPathMap = mutableMapOf<Int, MutableList<String>>() // maintain a mapping of depth -> paths
    for (i in 0..lines.lastIndex) {
        val row = lines[i]
        val depth = row.numberOfTabs()
        if (row.startsWithTabs()) {
            val parent = depthToPathMap[depth - 1]!!.last() // get its parent, it is always at last
            val absPath = "$parent/${row.replace("\t", "")}"

            // add its path to its corresponding depth at the last of list
            depthToPathMap[depth] = depthToPathMap.getOrElse(depth) { mutableListOf() }.apply {
                add(absPath)
            }
        } else {
            depthToPathMap[depth] = depthToPathMap.getOrElse(depth) { mutableListOf() }.apply {
                add(row)
            }
        }
    }
    // find the longest path
    var best = 0
    for (paths in depthToPathMap.values) {
        for (path in paths) {
            if (path.isFilePath()) { // only consider file path
                best = maxOf(best, path.length)
            }
        }
    }
    return best
}

private fun String.isFilePath(): Boolean {
    val split = split("/")
    return (split.last().contains("."))
}

private fun String.startsWithTabs(index: Int = 0): Boolean {
    return substring(index, index + 1) == "\t"
}

/**
 * Counts the number of '\t' characters at the beginning
 */
private fun String.numberOfTabs(): Int {
    var depth = 0
    var index = 0
    while (this.startsWithTabs(index)) {
        depth++
        index += 1
    }
    return depth
}

fun main() {
    lengthLongestPath(input = "file1.txt\nfile2.txt\nlongfile.txt") shouldBe 12


    lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext") shouldBe 20
    lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext") shouldBe 32
}