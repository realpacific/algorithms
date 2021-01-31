package questions

import java.util.*
import kotlin.test.assertTrue

/**
 * Phone number substring search
 * If not found, return "NO_CONTACT"
 * IF multiple return the alphabetically lowest
 */
fun main() {
    val result = solution(arrayOf("amy", "fara", "ben", "nina", "jon"), arrayOf("9849000000", "9849000001", "9849002310", "9888022345", "334455661"), "9849")
    assertTrue(result == "amy")
}


private const val NO_CONTACT = "NO CONTACT"

fun solution(A: Array<String>, B: Array<String>, P: String): String {
    // Max length of phone number is 9
    if (P.length > 9) {
        return NO_CONTACT
    }
    val mapOfPhoneToName = mutableMapOf<String, String>()

    B.forEachIndexed { index, phone ->
        mapOfPhoneToName.put(phone, A[index])
    }

    val phoneNumbers = B.filter { it.contains(P) }
    if (phoneNumbers.isEmpty()) {
        return NO_CONTACT
    }
    val results = TreeSet<String>()
    phoneNumbers.forEach {
        results.add(mapOfPhoneToName[it]!!)
    }
    return results.first()
}
