package year2021

import util.Solution

class Day09 : Solution<Int, Int>(year = 2021, day = 9) {

    override fun part1(input: String): Int {
        val grid = input.lines().map { it.map { c -> c.digitToInt() }.toIntArray() }.toTypedArray()
        var sum = 0
        for (i in grid.indices) for (j in grid[0].indices) {
            val up = if (i > 0) grid[i-1][j] else Int.MAX_VALUE
            val down = if (i < grid.lastIndex) grid[i+1][j] else Int.MAX_VALUE
            val left = if (j > 0) grid[i][j-1] else Int.MAX_VALUE
            val right = if (j < grid[0].lastIndex) grid[i][j+1] else Int.MAX_VALUE
            if (grid[i][j] < minOf(up, down, left, right)) sum += grid[i][j] + 1
        }
        return sum
    }

    override fun part2(input: String): Int {
        val grid = input.lines().map { it.map { c -> c.digitToInt() }.toIntArray() }.toTypedArray()
        val sum = mutableListOf<Int>()
        for (i in grid.indices) for (j in grid[0].indices) {
            val up = if (i > 0) grid[i-1][j] else Int.MAX_VALUE
            val down = if (i < grid.lastIndex) grid[i+1][j] else Int.MAX_VALUE
            val left = if (j > 0) grid[i][j-1] else Int.MAX_VALUE
            val right = if (j < grid[0].lastIndex) grid[i][j+1] else Int.MAX_VALUE
            if (grid[i][j] < minOf(up, down, left, right)) sum += getBasin(i, j, grid).size
        }
        return sum.sorted().takeLast(3).reduce { a, b -> a * b }
    }

    private fun getBasin(i: Int, j: Int, grid: Array<IntArray>,
                         visited: MutableSet<Pair<Int, Int>> = mutableSetOf())
            : MutableSet<Pair<Int, Int>> {
        if (i !in (0 .. grid.lastIndex) ||
            j !in (0 .. grid[0].lastIndex) ||
            grid[i][j] == 9 || (i to j) in visited) {
            return visited
        }
        visited.add((i to j))
        getBasin(i-1, j, grid, visited)
        getBasin(i+1, j, grid, visited)
        getBasin(i, j-1, grid, visited)
        getBasin(i, j+1, grid, visited)
        return visited
    }
}

fun main() = Day09().run {
    println(part1(input))
    println(part2(input))
}