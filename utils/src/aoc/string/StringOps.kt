@file:Suppress("DuplicatedCode")

package aoc.string

/**
 * Extracts all [Int]s from this [String], respecting their sign.
 *
 * For example
 * ```
 * "5x5: -1 10 -2 11 3".extractInts()
 * ```
 * provides the following list:
 * ```
 * [5, 5, -1, 10, -2, 11, 3]
 * ```
 *
 * @return a list of [Int]s found in this [String]
 */
fun String.extractInts(): List<Int> = buildList {
    val n = this@extractInts.length
    var i = 0

    nextInt@ while (true) {
        var value = 0
        var sign = 1

        while (true) {
            if (i == n) break@nextInt
            when (val c = this@extractInts[i++]) {
                in '0'..'9' -> {
                    value += c - '0'
                    break
                }

                '-' -> sign = -1
                else -> sign = 1
            }
        }

        while (i < n) {
            val c = this@extractInts[i++]
            if (c !in '0'..'9') break
            value = value * 10 + (c - '0')
        }

        add(value * sign)
    }
}

/**
 * Extracts all [Long]s from this [String], respecting their sign.
 *
 * For example
 * ```
 * "t=2147483648 reports x=2147483701 y=-2147483650".extractLongs()
 * ```
 * provides the following list:
 * ```
 * [2147483648, 2147483701, -2147483650]
 * ```
 *
 * @return a list of [Long]s found in this [String]
 */
fun String.extractLongs(): List<Long> = buildList {
    val n = this@extractLongs.length
    var i = 0

    nextLong@ while (true) {
        var value = 0L
        var sign = 1

        while (true) {
            if (i == n) break@nextLong
            when (val c = this@extractLongs[i++]) {
                in '0'..'9' -> {
                    value += c - '0'
                    break
                }

                '-' -> sign = -1
                else -> sign = 1
            }
        }

        while (i < n) {
            val c = this@extractLongs[i++]
            if (c !in '0'..'9') break
            value = value * 10 + (c - '0')
        }

        add(value * sign)
    }
}

/**
 * Extracts all [Double]s from this [String], respecting their sign.
 *
 * For example
 * ```
 * "pi=3.14159, e=2.71828".extractDoubles()
 * ```
 * provides the following list:
 * ```
 * [3.14159, 2.71828]
 * ```
 *
 * @return a list of [Double]s found in this [String]
 */
fun String.extractDoubles() = Regex("[+-]?\\d+(\\.\\d+)*").findAll(this).map { it.value.toDouble() }.toList()