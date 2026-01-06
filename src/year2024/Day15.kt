package year2024

import util.Solution
import util.grid.positionOf
import util.grid.shape
import util.grid.toCharGrid
import util.point.DOWN
import util.point.LEFT
import util.point.RIGHT
import util.point.UP

class Day15 : Solution<Int, Int>(year = 2024, day = 15) {

    override fun part1(input: String): Int {
        val (gridStr, moves) = input.split("\n\n")
        val grid = gridStr.lines().toCharGrid()
        val pos = grid.positionOf('@')
        grid[pos.i][pos.j] = '.'

        for (move in moves) {
            val dir = when (move) {
                '^' -> UP
                '>' -> RIGHT
                'v' -> DOWN
                '<' -> LEFT
                else -> continue
            }
            var k = 1
            while (grid[pos.i + dir.i * k][pos.j + dir.j * k] == 'O') k++
            if (grid[pos.i + dir.i * k][pos.j + dir.j * k] == '.') {
                if (k > 1) {
                    grid[pos.i + dir.i * k][pos.j + dir.j * k] = 'O'
                    grid[pos.i + dir.i][pos.j + dir.j] = '.'
                }
                pos += dir
            }
        }
        var res = 0
        val (m, n) = grid.shape
        for (i in 0..<m) for (j in 0..<n) if (grid[i][j] == 'O') {
            res += 100 * i + j
        }
        return res
    }

    override fun part2(input: String): Int {
        return 0
    }
}

fun main() = Day15().run {
    println(part1(input))
}