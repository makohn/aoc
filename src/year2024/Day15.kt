package year2024

import util.core.*
import util.grid.*
import util.point.*

class Day15 : Solution<Int, Int>(year = 2024, day = 15) {

    private fun push(grid: CharGrid, start: Point, dir: Point) {
        var k = 1
        val pos = start + dir
        while (grid[pos] != '.' && grid[pos] != '#') {
            pos += dir
            k++
        }
        if (grid[pos] == '.') {
            var prev = '.'
            val pos = start + dir
            repeat(k) {
                grid[pos] = prev.also { prev = grid[pos] }
                pos += dir
            }
            start += dir
        }
    }

    private fun push(grid: CharGrid, start: Point, dir: Point, todo: ArrayList<Point>) {
        if (grid[start + dir] == '.') {
            start += dir
            return
        }
        todo.clear()
        todo.add(ORIGIN)
        todo.add(start)
        var index = 1

        while (index < todo.size) {
            val next = todo[index] + dir
            index++

            val left: Point
            val right: Point
            when (grid[next]) {
                '[' -> {
                    left = next
                    right = next + RIGHT
                }
                ']' -> {
                    left = next + LEFT
                    right = next
                }
                '#' -> return
                else -> continue
            }

            if (left != todo[todo.size - 2]) {
                todo.add(left)
                todo.add(right)
            }
        }
        for (pos in todo.drop(2).reversed()) {
            grid[pos + dir] = grid[pos]
            grid[pos] = '.'
        }
        start += dir
    }

    private fun gpsSum(grid: CharGrid, box: Char): Int {
        var res = 0
        val (m, n) = grid.shape
        for (i in 0..<m) for (j in 0..<n) if (grid[i][j] == box) {
            res += 100 * i + j
        }
        return res
    }

    private fun List<String>.stretchMap(transform: (Char) -> String) = this.map { it.map(transform).joinToString("") }

    override fun part1(input: String): Int {
        val (gridStr, moves) = input.split("\n\n")
        val grid = gridStr.lines().toCharGrid()
        val start = grid.positionOf('@')
        grid[start] = '.'

        for (move in moves) {
            val dir = when (move) {
                '>' -> RIGHT
                'v' -> DOWN
                '<' -> LEFT
                '^' -> UP
                else -> continue
            }
            push(grid, start, dir)
        }
        return gpsSum(grid, 'O')
    }

    override fun part2(input: String): Int {
        val (gridStr, moves) = input.split("\n\n")
        val grid = gridStr.lines().stretchMap {
            when (it) {
                '#' -> "##"
                'O' -> "[]"
                '.' -> ".."
                '@' -> "@."
                else -> error(it)
            }
        }.toCharGrid()
        val start = grid.positionOf('@')
        grid[start] = '.'
        val todo = ArrayList<Point>()

        for (move in moves) {
            when (move) {
                '>' -> push(grid, start, RIGHT)
                'v' -> push(grid, start, DOWN, todo)
                '<' -> push(grid, start, LEFT)
                '^' -> push(grid, start, UP, todo)
                else -> continue
            }
        }
        return gpsSum(grid, '[')
    }
}

fun main() = Day15().run {
    println(part1(input))
    println(part2(input))
}