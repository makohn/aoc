package year2022

import util.core.*

class Day01 : Solution<Int, Int>(year = 2022, day = 1) {

    override fun part1(input: String): Int {
        val calories = mutableListOf<Int>()
        var count = 0
        for (s in input.lines()) {
            if (s.isEmpty()) {
                calories.add(count)
                count = 0
            } else {
                count += s.toInt()
            }
        }
        return calories.max()
    }

    override fun part2(input: String): Int {
        val calories = mutableListOf<Int>()
        var count = 0
        for (s in input.lines()) {
            if (s.isEmpty()) {
                calories.add(count)
                count = 0
            } else {
                count += s.toInt()
            }
        }
        calories.add(count)
        var total = 0
        total += calories.max()
        calories.remove(calories.max())
        total += calories.max()
        calories.remove(calories.max())
        total += calories.max()
        return total
    }
}

fun main() = Day01().run {
    println(part1(input))
    println(part2(input))
}