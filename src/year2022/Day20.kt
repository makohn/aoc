package year2022

import util.core.*
import util.parse.*

class Day20 : Solution<Int, Int>(year = 2022, day = 20) {

    private data class Number(val value: Int, val index: Int)

    override fun part1(input: String): Int {
        val numbers = input.extractInts().withIndex().map { (index, value) -> Number(value, index) }.toMutableList()
        val n = numbers.size
        val lookup = IntArray(n) { it }

        for (i in numbers.indices) {
            val index = lookup[i]
            val number = numbers[index]
            val offset = number.value % (n - 1)
            val expected = (index + offset + (n - 1)) % (n - 1)
            numbers.removeAt(index)
            numbers.add(expected, number)
            for (j in numbers.indices) {
                lookup[numbers[j].index] = j
            }
        }
        val zeroIndex = numbers.indexOfFirst { it.value == 0 }
        return numbers[(1000 + zeroIndex) % n].value +
                numbers[(2000 + zeroIndex) % n].value +
                numbers[(3000 + zeroIndex) % n].value
    }

    override fun part2(input: String): Int {
        return 0
    }
}