package year2023

import util.Solution
import util.grid.*
import kotlin.math.max

class Day23 : Solution<Int, Int>(year = 2023, day = 23) {

    val directions = mapOf(
        '>' to arrayOf(Direction.East),
        '<' to arrayOf(Direction.West),
        'v' to arrayOf(Direction.South),
        '^' to arrayOf(Direction.North),
        '.' to arrayOf(Direction.North, Direction.East, Direction.South, Direction.West)
    )

    override fun part1(input: String): Int {
        val map = input.lines().toCharGrid()
        val (n, _) = map.shape
        val startCell = map[0].mapIndexed { i, d -> CharPoint(0, i, d) }.first { it.data != '#' }
        val endCell = map[n-1].mapIndexed { i, d -> CharPoint(n-1, i, d) }.last { it.data != '#' }
        val visited = HashSet<CharPoint>()

        fun dfs(node: CharPoint): Int {
            if (node == endCell) return 0
            var ans = 0
            visited += node
            val neighbours = map
                .neighborsOf(node, *directions[node.data]!!)
                .filter { it.data != '#' }

            for (otherNode in neighbours) {
                if (otherNode !in visited) {
                    val pathLength = dfs(otherNode)
                    if (pathLength >= 0) ans = max(ans, pathLength + 1)
                }
            }
            visited -= node

            return ans
        }

        return dfs(startCell)
    }

    override fun part2(input: String): Int {
        val map = input.lines().toCharGrid()
        val (n, _) = map.shape
        val charCells = map.dataPoints()
        val startCell = map[0].mapIndexed { i, d -> CharPoint(0, i, d) }.first { it.data != '#' }
        val endCell = map[n-1].mapIndexed { i, d -> CharPoint(n-1, i, d) }.last { it.data != '#' }

        val forks = charCells
            .filter { it.data != '#' }
            .map { it to map.neighborsOf(it, *directions['.']!!) }
            .filter { (_, neighbours) -> neighbours.count { it.data != '#' } >= 3 }
            .map { (fork, _) -> fork }
            .toTypedArray()

        val nodes = listOf(startCell, endCell, *forks)

        data class WeightedNode(val cell: CharPoint, val weight: Int)

        val graph = HashMap<CharPoint, HashSet<WeightedNode>>()
        for (node in nodes) {
            val stack = ArrayDeque<WeightedNode>()
            val visited = HashSet<CharPoint>()
            val current = WeightedNode(node, 0)
            stack += current
            visited += current.cell

            while (stack.isNotEmpty()) {
                val otherNode = stack.removeLast()
                if (otherNode.weight != 0 && otherNode.cell in nodes) {
                    graph.computeIfAbsent(node) { HashSet() }
                    graph[node]!! += otherNode
                } else {
                    for (neighbour in map
                        .neighborsOf(otherNode.cell, *directions['.']!!)
                        .filter { it.data != '#' && it !in visited }) {
                        stack += WeightedNode(neighbour, otherNode.weight + 1)
                        visited += neighbour
                    }
                }
            }
        }

        val visited = HashSet<CharPoint>()

        fun dfs(node: CharPoint): Int {
            if (node == endCell) return 0
            var ans = -1
            visited += node
            val neighbours = graph[node]!!

            for (otherNode in neighbours) {
                if (otherNode.cell !in visited) {
                    val pathLength = dfs(otherNode.cell)
                    if (pathLength >= 0) ans = max(ans, pathLength + otherNode.weight)
                }
            }
            visited -= node

            return ans
        }

        return dfs(startCell)
    }
}

fun main() = Day23().run {
    println(part1(input))
    println(part2(input))
}