package year2022

import util.core.*

class Day02 : Solution<Int, Int>(year = 2022, day = 2) {

    private fun toScore(c:  Char) = when(c) {
        'X',
        'A' -> 1
        'Y',
        'B' -> 2
        'Z',
        'C' -> 3
        else -> throw IllegalArgumentException()
    }

    private fun calcScore(o: Char, m: Char): Int {
        val sm = toScore(m)
        val so = toScore(o)
        when (m) {
            'X' -> when (o) {
                'A' -> return sm + 3
                'B' -> return sm
                'C' -> return sm + 6
            }
            'Y' -> when (o) {
                'A' -> return sm + 6
                'B' -> return sm + 3
                'C' -> return sm
            }
            'Z' -> when (o) {
                'A' -> return sm
                'B' -> return sm + 6
                'C' -> return sm + 3
            }
        }
        return -1
    }

    private fun calcScore2(o: Char, m: Char): Int {
        when (m) {
            'X' -> when (o) {
                'A' -> return toScore('Z')
                'B' -> return toScore('X')
                'C' -> return toScore('Y')
            }
            'Y' -> when (o) {
                'A' -> return toScore('X') + 3
                'B' -> return toScore('Y') + 3
                'C' -> return toScore('Z') + 3
            }
            'Z' -> when (o) {
                'A' -> return toScore('Y') + 6
                'B' -> return toScore('Z') + 6
                'C' -> return toScore('X') + 6
            }
        }
        return -1
    }

    override fun part1(input: String) = input.lines()
        .map { it.split(" ") }.sumOf { calcScore(it.first()[0], it.last()[0]) }

    override fun part2(input: String) = input.lines()
        .map { it.split(" ") }.sumOf { calcScore2(it.first()[0], it.last()[0]) }
}

fun main() = Day02().run {
    println(part1(input))
    println(part2(input))
}
