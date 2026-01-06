package year2023

import util.core.*
import util.grid.*

class Day21(
    val s1: Int = 64,
    val s2: Int = 26501365
) : Solution<Int, Long>(year = 2023, day = 21) {

    val directions = arrayOf(Direction.North, Direction.East, Direction.South, Direction.West)

    fun countReachableCells(map: CharGrid, vararg steps: Int, adjacentTo: (CharPoint) -> List<CharPoint>) = sequence {
        val charCells = map.flatMapIndexed { i, row -> row.mapIndexed { j, char -> CharPoint(i, j, char) } }
        val startCell = charCells.first { it.data == 'S' }

        val newCells = mutableSetOf<CharPoint>()
        newCells.add(startCell)

        for (x in generateSequence(1) { it + 1 }) {
            val cells = newCells.flatMap {
                adjacentTo(it).filter { it.data != '#' }
            }
            newCells.clear()
            newCells.addAll(cells)
            if (x in steps) yield(newCells.size)
        }
    }

    override fun part1(input: String): Int {
        val map = input.lines().toCharGrid()
        return countReachableCells(map, s1) {
            map.neighborsOf(it, *directions)
        }.take(1).first()
    }

    override fun part2(input: String): Long {
        val map = input.lines().toCharGrid()
        val (n, _) = map.shape
        val x0 = s2 % n
        val (a, b, c) = countReachableCells(map, x0, x0 + 1 * n, x0 + 2 * n) {
            map.neighborsOfUnbound(it, *directions)
        }.take(3).map { it.toLong() }.toList()

        fun f(x: Long) = a + (b - a) * x + (x * (x - 1) / 2L) * ((c - b) - (b - a))

        val x = ((this.s2 - x0) / n).toLong()
        return f(x)
    }
}

fun main() = Day21().run {
    println(part1(input))
    println(part2(input))
}