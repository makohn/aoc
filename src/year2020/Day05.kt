package year2020

import util.core.*

class Day05 : Solution<Int, Int> {

    private fun String.decode() = fold(0) { a, b -> (a shl 1) or if (b == 'B' || b == 'R') 1 else 0 }

    private data class Data(val min: Int, val max: Int, val id: Int)

    override fun part1(input: String): Int = input.lines().maxOf { it.decode() }

    override fun part2(input: String): Int {
        val (min, max, id) = input.lines().fold(Data(Int.MAX_VALUE, Int.MIN_VALUE, 0)) { data, line ->
            val id = line.decode()
            Data(minOf(data.min, id), maxOf(data.max, id), data.id xor id)
        }
        return (min..max).fold(id) { a, b -> a xor b }
    }
}
