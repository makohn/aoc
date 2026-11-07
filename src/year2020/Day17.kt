package year2020

import util.core.*
import util.grid.*

class Day17 : Solution<Int, Int> {

    companion object {
        private const val X = 22
        private const val Y = 22
        private const val Z = 15

        private const val STRIDE_X = 1
        private const val STRIDE_Y = X
        private const val STRIDE_Z = Y * X
    }

    override fun part1(input: String): Int {
        val grid = input.lines().toCharGrid()
        val size = X * Y * Z
        val base = STRIDE_X + STRIDE_Y + STRIDE_Z
        val neighbors = ArrayList<Int>()

        for (x in -1..1) {
            for (y in -1..1) {
                for (z in -1..1) {
                    val offset = x * STRIDE_X + y * STRIDE_Y + z * STRIDE_Z
                    if (offset != 0) {
                        neighbors.add(offset)
                    }
                }
            }
        }

        var active = ArrayList<Int>(5_000)
        val candidates = ArrayList<Int>(5_000)
        var nextActive = ArrayList<Int>(5_000)
        val (width, height) = grid.shape

        for (x in 0..<width) {
            for (y in 0..<height) {
                if (grid[x][y] == '#') {
                    val index = 7 * base + x + y * STRIDE_Y
                    active.add(index)
                }
            }
        }

        repeat(6) {
            val state = IntArray(size) { 0 }
            for (cube in active) {
                for (offest in neighbors) {
                    val index = cube + offest
                    state[index] += 1

                    if (state[index] == 3) {
                        candidates.add(index)
                    }
                }
            }
            nextActive.addAll(active.filter { cube -> state[cube] == 2 })
            nextActive.addAll(candidates.filter { cube -> state[cube] == 3 })

            active = nextActive.also { nextActive = active }
            candidates.clear()
            nextActive.clear()
        }
        return active.size
    }

    override fun part2(input: String): Int = 0
}
