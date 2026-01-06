package year2021

import util.core.*
import kotlin.math.abs

class Day17 : Solution<Int, Int>(year = 2021, day = 17) {

    override fun part1(input: String): Int {
        val area = parse(input)
        val (maxY, _) = simulate(area)
        return maxY
    }

    override fun part2(input: String): Int {
        val area = parse(input)
        val (_, count) = simulate(area)
        return count
    }

    private fun parse(input: String): Int4 {
        val (minX, maxX, minY, maxY) = "(-?\\d+)".toRegex().findAll(input).map { it.value.toInt() }.toList()
        return Int4(minX, maxX, minY, maxY)
    }

    private fun simulate(area: Int4): Pair<Int, Int> {
        var yMax = 0
        var count = 0
        val (_, maxX, minY, maxY) = area
        val yRange = maxOf(abs(minY), abs(maxY))
        for (vx in 0 .. maxX) {
            for (vy in -yRange .. yRange) {
                val res = shot(vx, vy, area)
                if (res >= 0) count++
                if (res > yMax) yMax = res
            }
        }
        return yMax to count
    }

    private fun shot(vx: Int, vy: Int, area: Int4): Int {
        val (minX, maxX, minY, maxY) = area
        var state = Int4(0, 0, vx, vy)
        var yMax = 0
        while (true) {
            state = step(state)
            val (x, y, svx, _) = state
            if (y > yMax) yMax = y
            if (x > maxX) return -1
            if (svx == 0 && x !in (minX .. maxX)) return -1
            if (svx == 0 && y < minY) return -1
            if ((x in (minX .. maxX)) && y in (minY .. maxY)) return yMax
        }
    }

    private fun step(state: Int4): Int4 {
        var (x, y, vx, vy) = state
        x += vx
        y += vy
        if (vx > 0) vx-- else if (vx < 0) vx++
        vy--
        return Int4(x, y, vx, vy)
    }
}

fun main() = Day17().run {
    println(part1(input))
    println(part2(input))
}