package year2020

import util.core.*
import util.parse.*

class Day14 : Solution<Long, Int> {

    companion object {
        private const val MEMORY_SIZE = 1 shl 16
    }

    override fun part1(input: String): Long {
        val program = input.lines()
        val memory = LongArray(MEMORY_SIZE)
        var conjunct = 1L
        var disjunct = 0L
        for (line in program) {
            when (line[1]) {
                'a' -> {
                    val mask = line.substringAfter(" = ").reversed()
                    disjunct = mask.foldIndexed(0) { i, a, b -> if (b == '1') a or (1L shl i) else a }
                    conjunct = mask.foldIndexed(0) { i, a, b -> if (b != '0') a or (1L shl i) else a }
                }

                'e' -> {
                    val (address, value) = line.extractLongs()
                    memory[address.toInt()] = (value or disjunct) and conjunct
                }
            }
        }
        return memory.fold(0uL) { a, b -> a + b.toULong() }.toLong()
    }

    override fun part2(input: String): Int = 0
}
