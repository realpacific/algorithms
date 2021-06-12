package algorithmsinanutshell

import kotlin.test.assertEquals

/**
 * Greatest common divisor using Euclidean Algorithm
 *
 * [Link](https://www.freecodecamp.org/news/euclidian-gcd-algorithm-greatest-common-divisor/)
 */
private fun calculateGcd(a: Int, b: Int): Int {
  var (low, high) = if (a < b) Pair(a, b) else Pair(b, a)
  while ((high % low) > 0) {
    val remainder = high % low
    if (remainder == 0) {
      return low
    }
    high = low
    low = remainder
  }
  return low
}

fun main() {
  assertEquals(4, calculateGcd(1220, 516))
  assertEquals(10, calculateGcd(30, 20))
  assertEquals(3, calculateGcd(9, 6))
  assertEquals(4, calculateGcd(4, 12))
}