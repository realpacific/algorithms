package algorithmdesignmanualbook

inline fun <T> withBenchmark(block: () -> T): T {
    val startTime = System.currentTimeMillis()
    val result = block()
    val finishedTime = System.currentTimeMillis()
    println("Elapsed time ${finishedTime - startTime}ms")
    return result
}


inline fun <T> withBenchmark(count: Int, block: () -> T) {
    var sum = 0L
    repeat(count) {
        val startTime = System.nanoTime()
        block()
        val finishedTime = System.nanoTime()
        val diff = finishedTime - startTime
        println("Elapsed time ${diff}ns")
        sum += diff
    }
    println("Avg time ${sum / count}ns")
}