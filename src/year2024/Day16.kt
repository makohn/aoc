package year2024

import util.core.*
import util.grid.*
import util.point.*
import java.util.PriorityQueue

class Day16 : Solution<Int, Int>(year = 2024, day = 16) {

    private data class Node(val pos: Point, val dir: Int, val cost: Int): Comparable<Node> {
        override fun compareTo(other: Node) = this.cost.compareTo(other.cost)
    }

    private fun dijkstra(grid: CharGrid, seen: Grid<IntArray>, start: Point, end: Point): Int {
        val todo = PriorityQueue<Node>()

        todo += Node(start, 0, 0)
        seen[start][0] = 0

        var min = Int.MAX_VALUE

        fun enqueue(node: Node) {
            val (pos, dir, cost) = node
            if (grid[pos] != '#' && cost < seen[pos][dir]) {
                seen[pos][dir] = cost
                todo += node
            }
        }

        while (todo.isNotEmpty()) {
            val (pos, dir, cost) = todo.remove()
            if (cost >= min) continue
            if (pos == end) {
                min = cost
                continue
            }
            if (cost <= seen[pos][dir]) {
                enqueue(Node(pos + RDLU[dir], dir, cost + 1))
                enqueue(Node(pos, (dir + 3) % 4, cost + 1000))
                enqueue(Node(pos, (dir + 1) % 4, cost + 1000))
            }
        }
        return min
    }

    override fun part1(input: String): Int {
        val grid = input.lines().toCharGrid()
        val start = grid.positionOf('S')
        val end = grid.positionOf('E')
        val seen = Grid(grid.shape) { IntArray(4) { Int.MAX_VALUE } }
        return dijkstra(grid, seen, start, end)
    }

    override fun part2(input: String): Int {
        val grid = input.lines().toCharGrid()
        val start = grid.positionOf('S')
        val end = grid.positionOf('E')
        val seen = Grid(grid.shape) { IntArray(4) { Int.MAX_VALUE } }
        dijkstra(grid, seen, start, end)

        val todo = ArrayList<Node>()
        val path = BooleanGrid(grid.shape)

        for ((dir, cost) in seen[end].withIndex()) todo += Node(end, dir, cost)

        fun enqueue(node: Node) {
            val (pos, dir, cost) = node
            if (cost == seen[pos][dir]) {
                todo += node
                seen[pos][dir] = Int.MAX_VALUE
            }
        }

        while (todo.isNotEmpty()) {
            val (pos, dir, cost) = todo.removeFirst()
            path[pos] = true
            if (pos != start) {
                enqueue(Node(pos - RDLU[dir], dir, cost - 1))
                enqueue(Node(pos, (dir + 3) % 4, cost - 1000))
                enqueue(Node(pos, (dir + 1) % 4, cost - 1000))
            }
        }

        return path.sumOf { it.count { b -> b } }
    }
}

fun main() = Day16().run {
    println(part1(input))
    println(part2(input))
}