package year2020

import util.core.*
import util.parse.*

class Day15 : Solution<Int, Int> {

    private fun play(input: String, rounds: Int): Int {
        val numbers = input.extractInts()
        val spoken = IntArray(rounds)
        var last = numbers[numbers.lastIndex]

        for (i in 0..<numbers.size) {
            spoken[numbers[i]] = i + 1
        }

        for (i in numbers.size..<rounds) {
            val previous = spoken[last]
            spoken[last] = i
            last = if (previous == 0) 0 else i - previous
        }

        return last
    }

    override fun part1(input: String): Int = play(input, 2020)

    override fun part2(input: String): Int = play(input, 30000000)
}
