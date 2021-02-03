package questions

/**
 * Given a phone number should format in the form of abc-def-ijk. Last two part can be of 2 digits
 */
fun main() {
    solution("00-44    48 5555 83612").also { println(it) }
    solution("00-44    48 5555 8361").also { println(it) }
    solution("00-44    48 5555 836122").also { println(it) }
    solution("00-44    48 5555").also { println(it) }
    solution("00-4").also { println(it) }
    solution("00").also { println(it) }
    solution("0    0   ").also { println(it) }
}

fun solution(S: String): String {
    val digits = S.toCharArray().filter { Character.isDigit(it) }
    if (digits.size < 3) {
        return digits.joinToString("")
    }
    val remainder = digits.size % 3
    if (remainder == 0) {
        return formatPhoneNumber(digits).toString()
    } else {
        val division = digits.size / 3
        if (remainder == 2) {
            val numberOfItemsToTake = division * 3
            val formattableTo3digits = digits.subList(0, numberOfItemsToTake)
            return formatPhoneNumber(formattableTo3digits)
                    .append("-")
                    .append(digits[numberOfItemsToTake])
                    .append(digits[numberOfItemsToTake + 1]).toString()
        } else {
            val numberOfItemsToTake = (division - 1) * 3
            val formattableTo3digits = digits.subList(0, numberOfItemsToTake)
            return formatPhoneNumber(formattableTo3digits)
                    .append("-")
                    .append(digits[numberOfItemsToTake + 0])
                    .append(digits[numberOfItemsToTake + 1])
                    .append("-")
                    .append(digits[numberOfItemsToTake + 2])
                    .append(digits[numberOfItemsToTake + 3])
                    .toString()
        }
    }
}

fun formatPhoneNumber(digits: List<Char>): StringBuilder {
    val sb = StringBuilder()
    digits.forEachIndexed { index, c ->
        sb.append(c)
        if ((index + 1) % 3 == 0 && index < digits.lastIndex) {
            sb.append("-")
        }
    }
    return sb
}
