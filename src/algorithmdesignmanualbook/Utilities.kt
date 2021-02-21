package algorithmdesignmanualbook

import java.util.*

inline fun <T> withPrint(msg: String, block: () -> T): T {
    println("${"-".repeat(4)} $msg ${"-".repeat(4)}")
    val result = block()
    return result
}

fun <T> T.print(msg: String? = null, printer: ((T) -> Any)? = null): T {
    kotlin.io.print("${msg ?: "Result"}: ")
    val value = printer?.invoke(this) ?: this

    if (printer == null) {
        when (this) {
            is Array<*> -> {
                println(Arrays.toString(this))
                return this
            }
            is IntArray -> {
                println(this.toList())
                return this
            }
        }
    }

    println(value)
    return this
}