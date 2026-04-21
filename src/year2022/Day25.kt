package year2022

import util.core.*

class Day25 : Solution<String, Int> {

    private fun fromSnafu(snafu: String): Long = snafu.fold(0) { acc, c ->
        val digit = when (c) {
            '=' -> -2
            '-' -> -1
            '0' -> 0
            '1' -> 1
            '2' -> 2
            else -> error(c)
        }
        5 * acc + digit
    }

    private fun Long.toSnafu(): String {
        var n = this
        val digits = ArrayList<Char>()
        while (n > 0) {
            val next = when (n % 5) {
                0L -> '0'
                1L -> '1'
                2L -> '2'
                3L -> '='
                4L -> '-'
                else -> error(n)
            }
            digits.add(next)
            n = (n + 2) / 5
        }
        return digits.reversed().joinToString("")
    }

    override fun part1(input: String): String = input.lines().sumOf { fromSnafu(it) }.toSnafu()

    override fun part2(input: String): Int = 0
}
