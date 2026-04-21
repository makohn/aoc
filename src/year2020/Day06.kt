package year2020

import util.core.*

class Day06 : Solution<Int, Int> {

    override fun part1(input: String): Int = input.split("\n\n").sumOf { group -> (group.toSet() - '\n').size }

    override fun part2(input: String): Int = input
        .split("\n\n")
        .sumOf { group ->
            group.lines().map { line -> line.fold(0) { a, b -> a or (1 shl (b - 'a')) } }.reduce { a, b -> a and b }
                .countOneBits()
        }
}
