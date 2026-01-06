package year2024

import util.core.*
import util.grid.*
import util.point.*

class Day08 : Solution<Int, Int>(year = 2024, day = 8) {

    private fun parse(grid: CharGrid): Map<Char, ArrayList<Point>> {
        val (n, m) = grid.shape
        val antennas = HashMap<Char, ArrayList<Point>>()
        for (i in 0..<n) for (j in 0..<m) {
            val c = grid[i][j]
            if (c != '.') antennas.getOrPut(c) { ArrayList() }.add(Point(i, j))
        }
        return antennas
    }

    override fun part1(input: String): Int {
        val grid = input.lines().toCharGrid()
        val (n, m) = grid.shape
        val antennas = parse(grid)
        val antinodes = HashSet<Point>()
        for ((_, v) in antennas) for (i in 0..<v.size) for (j in i + 1..<v.size) {
            val (i1, j1) = v[i]
            val (i2, j2) = v[j]
            val a1 = 2 * i1 - i2
            val b1 = 2 * j1 - j2
            val a2 = 2 * i2 - i1
            val b2 = 2 * j2 - j1
            if (a1 in 0..<n && b1 in 0..<m) antinodes.add(Point(a1, b1))
            if (a2 in 0..<n && b2 in 0..<m) antinodes.add(Point(a2, b2))
        }
        return antinodes.size
    }

    override fun part2(input: String): Int {
        val grid = input.lines().toCharGrid()
        val (n, m) = grid.shape
        val antennas = parse(grid)
        val antinodes = HashSet<Point>()
        for ((_, v) in antennas) for (i in 0..<v.size) for (j in 0..<v.size) {
            if (i == j) continue
            val (i1, j1) = v[i]
            val (i2, j2) = v[j]
            val di = i2 - i1
            val dj = j2 - j1
            var i = i1
            var j = j1
            while (i in 0..<n && j in 0..<m) {
                antinodes.add(Point(i, j))
                i += di
                j += dj
            }
        }
        return antinodes.size
    }
}

fun main() = Day08().run {
    println(part1(input))
    println(part2(input))
}