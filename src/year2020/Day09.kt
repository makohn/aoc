package year2020

import util.core.*
import util.parse.*

class Day09(private val n: Int = 25) : Solution<Int, Int> {

    private fun findFirstInvalidNumber(numbers: List<Int>): Int = numbers
        .windowed(n + 1)
        .first {
            for (i in 0..<n - 1) {
                for (j in (i + 1)..<n) {
                    if (it[i] + it[j] == it[n]) {
                        return@first false
                    }
                }
            }
            true
        }[n]

    override fun part1(input: String): Int = findFirstInvalidNumber(input.extractInts())

    override fun part2(input: String): Int {
        val numbers = input.extractInts()
        val invalidNumber = findFirstInvalidNumber(numbers)

        var sum = numbers[0] + numbers[1]
        var start = 0
        var end = 2

        while (sum != invalidNumber) {
            if (sum < invalidNumber) {
                sum += numbers[end]
                end++
            } else {
                sum -= numbers[start]
                start++
            }
        }
        val range = numbers.subList(start, end)
        return range.min() + range.max()
    }
}
