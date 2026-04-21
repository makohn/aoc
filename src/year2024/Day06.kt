package year2024

import util.core.*
import util.grid.*
import util.point.*

class Day06 : Solution<Int, Int> {

    override fun part1(input: String): Int {
        val grid = input.lines().toCharGrid()
        val (width, height) = grid.shape
        val p = grid.positionOf('^')
        var d = Point(0, -1)
        var c = 1
        while (true) {
            val (j, i) = p
            val (dj, di) = p + d
            if (grid[i][j] == '.') c++
            if (di in 0..<height && dj in 0..<width) {
                if (grid[di][dj] == '#') d = Point(-d.y, d.x)
                grid[i][j] = 'X'
                p += d
            } else {
                break
            }
        }
        return c
    }

    override fun part2(input: String): Int {
        val startGrid = input.lines().toCharGrid()
        val (width, height) = startGrid.shape
        val (sj, si) = startGrid.positionOf('^')
        val sd = 0
        val dirs = arrayOf(Point(0, -1), Point(1, 0), Point(0, 1), Point(-1, 0))
        val seen = Array(4) { Array(height) { BooleanArray(width) } }

        fun solve(grid: CharGrid, i: Int, j: Int, dir: Int, first: Boolean): Int {
            seen.forEach { arr -> arr.forEach { it.fill(false) } }
            var ii = i
            var jj = j
            var d = dir
            var c = 0
            while (true) {
                val di = ii + dirs[d].y
                val dj = jj + dirs[d].x
                if (seen[d][ii][jj]) return 1 else seen[d][ii][jj] = true
                if (di in 0..<height && dj in 0..<width) {
                    if (grid[di][dj] == '#') {
                        d = (d + 1) % 4
                    } else {
                        if (first && grid[di][dj] == '.') {
                            grid[di][dj] = '#'
                            c += solve(grid, ii, jj, d, false)
                            grid[di][dj] = 'X'
                        }
                        ii = di
                        jj = dj
                    }
                } else {
                    return c
                }
            }
        }
        return solve(startGrid, si, sj, sd, true)
    }
}
