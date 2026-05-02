package year2023

import util.core.*
import util.grid.*
import util.point.*

class Day03 : Solution<Int, Int> {

    private class Input(
        val grid: CharGrid,
        val seen: IntGrid,
        val parts: ArrayList<Int>,
    ) {
        operator fun component1(): CharGrid = grid
        operator fun component2(): IntGrid = seen
        operator fun component3(): ArrayList<Int> = parts
    }

    private fun parse(input: String): Input {
        val grid = input.lines().toCharGrid()
        val (width, height) = grid.shape
        val seen = IntGrid(height, width) { 0 }
        val parts = arrayListOf(0)
        var number = 0

        for (y in 0..<height) {
            for (x in 0..<width) {
                val position = Point(x, y)
                val char = grid[position]

                if (char.isDigit()) {
                    seen[position] = parts.size
                    number = 10 * number + char.digitToInt()
                } else if (number > 0) {
                    parts.add(number)
                    number = 0
                }
            }
            if (number > 0) {
                parts.add(number)
                number = 0
            }
        }

        return Input(grid, seen, parts)
    }

    override fun part1(input: String): Int {
        val (grid, seen, parts) = parse(input)
        val (width, height) = grid.shape
        var result = 0

        for (y in 0..<height) {
            for (x in 0..<width) {
                val position = Point(x, y)
                val char = grid[position]

                if (!char.isDigit() && char != '.') {
                    for (direction in DIRS_8) {
                        val index = seen[position + direction]
                        if (index != 0) {
                            result += parts[index]
                            parts[index] = 0
                        }
                    }
                }
            }
        }
        return result
    }

    override fun part2(input: String): Int {
        val (grid, seen, parts) = parse(input)
        val (width, height) = grid.shape
        var result = 0

        for (y in 0..<height) {
            for (x in 0..<width) {
                val position = Point(x, y)

                if (grid[position] == '*') {
                    var previous = 0
                    var distinct = 0
                    var subtotal = 1

                    for (direction in DIRS_8) {
                        val index = seen[position + direction]
                        if (index != 0 && index != previous) {
                            previous = index
                            distinct += 1
                            subtotal *= parts[index]
                        }
                    }

                    if (distinct == 2) {
                        result += subtotal
                    }
                }
            }
        }
        return result
    }
}
