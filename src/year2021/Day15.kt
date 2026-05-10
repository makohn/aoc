package year2021

import util.core.*
import util.grid.*
import util.point.*
import java.util.*

class Day15 : Solution<Int, Int> {

    private data class State(val point: Point, val cost: Int) : Comparable<State> {
        override fun compareTo(other: State): Int = this.cost.compareTo(other.cost)
    }

    private fun dijkstra(grid: IntGrid): Int {
        val (width, height) = grid.shape

        val todo = PriorityQueue<State>()
        val visited = HashMap<Point, Int>()
        val start = Point(0, 0)
        val end = Point(width - 1, height - 1)

        todo.add(State(start, 0))
        visited[start] = 0

        while (todo.isNotEmpty()) {
            val (point, cost) = todo.remove()
            if (cost <= visited[point]!!) {
                for (next in RDLU.map { it + point }) {
                    if (next in grid) {
                        val nextCost = grid[next] + cost
                        if (next !in visited || nextCost < visited[next]!!) {
                            visited[next] = nextCost
                            todo.add(State(next, nextCost))
                        }
                    }
                }
            }
        }

        return visited[end]!!
    }

    override fun part1(input: String): Int {
        val grid = input.lines().toIntGrid()
        return dijkstra(grid)
    }

    override fun part2(input: String): Int {
        val grid = input.lines().toIntGrid()
        val (width, height) = grid.shape
        val expanded = Array(height * 5) { IntArray(width * 5) }
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                for (k in 0..4) {
                    val l = j + k * width
                    expanded[i][l] = (((grid[i][j] + 1 * k - 1)) % 9) + 1
                }
            }
            for (x in 1..4) {
                expanded[i + x * height] = expanded[i].map { ((it + x * 1 - 1)) % 9 + 1 }.toIntArray()
            }
        }
        return dijkstra(expanded)
    }
}
