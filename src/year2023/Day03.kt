package year2023

import util.core.*
import util.grid.*
import util.math.product

class Day03 : Solution<Int, Int>(year = 2023, day = 3) {

    fun Char.isSymbol(): Boolean {
        return !this.isDigit() && this != '.'
    }

    override fun part1(input: String): Int {
        val schematic = input.lines().toCharGrid()
        val partNumbers = ArrayList<Int>()

        var number = ""
        var isPartNumber = false

        fun reset() {
            if (number.isNotEmpty() && isPartNumber) {
                partNumbers.add(number.toInt())
            }
            number = ""
            isPartNumber = false
        }

        for ((i, row) in schematic.withIndex()) {
            for ((j, char) in row.withIndex()) {
                when (char) {
                    in '0'..'9' -> {
                        number += char
                        if (schematic.neighborsOf(i, j).any { it.data.isSymbol() }) isPartNumber = true
                    }
                    else -> reset()
                }
            }
            reset()
        }

        return partNumbers.sum()
    }

    override fun part2(input: String): Int {
        val schematic = input.lines().toCharGrid()
        val gearPositions = HashSet<CharPoint>()
        val gearCandidates = HashMap<CharPoint, MutableList<Int>>()

        var number = ""

        fun reset() {
            if (number.isNotEmpty()) {
                for (pos in gearPositions) {
                    gearCandidates.putIfAbsent(pos, ArrayList())
                    gearCandidates[pos]?.add(number.toInt())
                }
                number = ""
                gearPositions.clear()
            }
        }

        for ((i, row) in schematic.withIndex()) {
            for ((j, char) in row.withIndex()) {
                when (char) {
                    in '0'..'9' -> {
                        number += char
                        gearPositions.addAll(schematic
                            .neighborsOf(i, j)
                            .filter { it.data == '*' }
                            .toList())
                    }
                    else -> reset()
                }
            }
            reset()
        }

        return gearCandidates
            .map { it.value }
            .filter { it.size == 2 }
            .sumOf { it.product() }
    }
}

fun main() = Day03().run {
    println(part1(input))
    println(part2(input))
}