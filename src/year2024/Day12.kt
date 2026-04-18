package year2024

import util.core.*
import util.grid.*
import util.point.*

class Day12 : Solution<Int, Int>(year = 2024, day = 12) {

    private val di = intArrayOf(-1, 0, 1, 0)
    private val dj = intArrayOf(0, 1, 0, -1)

    override fun part1(input: String): Int {
        val grid = input.lines().map { it.map { c -> c.code }.toIntArray() }.toTypedArray()
        val (width, height) = grid.shape

        fun solve(i: Int, j: Int, c: Int, r: Int): Point {
            grid[i][j] = r
            var a = 1
            var p = 0
            for (x in 0..3) {
                val ii = i + di[x]
                val jj = j + dj[x]
                val inGrid = ii in 0..<height && jj in 0..<width
                if (inGrid && grid[ii][jj] == c) {
                    val (np, na) = solve(ii, jj, c, r)
                    a += na
                    p += np
                }
                if (!inGrid || (grid[ii][jj] != c && grid[ii][jj] != r)) p++
            }
            return Point(p, a)
        }

        val r = -1
        var sum = 0
        var x = r
        for (i in 0..<height) {
            for (j in 0..<width) {
                val c = grid[i][j]
                if (c <= r) continue
                val (p, a) = solve(i, j, c, x)
                x--
                sum += a * p
            }
        }
        return sum
    }

    override fun part2(input: String): Int {
        val grid = input.lines().map { it.map { c -> c.code }.toIntArray() }.toTypedArray()
        val (width, height) = grid.shape

        fun check(i: Int, j: Int, c: Int, r: Int) = (i in 0..<height && j in 0..<width && (grid[i][j] == c || grid[i][j] == r))
        fun check(i: Int, j: Int, c: Int) = (i in 0..<height && j in 0..<width && (grid[i][j] == c))

        fun solve(i: Int, j: Int, c: Int, r: Int): Point {
            grid[i][j] = r
            var a = 1
            var sides = 0
            for (x in 0..3) {
                val ii = i + di[x]
                val jj = j + dj[x]
                if (check(ii, jj, c)) {
                    val (np, na) = solve(ii, jj, c, r)
                    a += na
                    sides += np
                }
                val inGrid = ii in 0..<height && jj in 0..<width
                if (!inGrid || (grid[ii][jj] != c && grid[ii][jj] != r)) {
                    val rx = (x + 1).mod(4)
                    val lx = (x - 1).mod(4)
                    if (!(check(i + di[rx], j + dj[rx], c, r)) || check(ii + di[rx], jj + dj[rx], c, r)) sides++
                    if (!(check(i + di[lx], j + dj[lx], c, r)) || check(ii + di[lx], jj + dj[lx], c, r)) sides++
                }
            }
            return Point(sides, a)
        }

        val r = -1
        var sum = 0
        var x = r
        for (i in 0..<height) {
            for (j in 0..<width) {
                val c = grid[i][j]
                if (c <= r) continue
                val (sides, a) = solve(i, j, c, x)
                x--
                sum += a * (sides / 2)
            }
        }
        return sum
    }
}
