package algorithmdesignmanualbook

inline fun <T> withPrint(msg: String, block: () -> T): T {
    println("${"-".repeat(4)} $msg ${"-".repeat(4)}")
    val result = block()
    println("_".repeat(msg.length + 8))
    return result
}