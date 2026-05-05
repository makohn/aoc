package year2023

import util.core.*
import util.grid.*
import util.point.*

class Day23 : Solution<Int, Int> {

    private data class Node(val point: Point, val weight: Int)

    companion object {
        private val DIRECTIONS = mapOf(
            '>' to arrayOf(RIGHT),
            'v' to arrayOf(DOWN),
            '<' to arrayOf(LEFT),
            '^' to arrayOf(UP),
            '.' to RDLU,
        )
    }

    override fun part1(input: String): Int {
        val grid = input.lines().toCharGrid()
        val (width, height) = grid.shape
        val start = Point(1, 0)
        val end = Point(width - 2, height - 1)
        val visited = BooleanGrid(grid.shape)

        fun dfs(position: Point): Int {
            if (position == end) return 0
            var max = 0
            visited[position] = true
            for (next in DIRECTIONS[grid[position]]!!.map { position + it }) {
                if (next in grid && grid[next] != '#' && !visited[next]) {
                    val pathLength = dfs(next)
                    if (pathLength >= 0) max = maxOf(max, pathLength + 1)
                }
            }
            visited[position] = false
            return max
        }

        return dfs(start)
    }

    override fun part2(input: String): Int {
        val grid = input.lines().toCharGrid()
        val shape = grid.shape
        val (width, height) = shape
        val start = Point(1, 0)
        val end = Point(width - 2, height - 1)

        val pois = arrayListOf(start, end)

        // Find junctions
        for (y in 0..<height) {
            for (x in 0..<width) {
                if (grid[y][x] != '#') {
                    val position = Point(x, y)
                    val neighbors = DIRECTIONS['.']!!.map { position + it }.filter { it in grid && grid[it] != '#' }
                    if (neighbors.size >= 3) {
                        pois.add(position)
                    }
                }
            }
        }
        val graph = pois.associateWith { HashSet<Node>() }

        for (poi in pois) {
            val visited = BooleanGrid(shape)
            val stack = arrayListOf(Node(poi, 0))
            visited[poi] = true

            while (stack.isNotEmpty()) {
                val node = stack.removeLast()
                val (position, weight) = node
                if (weight != 0 && position in pois) {
                    graph[poi]!!.add(node)
                } else {
                    val neighbors = DIRECTIONS['.']!!
                        .map { direction -> position + direction }
                        .filter { it in grid && grid[it] != '#' && !visited[it] }
                    for (next in neighbors) {
                        stack.add(Node(next, weight + 1))
                        visited[next] = true
                    }
                }
            }
        }

        val visited = BooleanGrid(shape)

        fun dfs(position: Point): Int {
            if (position == end) return 0
            var max = -1
            visited[position] = true
            val neighbors = graph[position]!!
            for ((next, weight) in neighbors) {
                if (!visited[next]) {
                    val pathLength = dfs(next)
                    if (pathLength >= 0) max = maxOf(max, pathLength + weight)
                }
            }
            visited[position] = false
            return max
        }
        return dfs(start)
    }
}
