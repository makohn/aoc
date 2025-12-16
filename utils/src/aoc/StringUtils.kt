package aoc

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