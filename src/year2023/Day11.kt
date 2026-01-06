package year2023

import util.core.*
import util.iter.transpose
import kotlin.math.abs

class Day11(val addEmpty: Int = 1_000_000) : Solution<Int, Long>(year = 2023, day = 11) {

    override fun part1(input: String): Int {
        val universe = mutableListOf<String>()
        for (row in input.lines()) {
            universe.add(row)
            if ("#" !in row) {
                universe.add(row)
            }
        }
        val input2 = universe.map { it.toList() }.transpose().map { it.joinToString("") }
        val interimUniverse = mutableListOf<String>()
        for (row in input2) {
            interimUniverse.add(row)
            if ("#" !in row) {
                interimUniverse.add(row)
            }
        }
        val expandedUniverse = interimUniverse.map { it.toList() }.transpose().map { it.joinToString("") }

        val galaxies = mutableListOf<Pair<Int, Int>>()

        for ((i, row) in expandedUniverse.withIndex()) {
            for ((j, col) in row.withIndex()) {
                if (col == '#') {
                    galaxies.add(i to j)
                }
            }
        }

        var ans = 0
        for ((i, galaxy) in galaxies.withIndex()) {
            for (otherGalaxy in galaxies.drop(i)) {
                ans += abs(galaxy.first - otherGalaxy.first) + abs(galaxy.second - otherGalaxy.second)
            }
        }
        return ans
    }

    override fun part2(input: String): Long {
        val inputLines = input.lines()
        val galaxyRows = mutableListOf(0)
        for (row in inputLines) {
            galaxyRows.add(galaxyRows.last() + if ("#" !in row) addEmpty else 1)
        }
        val input2 = inputLines.map { it.toList() }.transpose().map { it.joinToString("") }

        val galaxyCols = mutableListOf(0)
        for (row in input2) {
            galaxyCols.add(galaxyCols.last() + if ("#" !in row) addEmpty else 1)
        }
        val galaxies = mutableListOf<Pair<Int, Int>>()

        for ((i, row) in inputLines.withIndex()) {
            for ((j, col) in row.withIndex()) {
                if (col == '#') {
                    galaxies.add(i to j)
                }
            }
        }

        var ans = 0L
        for ((i, galaxy) in galaxies.withIndex()) {
            for (otherGalaxy in galaxies.drop(i)) {
                ans += abs(galaxyRows[galaxy.first] - galaxyRows[otherGalaxy.first]) +
                        abs(galaxyCols[galaxy.second] - galaxyCols[otherGalaxy.second])
            }
        }
        return ans
    }
}

fun main() = Day11().run {
    println(part1(input))
    println(part2(input))
}