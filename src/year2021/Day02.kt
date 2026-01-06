package year2021

import util.core.*

class Day02 : Solution<Int, Int>(year = 2021, day = 2) {

    data class Position(var hor: Int = 0, var aim: Int = 0, var depth: Int = 0)

    override fun part1(input: String): Int = common(input)
        .groupBy( { it.first }, { it.second } )
        .mapValues { it.value.sum() }
        .values
        .reduce(Int::times)

    override fun part2(input: String): Int = common(input)
        .fold(Position()) { pos, p -> if (p.first == "down")
            pos.apply { aim += p.second } else
            pos.apply { hor += p.second; depth += p.second * aim }}
        .let { it.depth * it.hor }

    private fun common(input: String): List<Pair<String, Int>> = input
        .lines()
        .map { it.split(" ") }
        .map { it.first() to it.last().toInt() }
        .map { if (it.first == "up") "down" to -it.second else it }
}

fun main() = Day02().run {
    println(part1(input))
    println(part2(input))
}
