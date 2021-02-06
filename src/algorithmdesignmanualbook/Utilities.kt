package algorithmdesignmanualbook

import java.util.*

inline fun <T> withPrint(msg: String, block: () -> T): T {
    println("${"-".repeat(4)} $msg ${"-".repeat(4)}")
    val result = block()    
    return result
}

fun <T> T.print(printer: ((T) -> Any)? = null): T {
    val value = printer?.invoke(this) ?: this

    if (printer == null && this is Array<*>) {
        println(Arrays.toString(this))
        return this
    }

    println(value)
    return this
}