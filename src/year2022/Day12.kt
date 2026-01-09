package year2022

import util.core.*
import util.grid.*
import util.point.*

class Day12 : Solution<Int, Int>(year = 2022, day = 12) {

    override fun part1(input: String): Int {
        val grid = input.lines().toCharGrid()
        val start = grid.positionOf('E')
        return bfs(grid, start, 'S')
    }

    override fun part2(input: String): Int {
        val grid = input.lines().toCharGrid()
        val start = grid.positionOf('E')
        return bfs(grid, start, 'a')
    }

    private val Char.height: Int
        get() = when (this) {
            'S' -> 'a'
            'E' -> 'z'
            else -> this
        }.code

    private fun bfs(grid: CharGrid, start: Point, end: Char): Int {
        val queue = ArrayList<Pair<Point, Int>>()
        val visited = BooleanGrid(grid.shape)
        queue.add(start to 0)

        while (queue.isNotEmpty()) {
            val (point, cost) = queue.removeFirst()
            if (grid[point] == end) return cost

            for (direction in ORTHOGONAL) {
                val neighbor = point + direction
                if (neighbor in grid && !visited[neighbor] && grid[point].height - grid[neighbor].height <= 1) {
                    queue.add(neighbor to cost + 1)
                    visited[neighbor] = true
                }
            }
        }

        error("Unreachable")
    }
}

fun main() = Day12().run {
    println(part1(input))
    println(part2(input))
}