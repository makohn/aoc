package year2024

import util.point.Point
import util.Solution
import util.grid.shape
import util.grid.toIntGrid

class Day10 : Solution<Int, Int>(year = 2024, day = 10) {

    override fun part1(input: String): Int {
        val grid = input.lines().toIntGrid()
        val (n, m) = grid.shape

        val di = intArrayOf(-1, 0, 1, 0)
        val dj = intArrayOf(0, 1, 0, -1)

        fun findPath(i :Int, j: Int, found: HashSet<Point>) {
            val v = grid[i][j]
            if (v == 9) {
                found.add(Point(i, j))
                return
            }
            for (x in 0..3) {
                val ii = i + di[x]
                val jj = j + dj[x]
                if (ii in 0..<n && jj in 0..<m && grid[ii][jj] == v + 1) {
                    findPath(ii, jj, found)
                }
            }
        }

        var sum = 0
        for (i in 0..<n) for (j in 0..<m) {
            if (grid[i][j] == 0) {
                val found = HashSet<Point>()
                findPath(i, j, found)
                sum += found.size
            }
        }
        return sum
    }

    override fun part2(input: String): Int {
        val grid = input.lines().toIntGrid()
        val (n, m) = grid.shape

        val di = intArrayOf(-1, 0, 1, 0)
        val dj = intArrayOf(0, 1, 0, -1)

        fun findPath(i :Int, j: Int): Int {
            val v = grid[i][j]
            if (v == 9) return 1
            var score = 0
            for (x in 0..3) {
                val ii = i + di[x]
                val jj = j + dj[x]
                if (ii in 0..<n && jj in 0..<m && grid[ii][jj] == v + 1) {
                    score += findPath(ii, jj)
                }
            }
            return score
        }

        var sum = 0
        for (i in 0..<n) for (j in 0..<m) {
            if (grid[i][j] == 0) {
                val score = findPath(i, j)
                sum += score
            }
        }
        return sum
    }
}

fun main() = Day10().run {
    println(part1(input))
    println(part2(input))
}