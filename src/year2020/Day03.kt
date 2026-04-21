package year2020

import util.core.*
import util.grid.*
import util.point.*

class Day03 : Solution<Int, Long> {

    private fun countTrees(grid: CharGrid, slope: Point): Int {
        val (width, height) = grid.shape
        var position = Point(0, 0)
        var count = 0
        while (position.y != height - 1) {
            position = Point((position.x + slope.x) % width, (position.y + slope.y) % height)
            if (grid[position] == '#') count++
        }
        return count
    }

    override fun part1(input: String): Int {
        val grid = input.lines().toCharGrid()
        return countTrees(grid, Point(3, 1))
    }

    override fun part2(input: String): Long {
        val grid = input.lines().toCharGrid()
        val slopes = arrayOf(
            Point(1, 1),
            Point(3, 1),
            Point(5, 1),
            Point(7, 1),
            Point(1, 2),
        )
        return slopes.map { slope -> countTrees(grid, slope) }.fold(1L) { a, b -> a * b }
    }
}
