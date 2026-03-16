package year2024

import util.core.*
import util.grid.*
import util.parse.extractInts
import util.point.*
import java.util.*

class Day18(
    private val size: Int = 71,
    private val bytes: Int = 1024
) : Solution<Int, String>(year = 2024, day = 18) {

    private data class Item(val t: Int, val p: Point): Comparable<Item> {
        override fun compareTo(other: Item) = other.t.compareTo(this.t)
    }

    private fun parse(input: String): IntGrid {
        val grid = IntGrid(size, size) { Int.MAX_VALUE }
        for ((i, element) in input.extractInts().chunked(2).withIndex()) {
            val (x, y) = element
            grid[x][y] = i
        }
        return grid
    }

    override fun part1(input: String): Int {
        val grid = parse(input)
        val todo = ArrayList<Pair<Point, Int>>()
        val exit = Point(size - 1, size - 1)

        grid[ORIGIN] = 0
        todo.add(ORIGIN to 0)

        while (todo.isNotEmpty()) {
            val (pos, cost) = todo.removeFirst()
            if (pos == exit) return cost

            for (next in RDLU.map { pos + it }) {
                if (next in grid && grid[next] >= bytes) {
                    grid[next] = 0
                    todo.add(next to cost + 1)
                }
            }
        }
        return -1
    }

    override fun part2(input: String): String {
        val grid = parse(input)
        val todo = ArrayList<Point>()
        val heap = PriorityQueue<Item>()
        val exit = Point(size - 1, size - 1)
        var time = Int.MAX_VALUE

        grid[ORIGIN] = 0
        todo.add(ORIGIN)

        while (true) {
            while (todo.isNotEmpty()) {
                val pos = todo.removeFirst()
                if (pos == exit) {
                    val (i, j) = grid.positionOf(time)
                    return "$i,$j"
                }
                for (next in RDLU.map { pos + it }) {
                    if (next in grid) {
                        if (time < grid[next] || grid[next] == Int.MAX_VALUE) {
                            grid[next] = 0
                            todo.add(next)
                        } else {
                            heap.add(Item(grid[next], next))
                        }
                    }
                }
            }
            val (nextTime, nextPos) = heap.remove()
            time = nextTime
            todo.add(nextPos)
        }
    }
}