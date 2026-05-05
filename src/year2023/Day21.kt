package year2023

import util.core.*
import util.grid.*
import util.point.*

class Day21(
    val part1StepLimit: Int = 64,
    val part2StepLimit: Int = 26501365,
) : Solution<Int, Long> {

    private abstract class Bfs(
        grid: CharGrid,
        private vararg val limit: Int,
    ) : Iterator<Int> {

        private val start = grid.positionOf('S')
        private var steps = 0
        private var current = hashSetOf(start)

        override fun hasNext(): Boolean = true

        override fun next(): Int {
            while (true) {
                val next = HashSet<Point>()
                for (position in current) {
                    findNext(next, position)
                }
                current = next
                steps++
                if (steps in limit) {
                    return current.size
                }
            }
        }

        protected abstract fun findNext(next: HashSet<Point>, position: Point)
    }

    override fun part1(input: String): Int {
        val grid = input.lines().toCharGrid()
        return object : Bfs(grid, part1StepLimit) {
            override fun findNext(next: HashSet<Point>, position: Point) {
                for (direction in RDLU) {
                    val nextPosition = position + direction
                    if (nextPosition in grid && grid[nextPosition] != '#') {
                        next.add(nextPosition)
                    }
                }
            }
        }.next()
    }

    override fun part2(input: String): Long {
        val grid = input.lines().toCharGrid()
        val (width, height) = grid.shape
        val limit = part2StepLimit % height
        val bfs = object : Bfs(grid, limit, limit + height, limit + 2 * height) {
            override fun findNext(next: HashSet<Point>, position: Point) {
                for (direction in RDLU) {
                    val nextPosition = position + direction
                    if (grid[nextPosition.y.mod(height)][nextPosition.x.mod(width)] != '#') {
                        next.add(nextPosition)
                    }
                }
            }
        }
        val a = bfs.next().toLong()
        val b = bfs.next().toLong()
        val c = bfs.next().toLong()

        val x = ((part2StepLimit - limit) / height).toLong()

        return a + (b - a) * x + (x * (x - 1) / 2) * ((c - b) - (b - a))
    }
}
