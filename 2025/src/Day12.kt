import aoc.extractInts

fun main() {

    fun part1(input: List<String>): Int {
        val (instructions, _) = input.partition { it.contains("x") }
        return instructions
            .map { line -> line.extractInts() }
            .count { ints -> (ints[0] / 3) * (ints[1] / 3) >= ints.drop(2).sum() }
    }

    val input = readInput("Day12")
    part1(input).println()
}