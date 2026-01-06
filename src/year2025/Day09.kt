package year2025

import util.core.*
import kotlin.math.abs

class Day09 : Solution<Long, Long>(year = 2025, day = 9) {

    override fun part1(input: String): Long {
        val tiles = input.lines().map { it.split(",") }.map { (a,b) -> a.toInt() to b.toInt() }
        val areas = ArrayList<Long>()
        for (t in tiles.indices) for (u in t+1..tiles.lastIndex) {
            areas.add(abs((1L + tiles[t].first - tiles[u].first) * (1L + tiles[t].second - tiles[u].second)))
        }
        return areas.max()
    }

    override fun part2(input: String): Long {
        data class Point(val x: Int, val y: Int) {
            override fun toString() = "[$x, $y]"
        }
        val points = input.lines().map { it.split(",") }.map { (a,b) -> Point(a.toInt(), b.toInt()) }

        val xs = points.map { it.x }.distinct().sorted().toIntArray()
        val ys = points.map { it.y }.distinct().sorted().toIntArray()
        val xis = xs.withIndex().associate { (i, x) -> x to i }
        val yis = ys.withIndex().associate { (i, y) -> y to i }

        val grid = Array(xs.size) { IntArray(ys.size) { 0 } }
        val (n, m) = grid.size to grid[0].size

        for ((p1, p2) in points.zip(points.drop(1) + points.take(1))) {
            val (a1, b1) = xis[p1.x]!! to yis[p1.y]!!
            val (a2, b2) = xis[p2.x]!! to yis[p2.y]!!
            val (x1, x2) = if (a1 < a2) a1 to a2 else a2 to a1
            val (y1, y2) = if (b1 < b2) b1 to b2 else b2 to b1
            for (x in x1..x2) for (y in y1..y2) {
                grid[x][y] = 1
            }
        }

        val outside = hashSetOf(Point(-1, 1))
        val q = ArrayList(outside)

        while (q.isNotEmpty()) {
            val (x, y) = q.removeFirst()
            for ((nx, ny) in listOf(x - 1 to y, x + 1 to y, x to y - 1, x to y + 1)) {
                if (nx < -1 || ny < -1 || nx > n || ny > m) continue
                if (nx in 0..<n && ny in 0..<m && grid[nx][ny] == 1) continue
                val p = Point(nx, ny)
                if (p in outside) continue
                outside.add(p)
                q.add(p)
            }
        }

        for (x in 0..<n) for (y in 0..<m) {
            if (Point(x, y) !in outside) {
                grid[x][y] = 1
            }
        }

        var max = 0L
        for (i in points.indices) for (j in i+1..points.lastIndex) {
            val (x1, y1) = points[i]
            val (x2, y2) = points[j]
            val (xi1, yi1) = xis[x1]!! to yis[y1]!!
            val (xi2, yi2) = xis[x2]!! to yis[y2]!!
            val xRange = if (xi1 < xi2) xi1..xi2 else xi2..xi1
            val yRange = if (yi1 < yi2) yi1..yi2 else yi2..yi1
            var allInside = true
            checkInside@for (x in xRange) for (y in yRange) {
                if (grid[x][y] != 1) {
                    allInside = false
                    break@checkInside
                }
            }
            if (allInside) {
                val area = (1L + abs(x2 - x1)) * (1L + abs(y2 - y1))
                if (area > max) {
                    max = area
                }
            }
        }

        return max
    }
}

fun main() = Day09().run {
    println(part1(input))
    println(part2(input))
}