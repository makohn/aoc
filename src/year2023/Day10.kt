package year2023

import util.core.*
import util.grid.*
import util.point.*
import kotlin.math.abs

class Day10 : Solution<Int, Int> {

    private fun determinant(a: Point, b: Point): Int = a.x * b.y - a.y * b.x

    private inline fun parse(input: String, area: (Point, Point) -> Unit = { _, _ -> }): Int {
        val grid = input.lines().toCharGrid()

        var corner = grid.positionOf('S')
        var direction = if (grid[corner] in "|7F") UP else DOWN
        val position = corner + direction
        var steps = 1

        while (true) {
            while (grid[position] == '-' || grid[position] == '|') {
                position += direction
                steps++
            }

            val cell = grid[position]
            direction = when {
                (cell == '7' && direction == UP) || (cell == 'J' && direction == DOWN) -> LEFT
                (cell == 'F' && direction == UP) || (cell == 'L' && direction == DOWN) -> RIGHT
                cell == '7' || cell == 'F' -> DOWN
                cell == 'J' || cell == 'L' -> UP
                else -> {
                    area(corner, position)
                    break
                }
            }

            area(corner, position)
            corner = Point(position)
            position += direction
            steps++
        }

        return steps
    }

    override fun part1(input: String): Int {
        val steps = parse(input)
        return steps / 2
    }

    override fun part2(input: String): Int {
        var area = 0
        val steps = parse(input) { corner, position ->
            area += determinant(corner, position)
        }
        return abs(area) / 2 - steps / 2 + 1
    }
}
