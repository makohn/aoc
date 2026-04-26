package year2020

import util.core.*
import util.grid.*
import util.point.*

class Day11 : Solution<Int, Int> {

    companion object {
        private const val SEAT = 'L'
    }

    private class Seat(
        val position: Point,
        var size: Int,
        val neighbors: Array<Point>,
    ) {
        fun push(point: Point) {
            neighbors[size++] = point
        }
    }

    private inline fun solve(input: String, n: Int, proceed: (CharGrid, Seat, Point, Point) -> Unit): Int {
        val grid = input.lines().toCharGrid()
        val (width, height) = grid.shape
        val seats = buildList {
            for (y in 0..<height) {
                for (x in 0..<width) {
                    val position = Point(x, y)
                    if (grid[position] != SEAT) {
                        continue
                    }

                    val seat = Seat(position, 0, Array(8) { ORIGIN })
                    for (direction in DIRS_8) {
                        val next = position + direction
                        proceed(grid, seat, next, direction)
                    }
                    add(seat)
                }
            }
        }

        var previous = IntGrid(grid.shape) { 0 }
        var next = IntGrid(grid.shape) { 0 }
        while (true) {
            for (seat in seats) {
                val total = seat.neighbors.take(seat.size).sumOf { previous[it] }
                next[seat.position] = if (previous[seat.position] == 0) {
                    (total == 0).toInt()
                } else {
                    (total < n).toInt()
                }
            }
            previous = next.also { next = previous }
            if (previous.contentDeepEquals(next)) {
                return next.sumOf { it.sum() }
            }
        }
    }

    override fun part1(input: String): Int = solve(input, 4) { grid, seat, next, _ ->
        if (next in grid && grid[next] == SEAT) {
            seat.push(next)
        }
    }

    override fun part2(input: String): Int = solve(input, 5) { grid, seat, next, direction ->
        while (next in grid) {
            if (grid[next] == SEAT) {
                seat.push(next)
                break
            }
            next += direction
        }
    }
}
