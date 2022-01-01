package questions

import _utils.UseCommentAsDocumentation
import utils.assertIterableSameInAnyOrder

/**
 * A valid IP address consists of exactly four integers separated by single dots.
 * Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed
 * by inserting dots into s. You are not allowed to reorder or remove any digits in s.
 * You may return the valid IP addresses in any order.
 *
 * [Source](https://leetcode.com/problems/restore-ip-addresses/)
 */
@UseCommentAsDocumentation
private fun restoreIpAddresses(s: String): List<String> {
    if (s.length == 4) {
        val ip = s.toCharArray().joinToString(".")
        return listOf(ip)
    }
    // invalid ips
    if (s.length < 4 || s.length > 12) {
        return emptyList()
    }
    val result = mutableListOf<IPAddress>()
    generateIPAddress(s, 0, result)

    return result.mapNotNull { it.stringOrNull() }.distinct()
}

private class IPAddress {
    private var address = mutableListOf<String>()

    fun put(value: Char) {
        if (address.isEmpty()) {
            address.add(value.toString())
            return
        }
        val last = address.last()
        val newValue = last + value
        if (newValue.toInt() <= 255) { // replace current field
            address[address.lastIndex] = newValue
        } else { // can't be accommodated into current field
            address.add(value.toString())
        }
    }

    fun putInNext(value: Char) {
        address.add(value.toString())
    }

    fun clone(): IPAddress {
        return IPAddress().let {
            it.address = mutableListOf<String>().apply { this.addAll(address) }
            it
        }
    }

    fun isNoLongerValid(): Boolean {
        return address.size > 4 || address.find { it.length > 1 && it.startsWith("0") } != null
    }

    fun stringOrNull(): String? {
        if (address.size != 4) return null
        if (address.find { it.length > 1 && it.startsWith("0") } != null) return null
        return toString()
    }

    override fun toString(): String {
        return address.joinToString(".") { it }
    }
}

private fun generateIPAddress(s: String, index: Int, result: MutableList<IPAddress>) {
    if (index > s.lastIndex) {
        return
    }
    val character = s[index]

    if (result.isEmpty()) { // add the first element
        val address = IPAddress().apply { put(character) }
        result.add(address)
        generateIPAddress(s, index + 1, result)
        return
    }

    val newIps = mutableListOf<IPAddress>()
    result.forEach {
        // when adding a new character to IP, you can either add it to currently active field
        // or put it in next field

        // add it to currently active field i.e. 10.1.. --2--> 10.12..
        newIps.add(it.clone().apply {
            this.put(character)
        })
        // put it in the next field i.e. 10.1.. --2--> 10.1.2.
        newIps.add(it.clone().apply {
            this.putInNext(character)
        })
    }
    result.clear()

    // early break. If NONE of the current ips are no longer valid, then leave the remaining characters
    // as adding any other invalid will still make it invalid eg 530474625546 --> 53.0.47.46 is no longer valid
    if (newIps.filter { !it.isNoLongerValid() }.isEmpty()) {
        return
    }

    result.addAll(newIps)
    generateIPAddress(s, index + 1, result)
}

fun main() {
    assertIterableSameInAnyOrder(
        restoreIpAddresses("530474625546"),
        listOf()
    )
    assertIterableSameInAnyOrder(
        restoreIpAddresses("103574606193"),
        listOf()
    )
    assertIterableSameInAnyOrder(
        restoreIpAddresses("255255255255"),
        listOf("255.255.255.255")
    )
    assertIterableSameInAnyOrder(
        restoreIpAddresses("25525511135"),
        listOf("255.255.11.135", "255.255.111.35")
    )
    assertIterableSameInAnyOrder(
        restoreIpAddresses("0000"),
        listOf("0.0.0.0")
    )
    assertIterableSameInAnyOrder(
        restoreIpAddresses("101023"),
        listOf("1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3")
    )
}