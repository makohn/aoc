package year2021

import util.algorithm.*
import util.core.*
import util.grid.*
import util.point.*

class Day15 : Solution<Int, Int> {

    override fun part1(input: String): Int {
        val board = parse(input)
        val (width, height) = board.shape
        return solve(board, width, height)
    }

    override fun part2(input: String): Int {
        val board = parse(input)
        val (width, height) = board.shape
        val map = Array(height * 5) { IntArray(width * 5) }
        for (i in board.indices) {
            for (j in board[i].indices) {
                for (k in 0..4) {
                    val l = j + k * width
                    map[i][l] = (((board[i][j] + 1 * k - 1)) % 9) + 1
                }
            }
            for (x in 1..4) {
                map[i + x * height] = map[i].map { ((it + x * 1 - 1)) % 9 + 1 }.toIntArray()
            }
        }
        return solve(map, width * 5, height * 5)
    }

    private fun parse(input: String) = input.lines().map { it.map { c -> c.digitToInt() }.toIntArray() }.toTypedArray()

    private fun solve(board: Array<IntArray>, width: Int, height: Int): Int {
        fun adjacent(u: Point) = buildList {
            val (x, y) = u
            for ((nx, ny) in listOf(Point(x, y - 1), Point(x, y + 1), Point(x - 1, y), Point(x + 1, y))) {
                if ((0 <= ny) && (ny < width) && (0 <= nx) && (nx < height)) {
                    add(Point(nx, ny) to board[ny][nx])
                }
            }
        }

        val start = Point(0, 0)
        val end = Point(height - 1, width - 1)

        return dijkstra(start, ::adjacent)[end]!!
    }
}
