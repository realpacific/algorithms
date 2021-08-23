package algorithmdesignmanualbook.heuristics.backtrack

class AllSubsets(private val nums: Array<Int>) {

    private val result = mutableListOf<MutableList<Int>>()

    fun execute(): List<List<Int>> {
        if (nums.isEmpty()) {
            return result
        }
        backtrack(0, mutableListOf())
        return result
    }

    private fun backtrack(index: Int, current: MutableList<Int>) {
        result.add(ArrayList(current))
        for (i in index..nums.lastIndex) {
            println("adding " + nums[i])
            current.add(nums[i])
            backtrack(i + 1, current)
            current.removeLastOrNull().also {
                println("rm " + it)
            }
        }
    }
}


/**
 * @suppress
 */
fun main() {
    run {
        val solution = AllSubsets(arrayOf(1, 2, 3))
        solution.execute().forEach {
            println(it)
        }
    }
}