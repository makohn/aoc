package year2022

import util.core.*
import util.parse.*
import kotlin.math.sqrt

class Day20 : Solution<Long, Long>(year = 2022, day = 20) {

    private fun mix(values: List<Long>, times: Int = 1): Long {
        val n = values.size
        val blockSize = sqrt(n.toDouble()).toInt() + 1
        val numBlocks = (n + blockSize - 1) / blockSize

        val blocks = MutableList(numBlocks) { mutableListOf<Int>() }
        val blockOf = IntArray(n) { i ->
            val b = i / blockSize
            blocks[b].add(i)
            b
        }

        repeat(times) {
            for (i in 0..<n) {
                val value = values[i]
                if (value % (n - 1) == 0L) continue
                val block = blockOf[i]
                val innerPos = blocks[block].indexOf(i)
                blocks[block].removeAt(innerPos)

                val pos = blocks.take(block).sumOf { it.size } + innerPos
                val steps = (value % (n - 1)).toInt()
                var newPos = (pos + steps) % (n - 1)
                if (newPos < 0) newPos += (n - 1)

                var targetBlock = 0
                var p = newPos
                while (targetBlock < blocks.size && p >= blocks[targetBlock].size) {
                    p -= blocks[targetBlock].size
                    targetBlock++
                }
                blocks[targetBlock].add(p, i)
                blockOf[i] = targetBlock
            }
        }

        val order = blocks.flatten()
        val zeroIndex = order.indexOfFirst { values[it] == 0L }
        fun get(offset: Int) = values[order[(zeroIndex + offset) % n]]

        return get(1000) + get(2000) + get(3000)
    }

    override fun part1(input: String): Long {
        val numbers = input.extractLongs()
        return mix(numbers)
    }

    override fun part2(input: String): Long {
        val numbers = input.extractLongs().map { 811589153L * it }
        return mix(numbers, 10)
    }
}