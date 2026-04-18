package year2022

import util.core.*
import util.parse.extractInts

class Day18 : Solution<Int, Int>(year = 2022, day = 18) {

    companion object {
        private const val SIZE = 24
    }

    private fun parse(input: String): IntArray {
        val cube = IntArray(SIZE * SIZE * SIZE)
        input.extractInts().chunked(3).forEach { (x, y, z) ->
            cube[(x + 1) * SIZE * SIZE + (y + 1) * SIZE + (z + 1)] = 1
        }
        return cube
    }

    private inline fun count(cube: IntArray, fn: (Int) -> Int): Int {
        var total = 0
        for ((i, cell) in cube.withIndex()) {
            if (cell == 1) {
                val neighbors = cube[i - 1] +
                    cube[i + 1] +
                    cube[i - SIZE] +
                    cube[i + SIZE] +
                    cube[i - SIZE * SIZE] +
                    cube[i + SIZE * SIZE]
                total += fn(neighbors)
            }
        }
        return total
    }

    override fun part1(input: String): Int {
        val cube = parse(input)
        return count(cube) { 6 - it }
    }

    override fun part2(input: String): Int {
        val cube = parse(input)
        cube[0] = 8

        val todo = ArrayList<Short>()
        todo += 0

        fun floodFill(next: Int) {
            if (next < cube.size && cube[next] == 0) {
                cube[next] = 8
                todo += next.toShort()
            }
        }

        while (todo.isNotEmpty()) {
            val index = todo.removeFirst()
            floodFill((index - 1).toUShort().toInt())
            floodFill(index + 1)
            floodFill((index - SIZE).toUShort().toInt())
            floodFill(index + SIZE)
            floodFill((index - SIZE * SIZE).toUShort().toInt())
            floodFill(index + SIZE * SIZE)
        }

        return count(cube) { it shr 3 }
    }
}
