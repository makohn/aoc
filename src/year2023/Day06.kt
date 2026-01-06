package year2023

import util.core.*
import util.math.product

class Day06 : Solution<Int, Int>(year = 2023, day = 6) {

    override fun part1(input: String): Int {
        val (raceLengths, records) = input.lines().map { it.substringAfter(":").split("\\s".toRegex()).filter { it.isNotEmpty() }.map { it.toInt() } }

        val ans = raceLengths.withIndex().map { (idx, len) ->
            var count = 0
            for (i in 0..len) {
                if ((len - i) * i > records[idx]) count ++
            }
            count
        }
        return ans.product()
    }

    override fun part2(input: String): Int {
        val (raceLength, record) = input.lines().map { it.substringAfter(":").split("\\s".toRegex()).filter { it.isNotEmpty() }.joinToString("").toLong() }

        var count = 0
        for (i in 0..raceLength) {
            if ((raceLength - i) * i > record) count ++
        }

        return count
    }
}

fun main() = Day06().run {
    println(part1(input))
    println(part2(input))
}