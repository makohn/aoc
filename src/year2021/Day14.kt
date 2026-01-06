package year2021

import util.Solution
import kotlin.collections.get

class Day14 : Solution<Long, Long>(year = 2021, day = 14) {

    override fun part1(input: String): Long {
        val (template, rules) = parse(input)
        val occurrences = simulatePolymer(template, rules, steps = 10).values
        return 0L + occurrences.maxOf { it } - occurrences.minOf { it }
    }

    override fun part2(input: String): Long {
        val (template, rules) = parse(input)
        val occurrences = simulatePolymer(template, rules).values
        return 0L + occurrences.maxOf { it } - occurrences.minOf { it }
    }

    private fun parse(input: String): Pair<String, Map<String, String>> {
        val inputLines = input.lines()
        val template = inputLines.first()
        val rules = inputLines.drop(2)
            .map { it.split(" -> ") }
            .associate { it.first() to it.last() }
        return template to rules
    }

    private fun buildPolymer(template: String, rules: Map<String, String>, steps: Int = 10): String {
        var polymer = template
        repeat(steps) {
            polymer = polymer.windowed(2) {
                if (rules.contains(it)) "${it[0]}${rules[it]}${it[1]}" else it
            }.also { println(it) }.foldRight("") { acc, e -> "${acc}${e.drop(1)}"}
        }
        return polymer
    }

    private fun simulatePolymer(template: String, rules: Map<String, String>, steps: Int = 40): Map<String, Long> {
        val pairRules = rules.map { it.key to (it.key[0]+it.value to it.value+it.key[1]) }.toMap()
        var polymer = template.windowed(2).groupingBy { it }.fold(0L) { acc, _ -> acc + 1 }
        repeat(steps) {
            val newPolymer = mutableMapOf<String, Long>()
            polymer.entries.forEach { entry ->
                pairRules[entry.key]?.toList()?.forEach { rule ->
                    newPolymer[rule] = newPolymer.getOrDefault(rule, 0) + entry.value
                }
            }
            polymer = newPolymer
        }
        return polymer.flatMap { listOf(it.key[0].toString() to it.value, it.key[1].toString() to it.value) }
            .groupingBy { it.first }
            .fold(0L) { acc, e -> acc + (e.second/2.0).toLong() }
    }
}

fun main() = Day14().run {
    println(part1(input))
    println(part2(input))
}
