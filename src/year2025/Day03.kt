package year2025

import util.core.*
import kotlin.math.pow

class Day03 : Solution<Int, Long>(year = 2025, day = 3) {

    override fun part1(input: String): Int {
        var sum = 0
        for (line in input.lines()) {
            var max = -1
            var maxPos = -1
            for ((i, d) in line.withIndex()) {
                if (i == line.lastIndex) break
                val di = d.digitToInt()
                if (di > max) {
                    max = di
                    maxPos = i
                }
            }
            var secMax = -1
            for (d in line.substring(maxPos + 1)) {
                val di = d.digitToInt()
                if (di > secMax) {
                    secMax = di
                }
            }
            val res = max * 10 + secMax
            sum += res
        }
        return sum
    }

    override fun part2(input: String): Long {
        val m = 11
        var sum = 0L
        for (line in input.lines()) {
            val maximums = Array(12) { -1 to -1 }
            for (n in 0..m) {
                val startIndex = if (n > 0) maximums[n-1].second + 1 else 0
                for ((i, d) in line.substring(startIndex).withIndex()) {
                    if ((i + startIndex) > line.lastIndex - (m - n)) break
                    val di = d.digitToInt()
                    if (di > maximums[n].first) {
                        maximums[n] = di to i + startIndex
                    }
                }
            }
            val z = maximums.mapIndexed { i, p -> (p.first * 10.0.pow(m - i)).toLong() }.sum()
            sum += z
        }
        return sum
    }
}

fun main() = Day03().run {
    println(part1(input))
    println(part2(input))
}