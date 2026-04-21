package year2021

import util.core.*

class Day01 : Solution<Int, Int> {

    override fun part1(input: String): Int = input
        .lines()
        .map(String::toInt)
        .zipWithNext(::hasIncreased)
        .sum()

    override fun part2(input: String): Int = input
        .lines()
        .map(String::toInt)
        .windowed(3) { it.sum() }
        .zipWithNext(::hasIncreased)
        .sum()

    private fun hasIncreased(a: Int, b: Int) = (b > a).compareTo(false)
}
