package year2022

import util.core.*
import util.parse.extractInts
import util.point.*
import kotlin.math.abs

class Day15(
    private val row: Int = 2000000,
    private val range: IntRange = 0..4000000
) : Solution<Int, Long>(year = 2022, day = 15) {

    fun List<List<Point>>.scanArea(row: Int) = this.map { (scanner, beacon) ->
        val yDistance = abs(scanner.j - row)
        val distance = scanner.distanceTo(beacon)
        if (yDistance <= distance) {
            (scanner.i - distance) + yDistance..(scanner.i + distance) - yDistance
        } else IntRange.EMPTY
    }

    fun parseInput(input: String) = input
        .lines()
        .map { it.extractInts() }
        .flatMap { (x1, y1, x2, y2) -> listOf( Point(x1, y1), Point(x2, y2)) }
        .chunked(2)

    fun List<IntRange>.merge() = this
        .filter { !it.isEmpty() }
        .sortedBy { it.first }
        .foldIndexed(ArrayDeque<IntRange>()) { idx, stack, range ->
            if (idx == 0) stack.add(range)
            else if (range.last <= stack.last().last) return@foldIndexed stack
            else if (range.first > stack.last().last + 1) stack.add(range)
            else stack.add(stack.removeLast().first..range.last)
            stack
        }

    fun List<IntRange>.notIncludes(otherRange: IntRange): IntRange? = this.firstOrNull {
        otherRange.first < it.first || otherRange.last > it.last
    }

    override fun part1(input: String): Int {
        val report = parseInput(input)
        val ans = report.scanArea(row).merge()

        return ans.sumOf { it.last - it.first }
    }

    override fun part2(input: String): Long {
        val report = parseInput(input)
        for (row in range) {
            val ans = report.scanArea(row).merge().notIncludes(range)
            if (ans != null) {
                val col = (range.toSet() - ans.toSet()).first()
                return col * 4000000L + row
            }
        }

        return 0
    }
}

fun main() = Day15().run {
    println(part1(input))
    println(part2(input))
}