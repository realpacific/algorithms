package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 *
 * You are given a character array containing a set of words separated by whitespace.
 * Your task is to modify that character array so that the words all appear in reverse order.
 * Do this without using any extra space.
 */
@UseCommentAsDocumentation
fun swapWithoutExtraSpace(input: CharArray): CharArray {
    // reverse the input inplace
    input.reverse()

    // swap each letter in the word
    var start = 0
    for (i in 0..input.size) {
        val current = input.getOrNull(i) // loops till length not indices
        if (current == ' ' || current == null) {
            _swapLetters(input, start, i - 1)
            start = i + 1
        }
    }
    return input
}

/**
 * Do it without having trailing or multiple white spaces. Move extra whitespaces to the end
 */
fun swapWithoutTrailingOrMultipleWhitespace(input: CharArray): CharArray {
    // reverse the input inplace
    input.reverse()

    var wordsCount = 0

    // swap each letter in the word

    // keep record of where the swap starts from
    var replaceStartsFrom = 0
    for (i in 0..input.size) {
        val current = input.getOrNull(i)

        if (current == null) {
            _swapLetters(input, replaceStartsFrom, i - 1)
        } else {
            if (!current.isWhitespace()) { // not whitespace so must be a word
                wordsCount += 1
            }
            if (wordsCount == 0) { // only spaces till now
                continue
            }
            if (wordsCount > 0 && current.isWhitespace()) { // words encountered and right now at whitespace
                // swap words and previously encountered whitespace so that previous whitespaces are shifted to the end
                _swapLetters(input, replaceStartsFrom, i - 1)
                // move to the position of last word
                // add 1 for the one whitespace in between two words
                // and consider remaining whitespaces for swapping in later iteration
                replaceStartsFrom += wordsCount + 1
                wordsCount = 0
            }
        }
    }
    return input
}


private fun _swapLetters(arr: CharArray, startIndex: Int, endIndex: Int) {
    var startFrom = startIndex
    var endAt = endIndex
    val till = (endIndex - startFrom) / 2
    for (i in 0..till) {
        val temp = arr[startFrom]
        arr[startFrom] = arr[endAt]
        arr[endAt] = temp
        startFrom++
        endAt--
    }
}

fun main() {
    run {
        val input = charArrayOf('A', 'l', 'i', 'c', 'e', ' ', 'l', 'i', 'k', 'e', 's', ' ', 'B', 'o', 'b')
        swapWithoutExtraSpace(input) shouldBe charArrayOf(
            'B',
            'o',
            'b',
            ' ',
            'l',
            'i',
            'k',
            'e',
            's',
            ' ',
            'A',
            'l',
            'i',
            'c',
            'e'
        )
    }

    run {
        val input = "Algorithm repository"
        swapWithoutExtraSpace(input.toCharArray()) shouldBe "repository Algorithm".toCharArray()
    }

    run {
        val input = "Algorithm"
        swapWithoutExtraSpace(input.toCharArray()) shouldBe "Algorithm".toCharArray()
    }

    run {
        val input = "algorithm repository reverse words"
        swapWithoutExtraSpace(input.toCharArray()) shouldBe "words reverse repository algorithm".toCharArray()
    }

    run {
        val input = "  reverse  words  "
        swapWithoutTrailingOrMultipleWhitespace(input.toCharArray()) shouldBe "words reverse     ".toCharArray()
    }

    run {
        val input = "  words  "
        swapWithoutTrailingOrMultipleWhitespace(input.toCharArray()) shouldBe "words    ".toCharArray()
    }

    run {
        val input = "  reverse words  "
        swapWithoutTrailingOrMultipleWhitespace(input.toCharArray()) shouldBe "words reverse    ".toCharArray()
    }

    run {
        val input = "  Alice  likes  Bob  "
        swapWithoutTrailingOrMultipleWhitespace(input.toCharArray()) shouldBe "Bob likes Alice      ".toCharArray()
    }

    run {
        val input = "  "
        swapWithoutTrailingOrMultipleWhitespace(input.toCharArray()) shouldBe "  ".toCharArray()
    }
}