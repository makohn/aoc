package year2024

import util.core.*
import util.grid.*
import util.point.*
import kotlin.math.abs

class Day20(private val picos: Int = 100) : Solution<Int, Int>(year = 2024, day = 20) {

    private fun parse(input: String): IntGrid {
        val grid = input.lines().toCharGrid()
        val pos = grid.positionOf('S')
        val end = grid.positionOf('E')

        val (m, n) = grid.shape
        val time = IntGrid(m, n) { Int.MAX_VALUE }
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

    private fun check(time: IntGrid, first: Point, delta: Point): Int {
        val second = first + delta
        return if ((second in time)
            && time[second] != Int.MAX_VALUE
            && abs(time[first] - time[second]) - first.distanceTo(second) >= picos
        ) 1 else 0
    }

    override fun part1(input: String): Int {
        val time = parse(input)
        var cheats = 0
        val (m, n) = time.shape
        for (i in 1..<m - 1) for (j in 1..<n - 1) {
            val point = Point(i, j)
            if (time[point] != Int.MAX_VALUE) {
                cheats += check(time, point, Point(2, 0))
                cheats += check(time, point, Point(0, 2))
            }
        }
        return cheats
    }

    override fun part2(input: String): Int {
        return 0
    }
}

fun main() = Day20().run {
    println(part1(input))
}