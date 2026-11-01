package year2020

import util.core.*
import util.parse.extractInts

class Day16 : Solution<Int, Int> {

    private data class Rule(val a: Int, val b: Int, val c: Int, val d: Int)

    override fun part1(input: String): Int {
        val (first, _, third) = input.split("\n\n")
        val rules = first.extractInts().chunked(4).map { (a, b, c, d) -> Rule(a, b, c, d) }
        val nearbyTickets = third.split('\n').map { it.extractInts() }
        var total = 0
        for (ticket in nearbyTickets) {
            nextNumber@ for (number in ticket) {
                for ((a, b, c, d) in rules) {
                    if (number in a..b || number in c..d) {
                        continue@nextNumber
                    }
                }
                total += number
            }
        }
        return total
    }

    override fun part2(input: String): Int = 0
}
