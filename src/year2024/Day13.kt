package year2024

import util.Solution
import util.core.component6
import util.parse.extractLongs

class Day13 : Solution<Long, Long>(year = 2024, day = 13) {

    private fun solve(input: String, add: Long = 0L): Long {
        val machines = input.extractLongs().chunked(6)
        var sum = 0L
        for (machine in machines) {
            var (ax, ay, bx, by, px, py) = machine
            px += add
            py += add
            val det = ax * by - ay * bx
            if (det == 0L) continue
            var a = by * px - bx * py
            var b = ax * py - ay * px
            if (a % det == 0L && b % det == 0L) {
                a /= det
                b /= det
                sum += 3 * a + b
            }
        }
        return sum
    }

    override fun part1(input: String) = solve(input)

    override fun part2(input: String) = solve(input, 10000000000000)
}

fun main() = Day13().run {
    println(part1(input))
    println(part2(input))
}