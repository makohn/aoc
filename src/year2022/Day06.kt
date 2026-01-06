package year2022

import util.core.*

class Day06 : Solution<Int, Int>(year = 2022, day = 6) {

    fun findMarker(input: String, windowSize: Int) = input
        .withIndex()
        .windowed(windowSize, 1)
        .first { it.map { i -> i.value }.distinct().size == windowSize }
        .last()
        .index + 1

    override fun part1(input: String) = findMarker(input, 4)

    override fun part2(input: String) = findMarker(input, 14)
}

fun main() = Day06().run {
    println(part1(input))
    println(part2(input))
}