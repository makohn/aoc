package year2022

import util.core.*
import util.parse.*

class Day20 : Solution<Int, Long>(year = 2022, day = 20) {

    private data class Number<T>(val value: T, val index: Int)

    private inline fun <T> mix(numbers: MutableList<Number<T>>, n: Int, times: Int, offset: (T) -> Int) {
        val lookup = IntArray(n) { it }
        repeat(times) {
            for (i in numbers.indices) {
                val index = lookup[i]
                val number = numbers[index]
                val offset = offset(number.value)
                val expected = (index + offset + (n - 1)) % (n - 1)
                numbers.removeAt(index)
                numbers.add(expected, number)
                for (j in numbers.indices) {
                    lookup[numbers[j].index] = j
                }
            }
        }
    }

    override fun part1(input: String): Int {
        val numbers = input.extractInts().withIndex().map { (index, value) -> Number(value, index) }.toMutableList()
        val n = numbers.size
        mix(numbers, n, 1) { it % (n - 1) }
        val zeroIndex = numbers.indexOfFirst { it.value == 0 }
        return numbers[(1000 + zeroIndex) % n].value +
                numbers[(2000 + zeroIndex) % n].value +
                numbers[(3000 + zeroIndex) % n].value
    }

    override fun part2(input: String): Long {
        val numbers = input.extractInts().withIndex().map { (index, value) -> Number(811589153L * value, index) }.toMutableList()
        val n = numbers.size
        mix(numbers, n, 10) { (it % (n - 1)).toInt() }
        val zeroIndex = numbers.indexOfFirst { it.value == 0L }
        return numbers[(1000 + zeroIndex) % n].value +
                numbers[(2000 + zeroIndex) % n].value +
                numbers[(3000 + zeroIndex) % n].value
    }
}