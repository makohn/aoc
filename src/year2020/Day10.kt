package year2020

import util.core.*
import util.parse.*

class Day10 : Solution<Int, Long> {

    override fun part1(input: String): Int {
        val adapters = input.extractInts().sorted()
        val joltDiffs = intArrayOf(0, 0, 0, 1)
        joltDiffs[adapters[0]] += 1
        for ((a, b) in adapters.windowed(2)) {
            val diff = b - a
            joltDiffs[diff] += 1
        }
        return joltDiffs[1] * joltDiffs[3]
    }

    override fun part2(input: String): Long {
        val adapters = input.extractInts().sorted()
        val last = adapters.last()
        val sum = LongArray(last + 1)
        sum[0] = 1
        for (a in adapters) {
            sum[a] = when (a) {
                1 -> sum[0]
                2 -> sum[1] + sum[0]
                else -> sum[a - 1] + sum[a - 2] + sum[a - 3]
            }
        }
        return sum[last]
    }
}
