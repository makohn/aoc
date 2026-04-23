package year2020

import util.core.*

class Day06 : Solution<Int, Int> {

    private fun count(group: String, operation: (Int, Int) -> Int) = group
        .lines()
        .map { it.fold(0) { a, b -> a or (1 shl (b - 'a')) } }
        .reduce(operation)
        .countOneBits()

    override fun part1(input: String): Int = input
        .split("\n\n")
        .sumOf { count(it) { a, b -> a or b } }

    override fun part2(input: String): Int = input
        .split("\n\n")
        .sumOf { count(it) { a, b -> a and b } }
}
