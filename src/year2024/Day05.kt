package year2024

import util.core.*
import util.parse.*

class Day05 : Solution<Int, Int>(year = 2024, day = 5) {

    private fun solve(input: String): Pair<Int, Int> {
        val (rules, updates) = input.split("\n\n")

        val order = Array(100) { IntArray(100) { 1 } }
        for ((start, end) in rules.extractInts().chunked(2)) {
            order[start][end] = -1
        }
        var part1 = 0
        var part2 = 0
        for (line in updates.lines()) {
            val update = line.extractInts()
            val middle = update.size / 2
            val sorted = update.sortedWith { start, end -> order[start][end] }
            if (sorted == update) part1 += update[middle] else part2 += sorted[middle]
        }
        return part1 to part2
    }

    override fun part1(input: String): Int {
        return solve(input).first
    }

    override fun part2(input: String): Int {
        return solve(input).second
    }
}