package year2023

import util.core.*
import util.grid.*
import util.point.*
import java.util.*

class Day17 : Solution<Int, Int> {

    companion object {
        private val DIRECTIONS = mapOf(
            LEFT to setOf(LEFT, UP, DOWN),
            RIGHT to setOf(RIGHT, DOWN, UP),
            UP to setOf(UP, RIGHT, LEFT),
            DOWN to setOf(DOWN, LEFT, RIGHT),
        )
    }

    private data class State(val position: Point, val direction: Point, val steps: Int)

    private fun dijkstra(input: String, min: Int, max: Int): Int {
        val grid = input.lines().toIntGrid()
        val (width, height) = grid.shape

        val visited = HashMap<State, Int>()
        val todo = PriorityQueue<Pair<State, Int>>(compareBy { it.second })
        val start = State(Point(0, 0), RIGHT, 0)

        visited[start] = 0
        todo.add(start to 0)

        while (todo.isNotEmpty()) {
            val (state, cost) = todo.remove()
            if (cost <= visited[state]!!) {
                val (position, direction, steps) = state
                val nextDirections = if (steps < min) setOf(direction) else DIRECTIONS[direction]!!
                for (nextDirection in nextDirections) {
                    val next = nextDirection + position
                    val nextState = State(next, nextDirection, if (nextDirection == direction) steps + 1 else 1)
                    if (next in grid && nextState.steps <= max) {
                        val nextSteps = grid[next] + cost
                        if (nextState !in visited || nextSteps < visited[nextState]!!) {
                            visited[nextState] = nextSteps
                            todo.add(nextState to nextSteps)
                        }
                    }
                }
            }
        }
        val end = Point(width - 1, height - 1)
        return visited.filterKeys { it.position == end && it.steps >= min }.minBy { it.value }.value
    }

    override fun part1(input: String): Int = dijkstra(input, 0, 3)

    override fun part2(input: String): Int = dijkstra(input, 4, 10)
}
