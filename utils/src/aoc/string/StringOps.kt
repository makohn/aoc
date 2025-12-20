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
fun String.extractInts() = Regex("[+-]?\\d+").findAll(this).map { it.value.toInt() }.toList()

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
fun String.extractLongs() = Regex("[+-]?\\d+").findAll(this).map { it.value.toLong() }.toList()

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