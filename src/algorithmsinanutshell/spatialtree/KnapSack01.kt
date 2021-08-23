package algorithmsinanutshell.spatialtree

import utils.PrintUtils
import kotlin.math.max
import kotlin.test.assertTrue

/**
 * Knapsack Problem: Given total capacity [totalCapacity] of a bag,
 * how can you select subset of items of weight [weights] with profit [profits] such that the profit is maximized?
 *
 * [Video](https://www.youtube.com/watch?v=8LusJS5-AGo)
 *
 * ```
 * | P | w | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
 * | 1 | 1 |   |   |   |   |   |   |   |   |
 * | 4 | 3 |   |   |   |   |   |   |   |   | <-- while here, everything below are ignored so w1 and w3 are considered
 * | 5 | 4 |   |   |   |   |   |   |   |   |
 * | 6 | 5 |   |   |   |   |   |   |   |   |
 * ```
 *
 */
class KnapSack01(val weights: IntArray, val profits: IntArray, val totalCapacity: Int) {

    private val matrix: Array<IntArray> = Array(profits.size + 1) {
        IntArray(totalCapacity + 1) {
            0
        }
    }

    fun getProfitMatrix(): Array<IntArray> {
        for (row in 1..profits.size) {
            for (currentCapacity in 0..totalCapacity) {
                if (weights[row - 1] <= currentCapacity) {
                    // Which item can fit after we choose weights[row-1]
                    val remainingSpace = currentCapacity - weights[row - 1]

                    matrix[row][currentCapacity] = max(
                        matrix[row - 1][currentCapacity],  // Previous best profit
                        // I select an item, its profit = values[row - 1]
                        // now I can select any item that can fit in the remaining space
                        (profits[row - 1]) + (matrix[row - 1][remainingSpace])
                    )
                } else {
                    matrix[row][currentCapacity] = matrix[row - 1][currentCapacity]
                }
            }
        }
        return matrix
    }

    fun getSelectedWeight(): MutableList<Int> {
        val selection = mutableListOf<Int>()
        // Start from the bottom right corner of the matrix
        var row = matrix.lastIndex
        var col = matrix[0].lastIndex
        for (i in weights.lastIndex downTo 0) {
            compare(row, col, selection).apply {
                row = this.first
                col = this.second
            }
        }
        println(selection)
        return selection
    }

    private fun compare(row: Int, col: Int, selection: MutableList<Int>): Pair<Int, Int> {
        val currentProfit = matrix[row][col]
        val profitOneLevelUp = matrix[row - 1][col]

        // Do nothing if total selected weight exceeds [totalCapacity]
        if (selection.sum() >= totalCapacity) {
            return row to col
        }

        // Compare current profit with profit directly above
        // Select current weight if they differ
        return if (currentProfit != profitOneLevelUp) {
            // Select the weight
            val candidateForSelection = weights[row - 1]
            selection.add(candidateForSelection)
            // Find remaining space after the weight is selected
            val remainingSpace = totalCapacity - candidateForSelection
            // Go one step above and then to remaining space available
            row - 1 to remainingSpace
        } else {
            // Else go one step above and repeat
            row - 1 to col
        }
    }
}

/**
 * @suppress
 */
fun main() {
    val weights = intArrayOf(1, 3, 4, 5)
    val profits = intArrayOf(1, 4, 5, 7)
    val totalCapacity = 7
    val algorithm = KnapSack01(
        weights = weights,
        profits = profits,
        totalCapacity = totalCapacity
    )
    algorithm.getProfitMatrix().map {
        PrintUtils.print(it.toList())
    }
    val selectedWeight = algorithm.getSelectedWeight()
    assertTrue {
        selectedWeight.sum() == totalCapacity
    }
    assertTrue {
        selectedWeight.map { weight ->
            val index = weights.indexOf(weight)
            profits[index]
        }.sum() == 9
    }
}