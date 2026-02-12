package year2024

import util.core.*
import util.grid.*
import util.point.*

class Day16 : Solution<Int, Int>(year = 2024, day = 16) {

    private data class Node(val pos: Point, val dir: Int, val cost: Int)

    private val directions = arrayOf(RIGHT, DOWN, LEFT, UP)

    override fun part1(input: String): Int {
        val grid = input.lines().toCharGrid()
        val start = grid.positionOf('S')
        val end = grid.positionOf('E')

        val queue = ArrayList<Node>()
        val visited = Grid(grid.shape) { IntArray(4) { Int.MAX_VALUE } }

        queue += Node(start, 0, 0)
        visited[start][0] = 0

        var min = Int.MAX_VALUE

        while (queue.isNotEmpty()) {
            val (pos, dir, cost) = queue.removeFirst()
            if (cost >= min) continue
            if (pos == end) {
                min = cost
                continue
            }
            if (cost <= visited[pos][dir]) {
                val left = (dir + 3) % 4
                val right = (dir + 1) % 4
                val adjacent = arrayListOf(
                    Node(pos + directions[dir], dir, cost + 1),
                    Node(pos, left, cost + 1000),
                    Node(pos, right, cost + 1000)
                )
                for (other in adjacent) {
                    val (otherPos, otherDir, otherCost) = other
                    if (grid[otherPos] != '#' && otherCost < visited[otherPos][otherDir]) {
                        visited[otherPos][otherDir] = otherCost
                        queue += other
                    }
                }
            }
        }
        return min
    }

    override fun part2(input: String): Int {
        return 0
    }
}

fun main() = Day16().run {
    println(part1(input))
}