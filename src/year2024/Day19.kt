package year2024

import util.core.Solution

class Day19 : Solution<Int, Int>(year = 2024, day = 19) {

    override fun part1(input: String): Int {
        val (a, b) = input.split("\n\n")
        val towels = a.split(", ")
        val designs = b.lines()
        fun possible(design: String): Boolean {
            val n = design.length
            val cache = BooleanArray(n + 1)
            cache[n] = true
            for (i in n - 1 downTo 0) {
                cache[i] = towels.any { towel ->
                    design.startsWith(towel, i) && cache[i + towel.length]
                }
            }
            return cache[0]
        }
        return designs.count { possible(it) }
    }

    override fun part2(input: String): Int {
        return 0
    }
}

fun main() = Day19().run {
    println(part1(input))
}