package year2020

import util.core.*
import util.parse.*

class Day16 : Solution<Int, Long> {

    private data class Rule(val a: Int, val b: Int, val c: Int, val d: Int, val departure: Boolean = false)

    private fun Rule(line: String): Rule {
        val departure = line.startsWith("departure")
        val (a, b, c, d) = line.extractInts()
        return Rule(a, b, c, d, departure)
    }

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

    override fun part2(input: String): Long {
        val (first, second, third) = input.split("\n\n")
        val rules = first.lines().map { Rule(it) }
        val yourTicket = second.extractInts()

        val validTickets = third.extractInts()
            .chunked(rules.size)
            .filter { ticket ->
                ticket.all { number ->
                    rules.any { rule ->
                        number in rule.a..rule.b ||
                            number in rule.c..rule.d
                    }
                }
            } + listOf(yourTicket)

        val nearbyTickets = Array(rules.size) { ArrayList<Int>() }

        for (chunk in validTickets) {
            for ((i, number) in chunk.withIndex()) {
                nearbyTickets[i] += number
            }
        }

        val candidates = nearbyTickets.map { entry ->
            rules.filter { rule ->
                val (a, b, c, d) = rule
                entry.all { digit ->
                    digit in a..b || digit in c..d
                }
            }.toMutableSet()
        }

        val assigned = HashSet<Rule>()

        while (assigned.size < rules.size) {
            for (candidate in candidates) {
                if (candidate.size == 1) {
                    val rule = candidate.first()
                    if (assigned.add(rule)) {
                        candidates.forEach { it.remove(rule) }
                        candidate.add(rule)
                    }
                }
            }
        }

        var total = 1L

        for ((i, candidate) in candidates.withIndex()) {
            val rule = candidate.single()
            if (rule.departure) {
                total *= yourTicket[i]
            }
        }

        return total
    }
}
