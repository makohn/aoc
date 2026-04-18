package year2024

import util.core.*
import util.grid.*
import util.point.*
import kotlin.math.abs

class Day20(
    private val picos1: Int = 100,
    private val picos2: Int = 100,
) : Solution<Int, Int>(year = 2024, day = 20) {

    private fun parse(input: String): IntGrid {
        val grid = input.lines().toCharGrid()
        val pos = grid.positionOf('S')
        val end = grid.positionOf('E')

        val time = IntGrid(grid.shape) { Int.MAX_VALUE }
        var elapsed = 0

        var dir = RDLU.first { grid[pos + it] != '#' }

        while (pos != end) {
            time[pos] = elapsed++
            dir = arrayOf(dir, dir.clockwise(), dir.counterClockwise()).first { grid[pos + it] != '#' }
            pos += dir
        }

        time[end] = elapsed
        return time
    }

    private fun check(time: IntGrid, first: Point, delta: Point, picos: Int): Int {
        val second = first + delta
        return if ((second in time) &&
            time[second] != Int.MAX_VALUE &&
            abs(time[first] - time[second]) - first.distanceTo(second) >= picos
        ) {
            1
        } else {
            0
        }
    }

    override fun part1(input: String): Int {
        val time = parse(input)
        var cheats = 0
        val (m, n) = time.shape
        for (i in 1..<m - 1) {
            for (j in 1..<n - 1) {
                val point = Point(i, j)
                if (time[point] != Int.MAX_VALUE) {
                    cheats += check(time, point, Point(2, 0), picos1)
                    cheats += check(time, point, Point(0, 2), picos1)
                }
            }
        }
        return cheats
    }

    override fun part2(input: String): Int {
        val time = parse(input)
        val points = ArrayList<Point>(10000)
        val (m, n) = time.shape
        for (i in 1..<m - 1) {
            for (j in 1..<n - 1) {
                val point = Point(i, j)
                if (time[point] != Int.MAX_VALUE) {
                    points.add(point)
                }
            }
        }
        return points.parallelStream().map { point ->
            var cheats = 0
            for (i in 2..20) cheats += check(time, point, Point(i, 0), picos2)
            for (j in 1..20) {
                for (i in (j - 20)..(20 - j)) {
                    cheats += check(time, point, Point(i, j), picos2)
                }
            }
            cheats
        }.toList().sum()
    }
}
